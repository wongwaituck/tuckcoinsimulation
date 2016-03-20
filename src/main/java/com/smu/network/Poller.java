package com.smu.network;

import com.smu.StateStorage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by WaiTuck on 03/03/2016.
 */
public class Poller {
    public static final int INTERVAL = 1;

    public static void startPollingTransactions(){
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new TransactionHTTPRequest(), 0, INTERVAL, TimeUnit.SECONDS);
        StateStorage.getInstance().setTransactionService(service);
        new Thread(new StartMiningHTTPRequest(StateStorage.getInstance().getWallet())).run();
    }

    public synchronized static void stopPollingTransactions(){
        ScheduledExecutorService transactionService = StateStorage.getInstance().getTransactionService();
        if(transactionService != null){
            transactionService.shutdown();
        }
        StateStorage.getInstance().setTransactionService(null);
        new Thread(new StopMiningHTTPRequest(StateStorage.getInstance().getWallet())).run();

    }

    public static void startPollingBlocks(){
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new BlocksHTTPRequest(), 0, INTERVAL, TimeUnit.SECONDS);
        StateStorage.getInstance().setTransactionService(service);
    }
}
