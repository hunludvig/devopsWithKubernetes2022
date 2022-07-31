package com.hunludvig;

import io.fabric8.kubernetes.client.KubernetesClient;
import jakarta.inject.Inject;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.validation.constraints.NotEmpty;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

public abstract class DependentResources {

    @Inject
    protected KubernetesClient k8s;

    @Inject
    private TemplateEngine engine;

    public abstract void add(
            @NotEmpty String dummySiteName,
            @NotEmpty String namespace,
            @NotEmpty String websiteUrl,
            @NotEmpty String path);

    public abstract void cleanup(
            @NotEmpty String dummySiteName,
            @NotEmpty String namespace);

    protected InputStream renderTemplate(String template, Map<String, Object> variables) {
        var context = new Context();
        context.setVariables(variables);
        var output = engine.process(template, context).getBytes(StandardCharsets.UTF_8);
        return new ByteArrayInputStream(output);
    }
}
