package com.smu.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.smu.StateStorage;
import com.smu.model.Challenge;
import com.smu.model.ChallengeFactory;
import com.smu.model.Transaction;
import com.smu.util.IOUtility;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * Created by WaiTuck on 02/03/2016.
 */
public class TransactionHTTPRequest extends HTTPRequest {
    private static final String TRANSACTION_URL = BASE_URL + "/methods/transaction";

    public void poll(){
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(
                    TRANSACTION_URL);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // do something useful
                    Gson gson = new GsonBuilder().create();
                    ContentType contentType = ContentType.getOrDefault(entity);
                    Charset charset = contentType.getCharset();
                    String jsonString = IOUtility.convertStreamToString(entity.getContent());
                    Transaction transaction = gson.fromJson(jsonString, Transaction.class);
                    handleTransaction(transaction);
                }
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            } finally {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void run() {
        poll();
    }

    private synchronized void handleTransaction(Transaction transaction){
        if(transaction == null){
            return;
        }
        //get current working tx
        Transaction currentTx = StateStorage.getInstance().getCurrentTx();
        //if null or different
        if(currentTx == null || !currentTx.equals(transaction)){
            if(currentTx != null){
                Thread currentMiningThread = StateStorage.getInstance().getMiningThread();
                if(currentMiningThread != null) {
                    currentMiningThread.interrupt();
                }
            }
            //work on new transaction
            Challenge c = ChallengeFactory.getChallenge(ChallengeFactory.ChallengeTypes.SHA256, 15, transaction);
            StateStorage.getInstance().setCurrentTx(transaction);
            Thread miningThread = new Thread(c);
            StateStorage.getInstance().setMiningThread(miningThread);
            miningThread.run();

        } else{
           return;
        }
    }


}
