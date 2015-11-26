package net.stemtechnology.httpclientbasictest.app;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.List;

/**
 * Created by karthik on 8/14/14.
 */
public class DeleteTask extends AsyncTask<Void, Void, Void> {
    private String url;

    public DeleteTask(String url) {
        this.url = url;

    }
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            HttpClient httpClient = new DefaultHttpClient();
            Log.d("url", url);
            httpClient.execute(new HttpDelete(url));

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
