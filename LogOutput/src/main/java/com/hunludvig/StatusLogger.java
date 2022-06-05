package com.hunludvig;

import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class StatusLogger {
    private static final Logger LOG = LoggerFactory.getLogger(StatusLogger.class.getCanonicalName());

    @Inject
    private StatusStore status;

    @Scheduled(fixedDelay = "5s")
    public void printToken() {
        LOG.info("Current status is {}", status.currentStatus());
    }
}
