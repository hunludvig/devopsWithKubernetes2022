package com.hunludvig;

import jakarta.inject.Singleton;
import java.util.Map;
import javax.validation.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class Deployments extends DependentResources {
    private static final Logger LOG = LoggerFactory.getLogger(Deployments.class.getCanonicalName());

    @Override
    public void add(
            @NotEmpty final String dummySiteName,
            @NotEmpty final String namespace,
            @NotEmpty final String websiteUrl,
            @NotEmpty final String path) {
        var deployment = renderTemplate("deployment.yml", Map.of(
                "name", deploymentName(dummySiteName),
                "namespace", namespace,
                "label", deploymentLabel(dummySiteName),
                "website_url", websiteUrl,
                "path", path
        ));
        k8s.apps().deployments().inNamespace(namespace).load(deployment).createOrReplace();
        LOG.info("Created deployment for {}", dummySiteName);
    }

    @Override
    public void cleanup(
            @NotEmpty final String dummySiteName,
            @NotEmpty final String namespace) {
        k8s.apps().deployments().inNamespace(namespace).withLabel("dummySite", deploymentLabel(dummySiteName)).delete();
        LOG.info("Deployment cleanded up for {}", dummySiteName);
    }

    private static String deploymentName(final String dummySiteName) {
        return String.format("%s-dep", dummySiteName);
    }

    private static String deploymentLabel(final String dummySiteName) {
        return dummySiteName;
    }
}
