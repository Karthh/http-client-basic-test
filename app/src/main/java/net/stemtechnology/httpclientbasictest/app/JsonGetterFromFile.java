package net.stemtechnology.httpclientbasictest.app;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by karthik on 7/23/14.
 */
public class JsonGetterFromFile implements  JsonGetter {


    static String json = "";

    Context context;

    public String getJson(String fileName,Context context) {
        try {
            AssetManager am = context.getAssets();
            InputStream is = am.open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            json = sb.toString();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}
