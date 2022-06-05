package com.hunludvig;

import jakarta.inject.Singleton;

@Singleton
public class StatusStore {
    private final Status status = new Status();

    public Status currentStatus() {
        return status;
    }
}
