package com.smu.model;

import java.io.Serializable;

/**
 * Created by WaiTuck on 06/02/2016.
 */
public class Transaction implements Serializable{
    private Wallet from;
    private Wallet to;
    private long amount;

    public Transaction(Wallet from, Wallet to, long amount){
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public static Transaction getDummyTransaction(){
        Wallet alice = new Wallet("alice");
        Wallet bob = new Wallet("bob");
        return new Transaction(alice, bob, 100);
    }
}
