package com.smu.model;

import com.smu.StateStorage;
import com.smu.network.SubmitBlockHTTPRequest;

/**
 * Created by WaiTuck on 06/02/2016.
 */
public abstract class Challenge implements Runnable{
    private int difficulty; //number of MSB bits that have to be zero
    private Block b; //block to enter into the blockchain

    public Challenge(int difficulty, Block b){
        this.difficulty = difficulty;
        this.b = b;
        b.addWinningMiner(StateStorage.getInstance().getWallet());
    }

    public int getDifficulty(){
        return difficulty;
    }
    
    //tries to solve the challenge and prints to output if it does
    public void run() {
        long startTime = System.nanoTime();
        solveChallenge();
        System.out.println("CONGRATURATIONS, you have won 25 TUCKCOINS. SUCH MONEY!");
        System.out.println("Challenge Solved in " + (System.nanoTime() - startTime));
        //send block to verified
        new Thread(new SubmitBlockHTTPRequest(b)).run();
        //remove from currenttx
        StateStorage.getInstance().setCurrentTx(null);
    }

    protected abstract void solveChallenge();

    public Block getBlock(){
        return b;
    }
}
