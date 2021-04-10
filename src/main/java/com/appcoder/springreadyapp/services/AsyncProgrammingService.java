package com.appcoder.springreadyapp.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class AsyncProgrammingService implements IAsyncProgrammingService {
    private static Log log = LogFactory.getLog(AsyncProgrammingService.class);

    @Override
    public void sendNotification(String emailId) throws ExecutionException, InterruptedException {

        CompletableFuture<Void> futureNotificationNormal = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                System.out.println("We are sending notification via notification service: runnable implementation");
                sendNotification(emailId, "hello");
            }
        });
        futureNotificationNormal.get();

        Executor executor = Executors.newFixedThreadPool(10);
        CompletableFuture<Void> futureNotificationExecutor = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                // Simulating a long-running Job
                sendNotification(emailId, "hello");
                System.out.println("We are sending notification via notification service: executor implementation");
            }
        }, executor);
        futureNotificationExecutor.get();
    }

    @Override
    public String customerWalletBalance(String emailId) throws ExecutionException, InterruptedException {

        CompletableFuture<CompletableFuture<Double>> balanceFuture = fetchWalletId(emailId)
                .thenApply(walletId -> loadWalletBalance(walletId));

        return "Wallet Balance : " + balanceFuture.get();
    }


    private CompletableFuture<Long> fetchWalletId(String emailId) {
        return CompletableFuture.supplyAsync(() -> {
            // Load wallet id using email id
            return 10001L;
        });
    }

    private CompletableFuture<Double> loadWalletBalance(Long walletId) {
        return CompletableFuture.supplyAsync(() -> {
            // Load wallet balance from database
            return 500.00;
        });
    }

    @Override
    public boolean placeOrder(Long customerId, Long productId) throws ExecutionException, InterruptedException {

        log.info("Request coming to placeOrder() method.");
        log.info("Thread Name :"+Thread.currentThread().getName());

        CompletableFuture<Boolean> combinedFuture = loadCustomer(customerId)
                .thenCombine(loadProduct(productId), (customer, product) -> {
                    boolean isOrderPlaced = saveOrder(customer, product);
                    return isOrderPlaced;
                });


        return combinedFuture.get();
    }

    @Override
    public String getUserFeeds(String emailId) throws ExecutionException, InterruptedException {

        log.info("In method getUserFeeds() : START");
        log.info("Thread Name :"+Thread.currentThread().getName());


        CompletableFuture<String> loadUsersFacebookFeedsFuture = loadUsersFacebookFeeds(emailId);
        CompletableFuture<String> loadUsersGitHubFeedsFuture = loadUsersGitHubFeeds(emailId);
        CompletableFuture<String> loadUsersMediumFeedsFuture = loadUsersMediumFeeds(emailId);

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                loadUsersFacebookFeedsFuture,
                loadUsersGitHubFeedsFuture,
                loadUsersMediumFeedsFuture
        );
        log.info("CALL allFutures.get() : START");

        allFutures.get();
        log.info("CALL allFutures.get() : END");

        String result = "";
        if (allFutures.isDone ()) {
            result = "User feed : "+loadUsersFacebookFeedsFuture.get()+". "+loadUsersGitHubFeedsFuture.get()+". "+loadUsersMediumFeedsFuture.get();
        } else {
            result = "Futures are not ready YET";
        }

        log.info("Result : "+result);

        return result;
    }

    CompletableFuture<String> loadUsersFacebookFeeds(String emailId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                log.info("loadUsersFacebookFeeds() : START");
                log.info("Thread Name :"+Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(5);
                log.info("loadUsersFacebookFeeds() : END");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "This is users github details";
        });
    }

    CompletableFuture<String> loadUsersGitHubFeeds(String emailId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                log.info("loadUsersGitHubFeeds() : START");
                log.info("Thread Name :"+Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(5);
                log.info("loadUsersGitHubFeeds() : END");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "This is users github details";
        });
    }

    CompletableFuture<String> loadUsersMediumFeeds(String emailId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                log.info("loadUsersMediumFeeds() : START");
                log.info("Thread Name :"+Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(5);
                log.info("loadUsersMediumFeeds() : END");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "This is users medium details";
        });
    }

    private CompletableFuture<Customer> loadCustomer(Long customerId) {

        return CompletableFuture.supplyAsync(() -> {
            // Load wallet id using email id
            try {
                log.info("loadCustomer() : START");
                TimeUnit.SECONDS.sleep(5);
                log.info("loadCustomer() : END");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Customer customer = null;
            return customer;
        });
    }

    private CompletableFuture<Product> loadProduct(Long productId) {
        return CompletableFuture.supplyAsync(() -> {
            // Load wallet balance from database
            try {
                log.info("Task loadProduct() : START");
                TimeUnit.SECONDS.sleep(5);
                log.info("Task loadProduct() : END");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Product product = null;
            return product;
        });
    }

    private boolean saveOrder(Customer customer, Product product) {
        // save data on database
        try {
            log.info("saveOrder() : START");
            TimeUnit.SECONDS.sleep(5);
            log.info("saveOrder() : END");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }


    public void sendNotification(String to, String message) {
        try {
            log.info("Sending notification. To : " + to + " Message :" + message);
            TimeUnit.SECONDS.sleep(5);
            log.info("Notification sending done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public class Product {
        Long id;
        String productName;
        Double price;
    }

    public class Customer {
        Long id;
        String name;
        Double age;
    }

}
