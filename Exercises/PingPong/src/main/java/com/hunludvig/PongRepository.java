package com.hunludvig;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import java.math.BigInteger;
import java.time.ZonedDateTime;

@Repository
public interface PongRepository extends JpaRepository<Pong, BigInteger>{

    default long currentValue() {
        return count();
    }

    default void increment() {
        var pong = new Pong();
        pong.setCreatedAt(ZonedDateTime.now());
        save(pong);
    }
}
