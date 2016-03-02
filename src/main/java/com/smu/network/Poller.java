package com.smu.network;

import com.smu.StateStorage;

/**
 * Created by WaiTuck on 03/03/2016.
 */
public class Poller {
    public static final int INTERVAL = 300;

    public static void startPollingTransactions(){

        Thread transactionThread = new Thread(new TransactionHTTPRequest());
        StateStorage.getInstance().setTransactionThread(transactionThread);
        transactionThread.run();
    }

    public static void stopPollingTransactions(){
        Thread transactionThread = StateStorage.getInstance().getTransactionThread();
        if(transactionThread != null){
            transactionThread.interrupt();
        }
        StateStorage.getInstance().setTransactionThread(null);

    }
}
