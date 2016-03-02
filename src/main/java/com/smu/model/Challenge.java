package com.smu.model;

/**
 * Created by WaiTuck on 06/02/2016.
 */
public abstract class Challenge implements Runnable{
    private int difficulty; //number of MSB bits that have to be zero
    private Block b; //block to enter into the blockchain

    public Challenge(int difficulty, Block b){
        this.difficulty = difficulty;
        this.b = b;
    }

    public int getDifficulty(){
        return difficulty;
    }
    
    //tries to solve the challenge and prints to output if it does
    public void run() {
        long startTime = System.nanoTime();
        solveChallenge();
        System.out.println("Difficulty: " + difficulty);
        System.out.println("Challenge Solved in " + (System.nanoTime() - startTime));
    }

    protected abstract void solveChallenge();

}
