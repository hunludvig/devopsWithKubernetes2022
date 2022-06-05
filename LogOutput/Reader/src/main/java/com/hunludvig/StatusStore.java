package com.hunludvig;

import io.micronaut.context.annotation.Value;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

@Singleton
public class StatusStore {

    @Value("${micronaut.application.timestamp-path}")
    private String path;

    private Status status;

    @PostConstruct
    public void init() {
        status = new Status();
    }

    public Status currentStatus() {
        return status;
    }

    public void update() throws IOException {
        var timestamp = new Date(Long.valueOf(Files.readString(Paths.get(path))));
        if(!status.getCreatedAt().equals(timestamp)) {
            status = new Status(status, timestamp);
        }
    }
}
