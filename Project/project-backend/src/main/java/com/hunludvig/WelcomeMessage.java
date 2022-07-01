package com.hunludvig;

import io.micronaut.context.annotation.Value;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class WelcomeMessage {
    private static final Logger LOG = LoggerFactory.getLogger(WelcomeMessage.class.getCanonicalName());
    
    @Value("${micronaut.server.port}")
    private Integer port;

    
    @EventListener
    public void printMessage(ServerStartupEvent event) {
        LOG.info("Server started in port {}", port);
    }
}
