package com.appcoder.springreadyapp.services;

import com.appcoder.springreadyapp.repository.RedisCacheRepository;
import com.appcoder.springreadyapp.request.TransactionRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class RedisTestServiceImpl implements RedisTestService {
    private static Log log = LogFactory.getLog(RedisTestService.class);
    private RedisCacheRepository cacheRepository;

    public RedisTestServiceImpl(RedisCacheRepository cacheRepository) {
        this.cacheRepository = cacheRepository;
    }

    @Override
    public boolean doTransactionNormal(TransactionRequest transactionRequest) throws RuntimeException {

        String key = "TRANSACTION-" + transactionRequest.getSenderWalletId().toString();

        if (cacheRepository.isTransactionRunningForSender(key)) {
            log.error("Transaction Failed, Transaction is going on for sender : " + transactionRequest.getSenderWalletId());
            throw new RuntimeException("Transaction Failed, Transaction is going on for sender : " + transactionRequest.getSenderWalletId());
        }

        fetchSomeValueFromDb();

        cacheRepository.makeSenderBusyForTransactionNormal(key);

        saveSomeDataIntoDb();
        log.info("Transaction Done");
        cacheRepository.makeSenderFreeForTransactionNormal(key);
        return true;

    }

    @Override
    public boolean doTransactionLock(TransactionRequest transactionRequest) throws RuntimeException {

        String key = "TRANSACTION-" + transactionRequest.getSenderWalletId().toString();

        if (cacheRepository.acquireLock(key)) {
            fetchSomeValueFromDb();
            saveSomeDataIntoDb();
            log.info("Transaction Done");
            cacheRepository.releaseLock(key);
            return true;
        } else {
            log.error("Transaction Failed, Transaction is going on for sender : " + transactionRequest.getSenderWalletId());
            throw new RuntimeException("Transaction Failed, Transaction is going on for sender : " + transactionRequest.getSenderWalletId());
        }
    }

    public void fetchSomeValueFromDb() {
        log.info("Fetching some data from database.");
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("Data fetch done from database.");
    }

    public void saveSomeDataIntoDb() {
        log.info("Saving data into database.");

        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("Saving data into database done.");

    }
}
