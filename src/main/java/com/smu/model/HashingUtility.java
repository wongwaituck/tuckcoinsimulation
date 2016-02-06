package com.smu.model;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by WaiTuck on 06/02/2016.
 */
public class HashingUtility{
    public static String getSHA256Hash(String a){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(a.getBytes("UTF-8")); // Change this to "UTF-16" if needed
            byte[] digest = md.digest();
            return String.format("%064x", new java.math.BigInteger(1, digest));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] getSHA256HashBytes (String a){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(a.getBytes("UTF-8")); // Change this to "UTF-16" if needed
            return md.digest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] getArgon2HashBytes (String a){
        Argon2 argon2 = Argon2Factory.create();
        String hash =  argon2.hash(2, 65536, 1, a);
        return hash.getBytes();
    }

    public static String saltString(String a){
        //generate random UUID
        return a + UUID.randomUUID().toString();
    }
}
