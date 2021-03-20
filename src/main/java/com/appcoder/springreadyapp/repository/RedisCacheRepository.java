package com.appcoder.springreadyapp.repository;

public interface RedisCacheRepository {
    public boolean isTransactionRunningForSender(String key);
    void makeSenderBusyForTransactionNormal(String key);
    void makeSenderFreeForTransactionNormal(String key);

    public boolean acquireLock(String key);
    public boolean releaseLock(String key);

}
