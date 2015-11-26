package net.stemtechnology.httpclientbasictest.app;

import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by karthik on 7/23/14.
 */
public class JsonGetterFromNetwork implements JsonGetter{
    static JSONObject jObj = null;
    static String json = "";
    InputStream is;


    private String fetchFromSever(String whereToGetJsonFrom) {
        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpResponse response = httpClient.execute(new HttpGet(whereToGetJsonFrom));
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            json = sb.toString();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public String getJson(String whereToGetJsonFrom, Context context) {
        return fetchFromSever(whereToGetJsonFrom);
    }
}

