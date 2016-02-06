package com.smu.view;

import com.smu.model.Challenge;
import com.smu.model.ChallengeFactory;

/**
 * Created by WaiTuck on 06/02/2016.
 */
public class App {
    public static void main(String[] args) throws Exception{
        System.out.println("Running 10 sha-256 challenges");
        for(int i = 20; i <= 30; i++) {
            Challenge c = ChallengeFactory.getChallenge("SHA-256", 15);
            new Thread(c).run();
        }

        System.out.println("Running 10 argon2 challenges");
        for(int i = 0; i < 10; i++) {
            Challenge c = ChallengeFactory.getChallenge("Argon2", 15);
            new Thread(c).run();
        }

    }
}
