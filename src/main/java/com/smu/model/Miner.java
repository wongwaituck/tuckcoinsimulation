package com.smu.model;

/**
 * Created by WaiTuck on 02/03/2016.
 */
public class Miner implements Runnable{

    private Block block; //transaction to verify

    public Miner(Block block){
        this.block = block;
    }

    @Override
    public void run() {

    }
}
