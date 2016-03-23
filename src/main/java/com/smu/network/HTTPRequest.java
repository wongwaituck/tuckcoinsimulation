package com.smu.network;

import org.apache.http.client.HttpClient;

/**
 * Created by WaiTuck on 02/03/2016.
 */
public abstract class HTTPRequest implements Runnable{
    public static  String BASE_URL = "http://139.59.249.57";
    public abstract void run();
}
