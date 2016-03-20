package com.smu.view;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WaiTuck on 20/03/2016.
 */
public class Argon2Benchmark {
    public static  long count = 0;
    public static void main(String[] args) throws InterruptedException {
        //create 8 threads
        new Thread(new Argon2BenchmarkThread(4096000)).start();

        Thread.sleep(60000);
        System.out.println(count);

    }
}
