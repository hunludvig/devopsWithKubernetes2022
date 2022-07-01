package com.hunludvig;

import io.micronaut.context.annotation.Value;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class TimestampWriter {
    private static final Logger LOG = LoggerFactory.getLogger(TimestampWriter.class.getCanonicalName());

    @Value("${micronaut.application.timestamp-path}")
    private String path;

    @Scheduled(fixedDelay = "5s")
    public void saveStatus() throws IOException {
        var now = new Date();
        Files.writeString(Paths.get(path), String.valueOf(now.getTime()));
        LOG.info("Current timestamp {} is written to {}", now, path);
    }
}
