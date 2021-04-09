package com.appcoder.springreadyapp.services;

import java.util.concurrent.ExecutionException;

public interface IAsyncProgrammingService {
    void sendNotification(String emailId) throws ExecutionException, InterruptedException;
    String customerWalletBalance(String emailId) throws ExecutionException, InterruptedException;
    boolean placeOrder(Long customerId, Long productId) throws ExecutionException, InterruptedException;
    String getUserFeeds(String emailId) throws ExecutionException, InterruptedException;
}
