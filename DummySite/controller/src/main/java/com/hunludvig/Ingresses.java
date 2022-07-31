package com.hunludvig;

import jakarta.inject.Singleton;
import java.util.Map;
import javax.validation.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class Ingresses extends DependentResources {
    private static final Logger LOG = LoggerFactory.getLogger(Ingresses.class.getCanonicalName());

    @Override
    public void add(
            @NotEmpty final String dummySiteName,
            @NotEmpty final String namespace,
            @NotEmpty final String websiteUrl,
            @NotEmpty final String path) {
        var ingress = renderTemplate("ingress.yml", Map.of(
                "name", ingressName(dummySiteName),
                "namespace", namespace,
                "label", ingressLabel(dummySiteName),
                "path", path,
                "service_name", Services.serviceName(dummySiteName)
        ));
        k8s.network().v1().ingresses().inNamespace(namespace).load(ingress).createOrReplace();
        LOG.info("Created ingress for {}", dummySiteName);
    }

    @Override
    public void cleanup(
            @NotEmpty final String dummySiteName,
            @NotEmpty final String namespace) {
        k8s.network().v1().ingresses().inNamespace(namespace).withLabel("dummySite", ingressLabel(dummySiteName)).delete();
        LOG.info("Ingress cleanded up for {}", dummySiteName);
    }

    private static String ingressName(final String dummySiteName) {
        return String.format("%s-ingress", dummySiteName);
    }

    private static String ingressLabel(final String dummySiteName) {
        return dummySiteName;
    }
}
