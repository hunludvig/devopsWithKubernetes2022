package com.hunludvig;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.io.IOException;
import java.math.BigDecimal;

@Singleton
public class PongStore implements Store {

    @Inject
    private PongFetcher fetcher;

    private BigDecimal pongCounter;

    public BigDecimal currentCounter() {
        return pongCounter;
    }

    @Override
    public void update() throws IOException {
        pongCounter = new BigDecimal(fetcher.fetchPongs());
    }
}
