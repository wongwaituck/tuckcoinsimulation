package com.smu.network;

import com.google.gson.Gson;
import com.smu.model.Wallet;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * Created by WaiTuck on 17/03/2016.
 */
public class StartMiningHTTPRequest extends HTTPRequest{
    private static final String START_MINING_URL =  BASE_URL + "/methods/startMining";
    private Wallet wallet;

    public StartMiningHTTPRequest(Wallet wallet){
        this.wallet = wallet;
    }

    @Override
    public void run() {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(
                    START_MINING_URL);
            Gson gson = new Gson();
            StringEntity postBody = new StringEntity(gson.toJson(wallet));

            httpPost.setEntity(postBody);
            httpPost.setHeader("Content-type", "application/json");
            CloseableHttpResponse response = httpclient.execute(httpPost);


        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
