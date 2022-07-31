package com.hunludvig;

import io.fabric8.kubernetes.api.model.GenericKubernetesResource;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.WatcherException;
import io.fabric8.kubernetes.client.dsl.base.CustomResourceDefinitionContext;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import io.netty.channel.EventLoopGroup;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class ResourceWatcher {
    private static final Logger LOG = LoggerFactory.getLogger(ResourceWatcher.class.getCanonicalName());

    @Inject
    private EventLoopGroup loop;

    @Inject
    private KubernetesClient k8s;

    @Inject
    private CustomResourceDefinitionContext dummySitesContext;

    @Inject
    private Collection<DependentResources> dependentResources;

    @EventListener
    public void watch(final ServerStartupEvent event) {
        k8s.genericKubernetesResources(dummySitesContext).inAnyNamespace().watch(new Watcher<GenericKubernetesResource>() {
            @Override
            public void eventReceived(final Watcher.Action action, GenericKubernetesResource t) {
                switch (action) {
                    case ADDED -> {
                        LOG.info("Resource {} added {}", t.getKind(), t.getMetadata().getName());
                        addResources(t);
                    }
                    case DELETED -> {
                        LOG.info("Resource {} deleted {}", t.getKind(), t.getMetadata().getName());
                        cleanupResources(t);
                    }
                    case MODIFIED -> {
                        LOG.info("Resource {} modified {}", t.getKind(), t.getMetadata().getName());
                        cleanupResources(t);
                        addResources(t);
                    }
                }
            }

            @Override
            public void onClose(final WatcherException we) {
                loop.shutdownGracefully();
            }
        });
        LOG.info("Wathing {} started", dummySitesContext.getName());
    }

    private void cleanupResources(GenericKubernetesResource t) {
        for(var resources : dependentResources) {
            resources.cleanup(
                    t.getMetadata().getName(),
                    t.getMetadata().getNamespace());
        }
    }

    private void addResources(GenericKubernetesResource t) {
        for(var resources : dependentResources) {
            resources.add(
                    t.getMetadata().getName(),
                    t.getMetadata().getNamespace(),
                    t.get("spec", "website_url"),
                    t.get("spec", "path"));
        }
    }
}
