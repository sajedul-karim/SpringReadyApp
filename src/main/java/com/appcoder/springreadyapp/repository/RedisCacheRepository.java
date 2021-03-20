package com.appcoder.springreadyapp.repository;

public interface RedisCacheRepository {
    public boolean isKeyExists(String key);
    public boolean acquireLock(String key);
    public boolean releaseLock(String key);
}
