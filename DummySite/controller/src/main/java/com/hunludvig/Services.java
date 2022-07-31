package com.hunludvig;

import jakarta.inject.Singleton;
import java.util.Map;
import javax.validation.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class Services extends DependentResources {
    private static final Logger LOG = LoggerFactory.getLogger(Services.class.getCanonicalName());

    @Override
    public void add(
            @NotEmpty final String dummySiteName,
            @NotEmpty final String namespace,
            @NotEmpty final String websiteUrl,
            @NotEmpty final String path) {
        var service = renderTemplate("service.yml", Map.of(
                "name", serviceName(dummySiteName),
                "namespace", namespace,
                "label", serviceLabel(dummySiteName)
        ));
        k8s.services().inNamespace(namespace).load(service).createOrReplace();
        LOG.info("Created service for {}", dummySiteName);
    }

    @Override
    public void cleanup(
            @NotEmpty final String dummySiteName,
            @NotEmpty final String namespace) {
        k8s.services().inNamespace(namespace).withLabel("dummySite", serviceLabel(dummySiteName)).delete();
        LOG.info("Service cleanded up for {}", dummySiteName);
    }

    public static String serviceName(final String dummySiteName) {
        return String.format("%s-svc", dummySiteName);
    }

    private static String serviceLabel(final String dummySiteName) {
        return dummySiteName;
    }
}
