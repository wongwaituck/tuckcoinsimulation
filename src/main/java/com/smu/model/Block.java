package com.smu.model;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Created by WaiTuck on 06/02/2016.
 */
public class Block implements Serializable {
    //private byte[] prevBlockHash;
    private List<Transaction> transactions;
    private BigInteger nonce;

    public Block(List<Transaction> transactions) {
        this.transactions = transactions;
        nonce = BigInteger.ZERO;
    }

    public static Block getDummyBlock() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(Transaction.getDummyTransaction());
        return new Block(transactions);
    }

    public void incrementNonce() {
        nonce = nonce.add(BigInteger.ONE);
    }

    /**
     * Serialize the object to Base64 string.
     */
    public String serializeToString() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }


    /**
     * Read the object from Base64 string.
     */
    public static Block fromString(String s) throws IOException,
            ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(s);
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(data));
        Object o = ois.readObject();
        ois.close();
        return (Block) o;
    }

    public void addWinningMiner(Wallet w){
        transactions.add(0, new Transaction(null, w, 25));
    }

}
