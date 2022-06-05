package com.hunludvig;

import io.micronaut.context.annotation.Value;
import jakarta.inject.Singleton;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

@Singleton
public class PongStore {

    @Value("${micronaut.application.pingpong-path}")
    private String path;

    private BigDecimal pongCounter;

    public BigDecimal currentCounter() {
        return pongCounter;
    }

    public void update() throws IOException {
        pongCounter = new BigDecimal(Files.readString(Paths.get(path)));
    }
}
