package com.hunludvig;

import io.micronaut.context.annotation.Value;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class RequestCounter {
    private static final Logger LOG = LoggerFactory.getLogger(RequestCounter.class.getCanonicalName());

    @Value("${micronaut.application.pingpong-path}")
    private String path;

    private BigDecimal counter;

    public BigDecimal currentValue() {
        return counter;
    }

    public void increment() {
        counter = counter.add(BigDecimal.ONE);
        save();
    }

    @PostConstruct
    public void reset() {
        counter = BigDecimal.ZERO;
        save();
    }

    private void save() {
        try {
            Files.writeString(Paths.get(path), String.valueOf(counter));
            LOG.info("Current timestamp {} is written to {}", counter, path);
        } catch (IOException e) {
            LOG.error("Cannot save pong counter to {}", path, e);
        }
    }
}
