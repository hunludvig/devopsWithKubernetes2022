package com.hunludvig;

import io.fabric8.kubernetes.api.model.apiextensions.v1.CustomResourceDefinition;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;
import io.fabric8.kubernetes.client.dsl.base.CustomResourceDefinitionContext;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;

@Factory
public class K8sConfig {

    @Singleton
    public KubernetesClient k8s() {
        return new KubernetesClientBuilder().build();
    }

    @Singleton
    public CustomResourceDefinition dummySitesCrd(final KubernetesClient k8s) {
        return k8s.apiextensions().v1().customResourceDefinitions().withName("dummysites.hunludvig.fi").fromServer().get();
    }

    @Singleton
    public CustomResourceDefinitionContext dummySitesContext(final CustomResourceDefinition dummySitesCrd) {
        return CustomResourceDefinitionContext.fromCrd(dummySitesCrd);
    }
}
