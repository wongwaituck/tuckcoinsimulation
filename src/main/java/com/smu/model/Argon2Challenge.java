package com.smu.model;

import java.io.IOException;

/**
 * Created by WaiTuck on 06/02/2016.
 */
public class Argon2Challenge extends Challenge {
    public Argon2Challenge(int difficulty, Block b) {
        super(difficulty, b);
    }

    @Override
    protected void solveChallenge() {
        //get dummy block
        Block b = this.getBlock();
        //serialize
        try {
            boolean solvable;
            do {
                solvable = true;
                String serializedBlock = b.serializeToString();
                byte[] digest = HashingUtility.getArgon2HashBytes(serializedBlock);
                //get end index of array to check
                int endIndex = getDifficulty() / 8;

                for (int i = 0; i <= endIndex && solvable; i++) {
                    int toCheck = (int) digest[i];
                    int lastBitToCheck = (i == endIndex) ? (7 - getDifficulty() % 8) : 0;
                    for (int j = 7; j >= lastBitToCheck; j--) {
                        //bit mask
                        int bitMask = 0b00000001 << j;
                        int masked = toCheck & bitMask;
                        if (masked != 0) {
                            solvable = false;
                        }
                    }
                }

                if(!solvable){
                    b.incrementNonce();
                } else{
                    System.out.println(new String(HashingUtility.getArgon2HashBytes(serializedBlock), "ASCII"));
                    System.out.println("Solved! Winning block: " + serializedBlock);
                }
            } while(!solvable);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
