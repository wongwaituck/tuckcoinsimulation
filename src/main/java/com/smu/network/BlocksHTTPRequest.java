package com.smu.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.smu.StateStorage;
import com.smu.model.Block;
import com.smu.model.BlockChain;
import com.smu.model.Transaction;
import com.smu.util.IOUtility;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WaiTuck on 02/03/2016.
 */
public class BlocksHTTPRequest extends HTTPRequest {
    private static final String BLOCKCHAIN_URL = BASE_URL + "/methods/blocks";

    @Override
    public void run() {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(
                    BLOCKCHAIN_URL);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // do something useful
                    Gson gson = new GsonBuilder().create();
                    ContentType contentType = ContentType.getOrDefault(entity);
                    Charset charset = contentType.getCharset();
                    String jsonString = IOUtility.convertStreamToString(entity.getContent());
                    Type listType = new TypeToken<ArrayList<Block>>() {
                    }.getType();
                    List<Block> blocks = gson.fromJson(jsonString, listType);
                    StateStorage.getInstance().setBlocks(blocks);
                    BlockChain.parseBlocks();
                }
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            } finally {
                response.close();
            }
        } catch (IOException e) {
           // e.printStackTrace();
        }

    }
}
