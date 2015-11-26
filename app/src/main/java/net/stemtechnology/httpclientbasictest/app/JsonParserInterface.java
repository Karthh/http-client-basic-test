package net.stemtechnology.httpclientbasictest.app;

import android.content.Context;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by karthik on 7/23/14.
 */
public interface JsonParserInterface {
    List<Student> parseJson(Context context);
}
