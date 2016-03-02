package com.smu.model;

import java.io.Serializable;

/**
 * Created by WaiTuck on 06/02/2016.
 */
public class Wallet implements Serializable{
    private String ownerName;
    private String ownersKey;

    public Wallet(String ownerName){
        this(ownerName, HashingUtility.getSHA256Hash(HashingUtility.saltString(ownerName)));
    }

    public Wallet(String ownerName, String ownersKey) {
        this.ownerName = ownerName;
        this.ownersKey = ownersKey;
    }

    public String getName() {
        return ownerName;
    }
}
