package com.smu;

import com.smu.model.Transaction;
import com.smu.model.Wallet;

import java.util.List;

/**
 * Created by WaiTuck on 02/03/2016.
 */
public class StateStorage {
    private static StateStorage stateStorage;
    private Wallet myWallet;
    private boolean evilMode = false;
    private boolean isMining = false;
    private Transaction currentlyWorkingTx;
    private Thread miningThread;
    private Thread transactionThread;
    private Thread verfiedChainThread;

    private StateStorage(){}

    public static StateStorage getInstance(){
        if(stateStorage == null){
            StateStorage ss = new StateStorage();
            stateStorage = ss;
        }
        return stateStorage;
    }

    public void setWallet(String name){
        this.myWallet = new Wallet(name);
    }

    public String getName(){
        return myWallet.getName();
    }

    public void toggleEvilMode(){
        evilMode = !evilMode;
    }

    public void toggleMining(){
        isMining = !isMining;
    }

    public boolean isMining(){
        return isMining;
    }

    public boolean isEvilMode(){
        return evilMode;
    }


    public Wallet getWallet() {
        return myWallet;
    }

    public synchronized Transaction getCurrentTx(){
        return currentlyWorkingTx;
    }

    public synchronized void setCurrentTx(Transaction tx){
        currentlyWorkingTx = tx;

    }

    public Thread getMiningThread() {
        return miningThread;
    }

    public void setMiningThread(Thread miningThread) {
        this.miningThread = miningThread;
    }

    public Thread getTransactionThread() {
        return transactionThread;
    }

    public void setTransactionThread(Thread transactionThread) {
        this.transactionThread = transactionThread;
    }

    public Thread getVerfiedChainThread() {
        return verfiedChainThread;
    }

    public void setVerfiedChainThread(Thread verfiedChainThread) {
        this.verfiedChainThread = verfiedChainThread;
    }
}
