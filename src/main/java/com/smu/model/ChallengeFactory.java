package com.smu.model;

/**
 * Created by WaiTuck on 06/02/2016.
 */
public class ChallengeFactory {
    public static Challenge getChallenge(String type, int difficulty){
        switch(type){
            case "SHA-256":
                return new SHA256Challenge(difficulty);
            case "Argon2":
                return new Argon2Challenge(difficulty);
            default:
                return new SHA256Challenge(difficulty);
        }
    }
}
