package com.hunludvig;

import io.micronaut.context.annotation.Value;
import jakarta.inject.Singleton;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Singleton
public class ConfigStore implements Store {

    @Value("${micronaut.application.config-path}")
    private String path;

    private Properties config;

    @Override
    public void update() throws IOException {
        config = new Properties();
        config.load(new FileInputStream(path));
    }

    public String currentMessage() {
        return config.getProperty("MESSAGE", "N/A");
    }
}
