package com.miniredis.storage;

public class ExpiringKey {
    private final String value;
    private final long expiryTime;

    public ExpiringKey(String value, long ttlMillis) {
        this.value = value;
        this.expiryTime = System.currentTimeMillis() + ttlMillis;
    }

    public String getValue() {
        if (System.currentTimeMillis() > expiryTime) {
            return null;
        }
        return value;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
}
