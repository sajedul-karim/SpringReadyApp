package com.appcoder.springreadyapp.services;

public interface RedisCacheRepository {
    public boolean isKeyExists(String key);
    public boolean acquireLock(String key);
    public boolean releaseLock(String key);
}
