package com.hunludvig;

import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class StringPrinter {
    private static final Logger LOG = LoggerFactory.getLogger(StringPrinter.class.getCanonicalName());

    private static final String TOKEN = UUID.randomUUID().toString();

    @Scheduled(fixedDelay = "5s")
    public void printToken() {
        LOG.info("Token is {}", TOKEN);
    }
}
