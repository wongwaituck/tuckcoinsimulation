package com.smu.view;

import com.smu.model.HashingUtility;

import java.math.BigInteger;

/**
 * Created by WaiTuck on 20/03/2016.
 */
public class Argon2BenchmarkThread implements Runnable{
    private byte[] a;
    private int memory;

    public Argon2BenchmarkThread(int memory){
        this.memory = memory;
    }
    @Override
    public void run() {
        while(true) {
            a = HashingUtility.getArgon2HashBytes("abc" + Argon2Benchmark.count, memory);

            Argon2Benchmark.count++;
        }
    }
}
