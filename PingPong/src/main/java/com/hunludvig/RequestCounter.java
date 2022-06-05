package com.hunludvig;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;
import java.math.BigDecimal;

@Singleton
public class RequestCounter {

    private BigDecimal counter;

    public BigDecimal currentValue() {
        return counter;
    }

    public void increment() {
        counter = counter.add(BigDecimal.ONE);
    }

    @PostConstruct
    public void reset() {
        counter = BigDecimal.ZERO;
    }
}
