package com.smu.network;

import org.apache.http.client.HttpClient;

/**
 * Created by WaiTuck on 02/03/2016.
 */
public abstract class HTTPRequest implements Runnable{
    public static final String BASE_URL = "http://127.0.0.1:3000";
    public abstract void run();
}
