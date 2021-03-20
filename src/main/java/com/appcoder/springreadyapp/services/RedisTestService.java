package com.appcoder.springreadyapp.services;

import com.appcoder.springreadyapp.request.TransactionRequest;

public interface RedisTestService {
    boolean doTransaction(TransactionRequest transactionRequest) throws RuntimeException;
}
