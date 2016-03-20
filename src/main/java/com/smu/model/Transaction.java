package com.smu.model;

import java.io.Serializable;

/**
 * Created by WaiTuck on 06/02/2016.
 */
public class Transaction implements Serializable{
    private String _id;
    private Wallet fromWallet;
    private Wallet toWallet;
    private long amount;

    public Transaction(Wallet fromWallet, Wallet toWallet, long amount){
        this.fromWallet = fromWallet;
        this.toWallet = toWallet;
        this.amount = amount;
    }

    public static Transaction getDummyTransaction(){
        Wallet alice = new Wallet("alice");
        Wallet bob = new Wallet("bob");
        return new Transaction(alice, bob, 100);
    }

    public boolean equals(Object obj){
        if(obj instanceof Transaction) {
            Transaction otherTx = (Transaction) obj;
            if (fromWallet != null) {
                return otherTx.fromWallet.equals(fromWallet) && otherTx.toWallet.equals(toWallet) && amount == otherTx.amount;
            } else if (fromWallet == otherTx.fromWallet){
                return otherTx.toWallet.equals(toWallet) && amount == otherTx.amount;
            }
        }
        return false;
    }
}
