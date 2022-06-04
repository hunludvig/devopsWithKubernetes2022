package com.hunludvig;

import java.util.UUID;

public class Hash {
    
    private final String hash = UUID.randomUUID().toString().substring(0, 8);
    
    public String get() {
        return hash;
    }
}
