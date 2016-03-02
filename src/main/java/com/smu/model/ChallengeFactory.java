package com.smu.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WaiTuck on 06/02/2016.
 */
public class ChallengeFactory {
    public enum ChallengeTypes{
        SHA256("SHA-256"), ARGON2("Argon2");
        private String type;
        ChallengeTypes(String type){
            this.type = type;
        }

        public String getType(){
            return type;
        }

    }

    public static Challenge getChallenge(ChallengeTypes type, int difficulty, Transaction tx){
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(tx);
        Block b = new Block(transactions);
        switch(type.getType()){
            case "SHA-256":
                return new SHA256Challenge(difficulty, b);
            case "Argon2":
                return new Argon2Challenge(difficulty, b);
            default:
                return new SHA256Challenge(difficulty, b);
        }
    }
}
