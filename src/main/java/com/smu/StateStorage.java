package com.smu;

import com.smu.model.Wallet;

/**
 * Created by WaiTuck on 02/03/2016.
 */
public class StateStorage {
    private static StateStorage stateStorage;
    private Wallet myWallet;
    private boolean evilMode = false;

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

    public boolean isEvilMode(){
        return evilMode;
    }




}
