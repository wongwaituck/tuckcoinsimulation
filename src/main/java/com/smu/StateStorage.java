package com.smu;

import com.smu.model.Block;
import com.smu.model.BlockChain;
import com.smu.model.Transaction;
import com.smu.model.Wallet;
import com.smu.network.BlocksHTTPRequest;
import com.smu.network.GoOfflineHTTPRequest;
import com.smu.network.SendWalletHTTPRequest;
import com.smu.network.StopMiningHTTPRequest;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

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
    private ScheduledExecutorService transactionService;
    private Thread verfiedChainThread;
    private List<Block> blocks;
    private List<Block> verifiedBlockChain;
    public static boolean adminMode = false;

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
        new Thread(new SendWalletHTTPRequest(myWallet)).run();
    }

    public String getName(){
        return myWallet.getName();
    }

    public synchronized void setBlocks(List<Block> blocks){
        this.blocks = blocks;
        BlockChain.parseBlocks();
    }

    public synchronized List<Block> getBlocks(){
        if(blocks == null){
            new BlocksHTTPRequest().run();
        }
        return blocks;
    }

    public synchronized void setVerifiedBlockChain(List<Block> blocks){
        this.verifiedBlockChain = blocks;
    }

    public synchronized List<Block> getVerifiedBlockChain(List<Block> blocks){
        return verifiedBlockChain;
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

    public ScheduledExecutorService getTransactionService() {
        return transactionService;
    }

    public void setTransactionService(ScheduledExecutorService transactionService) {
        this.transactionService = transactionService;
    }

    public Thread getVerfiedChainThread() {
        return verfiedChainThread;
    }

    public void setVerfiedChainThread(Thread verfiedChainThread) {
        this.verfiedChainThread = verfiedChainThread;
    }

    public void quit(){
        new Thread(new StopMiningHTTPRequest(myWallet)).run();
        new Thread(new GoOfflineHTTPRequest(myWallet)).run();
        System.exit(0);
    }
}
