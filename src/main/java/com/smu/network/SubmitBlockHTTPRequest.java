package com.smu.network;

import com.google.gson.Gson;
import com.smu.model.Block;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * Created by WaiTuck on 03/03/2016.
 */
public class SubmitBlockHTTPRequest extends HTTPRequest {
    private static final String SUBMIT_BLOCK_URL = "http://127.0.0.1:3000/methods/submitBlock";
    private Block winningBlock;


    public SubmitBlockHTTPRequest(Block winningBlock){
        this.winningBlock = winningBlock;
    }

    @Override
    public void run() {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(
                    SUBMIT_BLOCK_URL);
            Gson gson = new Gson();
            StringEntity postBody = new StringEntity(gson.toJson(winningBlock));

            httpPost.setEntity(postBody);
            httpPost.setHeader("Content-type", "application/json");
            CloseableHttpResponse response = httpclient.execute(httpPost);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
