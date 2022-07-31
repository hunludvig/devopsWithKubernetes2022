package com.hunludvig;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.messageresolver.IMessageResolver;
import org.thymeleaf.messageresolver.StandardMessageResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Factory
public class ThymeleafConfig {

    @Bean
    public ITemplateResolver templateResolver() {
        var resolver = new ClassLoaderTemplateResolver();
        resolver.setTemplateMode(TemplateMode.TEXT);
        return resolver;
    }

    @Bean
    public IMessageResolver messageResolver() {
        return new StandardMessageResolver();
    }

    @Bean
    public TemplateEngine engine(final ITemplateResolver templateResolver, final IMessageResolver messageResolver) {
        var engine = new TemplateEngine();
        engine.setTemplateResolver(templateResolver);
        engine.setMessageResolver(messageResolver);
        return engine;
    }
}
