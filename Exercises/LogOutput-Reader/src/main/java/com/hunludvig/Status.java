package com.hunludvig;

import java.util.Date;
import java.util.UUID;

public class Status {
    private final String token;
    private final Date createdAt;

    public Status() {
        token = UUID.randomUUID().toString();
        createdAt = new Date();
    }

    public Status(Status oldStatus, Date timestamp) {
        token = oldStatus.getToken();
        createdAt = timestamp;
    }

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
