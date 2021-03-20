package com.appcoder.springreadyapp.services;

import com.appcoder.springreadyapp.request.TransactionRequest;

public interface RedisTestService {
    boolean doTransactionNormal(TransactionRequest transactionRequest) throws RuntimeException;
    boolean doTransactionLock(TransactionRequest transactionRequest) throws RuntimeException;
}
