package com.smu.model;

import com.smu.StateStorage;
import com.smu.view.App;

import java.io.IOException;

/**
 * Created by WaiTuck on 06/02/2016.
 */
public class SHA256Challenge extends Challenge{

    public SHA256Challenge(int difficulty, Block b) {
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
                byte[] digest = HashingUtility.getSHA256HashBytes(serializedBlock);
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
                //App.clearScreen();
                //System.out.println("Trying Hash:");
                System.out.println(HashingUtility.getSHA256Hash(serializedBlock));
                if(!solvable){
                    b.incrementNonce();
                } else{

                    String winningHash = HashingUtility.getSHA256Hash(serializedBlock);
                    //System.out.println(winningHash);
                    b.setCurrentHash(winningHash);
                }
                System.out.println("Press 1 and enter to stop mining");
            } while(!solvable);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
