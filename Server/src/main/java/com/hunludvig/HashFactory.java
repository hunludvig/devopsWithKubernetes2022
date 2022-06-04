package com.hunludvig;

import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Primary;
import io.micronaut.context.annotation.Prototype;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Factory
public class HashFactory {

    @Primary
    @Prototype
    public Hash ephemeralHash() {
        return new Hash();
    }

    @Singleton
    @Named("persistent")
    public Hash persistentHash() {
        return new Hash();
    }
}
