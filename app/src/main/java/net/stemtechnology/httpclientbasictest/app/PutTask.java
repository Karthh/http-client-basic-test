package net.stemtechnology.httpclientbasictest.app;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

/**
 * Created by karthik on 8/24/14.
 */
public class PutTask extends AsyncTask<Void, Void, Void> {
    private String url;
    private JSONObject json;
    public PutTask(String url, JSONObject json){
        this.url = url;
        this.json = json;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            HttpClient httpClient = new DefaultHttpClient();
            HttpPut put = new HttpPut(url);
            StringEntity se = new StringEntity(json.toString());
            Log.w("url", url);
            Log.w("json",json.toString());
            put.setEntity(se);
            HttpResponse httpresponse = httpClient.execute(put);

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
