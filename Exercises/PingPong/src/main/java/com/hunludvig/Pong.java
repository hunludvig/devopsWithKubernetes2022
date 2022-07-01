package com.hunludvig;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Pong implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private BigInteger id;

    private ZonedDateTime createdAt;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
