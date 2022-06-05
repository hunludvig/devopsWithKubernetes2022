package com.hunludvig;

import java.util.Date;
import java.util.UUID;

public class Status {
    private final String token = UUID.randomUUID().toString();
    private final Date createdAt = new Date();

    public String getToken() {
        return token;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return token;
    }
}
