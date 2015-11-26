package net.stemtechnology.httpclientbasictest.app;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

/**
 * Created by karthik on 8/19/14.
 */
public class PostTask extends AsyncTask<Void, Void, Void> {
    private String url;
    private JSONObject json;
    public PostTask(String url, JSONObject json){
        this.url = url;
        this.json = json;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            StringEntity se = new StringEntity(json.toString());
            httpPost.setEntity(se);
            HttpResponse httpresponse = httpClient.execute(httpPost);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
