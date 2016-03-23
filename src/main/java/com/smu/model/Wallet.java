package com.smu.model;

import java.io.Serializable;

/**
 * Created by WaiTuck on 06/02/2016.
 */
public class Wallet implements Serializable{
    private String ownerName;
    private String ownerKey;

    public Wallet(String ownerName){
        this(ownerName, HashingUtility.getSHA256Hash(ownerName));
    }

    public Wallet(String ownerName, String ownersKey) {
        this.ownerName = ownerName;
        this.ownerKey = ownersKey;
    }

    public String getName() {
        return ownerName;
    }

    public boolean equals(Object obj){
        if(obj instanceof Wallet){
            Wallet other = (Wallet) obj;
            return ownerName.equals(other.ownerName) && ownerKey.equals(other.ownerKey);
        }
        return false;
    }
}
