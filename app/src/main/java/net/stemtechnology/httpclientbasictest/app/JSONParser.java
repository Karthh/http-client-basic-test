package net.stemtechnology.httpclientbasictest.app;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by karthik on 7/23/14.
 */
public class JSONParser implements JsonParserInterface {

    private static int FILE = 0;
    private static int NETWORK = 1;
    private int jsonSouce = NETWORK;  // 0 = FILE, 1 = NETWORK
    JSONObject jsonObject;
    JSONArray students;
    List<Student> studentList = new ArrayList<Student>();

    private List<Student> parseJsonFromFile(Context context) {
        try {

            JsonGetter jGetter = new JsonGetterFromFile();
            String jsonString = jGetter.getJson("JsonFile.json", context);
            jsonObject = new JSONObject(jsonString);
            students = jsonObject.getJSONArray("students");
            for (int i = 0; i < students.length(); i++) {
                JSONObject person = students.getJSONObject(i);
                String name = person.getString("name");
                int age = person.getInt("age");
                int grade = person.getInt("grade");
                String email = person.getString("email");
                int id = person.getInt("id");
                Student student = new Student(name, age, grade,id,email);
                studentList.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    private List<Student> parseJsonFromNetwork(Context context) {
        try {

            jsonObject = new JSONObject(new JsonGetterFromNetwork().getJson(MainActivity.STUDENTURL, context));
            students = jsonObject.getJSONArray("students");
            for (int i = 0; i < students.length(); i++) {
                JSONObject person = students.getJSONObject(i);
                String name = person.getString("name");
                int age = person.getInt("age");
                int grade = person.getInt("grade");
                String email = person.getString("email");
                int id = person.getInt("id");
                Student student = new Student(name, age,grade,id,email);
                studentList.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    @Override
    public List<Student> parseJson(Context context) {
        if (jsonSouce == FILE) // FILE
            return parseJsonFromFile(context);
        else
            return parseJsonFromNetwork(context);
    }
}
