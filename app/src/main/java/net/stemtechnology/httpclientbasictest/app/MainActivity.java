package net.stemtechnology.httpclientbasictest.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends ActionBarActivity implements DataSink {

    public static final String STUDENTURL = "http://192.168.1.115/html/students.php/";
    public boolean allowRefresh = true;
    public View.OnTouchListener gestureListener;
    public int id;
    public int colorId = 0;
     View previousTap;
    View currentTap;
    public RefreshAllower ref = new RefreshAllower();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout linLayout = (RelativeLayout)findViewById(R.id.ActivityLayout);
        beginDataRefresh();
        Button buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new Button.OnClickListener() {
           public void onClick(View v) {
               RelativeLayout layout = (RelativeLayout)findViewById(R.id.button_layout);
               new DeleteTask(STUDENTURL + currentTap.getId()).execute();
               new DataFetcher(MainActivity.this, MainActivity.this,ref).execute();
               layout.setVisibility(View.INVISIBLE);

            }
        });
        Button buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(new Button.OnClickListener() {
            RelativeLayout layout = (RelativeLayout)findViewById(R.id.button_layout);
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FormActivityPut.class);
                intent.putExtra("url",STUDENTURL+currentTap.getId());
                startActivity(intent);
                new DataFetcher(MainActivity.this, MainActivity.this,ref).execute();
                layout.setVisibility(View.INVISIBLE);

            }
        });
        linLayout.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(view.getId() == R.id.ActivityLayout){
                    RelativeLayout layout = (RelativeLayout)findViewById(R.id.button_layout);
                    if(layout.getVisibility()==View.VISIBLE){
                            if (currentTap != null) {
                                colorId = 0;
                                previousTap.setBackgroundColor(Color.WHITE);
                                ref.setRefresh(true);
                                layout.setVisibility(View.INVISIBLE);

                        }
                    }
                }
                return false;
            }
        });
        final MainActivity aDataSink = this;
        final Context context = this;
       // if(ref.getRefreshValue() == true) {
       // }


        gestureListener = new View.OnTouchListener() {
            int action_down_x = 0;
            int action_up_x = 0;
            int difference = 0;
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                try {
                    //view.setBackgroundColor(0xFFFFFFFF);
                    //return true;
                    int action = event.getAction();
                    //int position = (Integer) view.getTag();
                    switch (action) {
                        case MotionEvent.ACTION_DOWN:
                            if(currentTap!=null) {
                                previousTap = currentTap;
                            }
                            currentTap = view;
                            if(previousTap == null){
                                previousTap = currentTap;
                            }
                            id = view.getId();
                            System.out.println(view);
                            ref.setRefresh(false);
                           // changeRefreshAllowerValue();
                            System.out.println((view.getId()));
                            action_down_x = (int) event.getX();
                            Log.d("action", "ACTION_DOWN - ");
                            break;
                        case MotionEvent.ACTION_MOVE:
                            Log.d("action", "ACTION_MOVE - ");
                            action_up_x = (int) event.getX();
                            difference = action_down_x - action_up_x;
                            break;
                        case MotionEvent.ACTION_UP:
                            Log.d("action", "ACTION_UP - ");
                            if (difference == 0) {
                                RelativeLayout layout = (RelativeLayout)findViewById(R.id.button_layout);
                                View root = view.getRootView();

                                if(colorId == 1 && previousTap == currentTap){
                                    view.setBackgroundColor(Color.WHITE);
                                    colorId = 0;
                                }else{
                                    view.setBackgroundColor(Color.CYAN);
                                    colorId = 1;
                                }
                                if(layout.getVisibility() == View.INVISIBLE ){
                                    layout.setVisibility(View.VISIBLE);

                                }else{
                                    if(previousTap == currentTap) {
                                        layout.setVisibility(View.INVISIBLE);
                                        ref.setRefresh(true);
                                    }

                                    //changeRefreshAllowerValue();

                                }
                               // if(linLayout.getBackground() == "ff000000")
                            }
                            if (difference < -75) {
                                view.setVisibility(View.GONE);
                                System.out.println("Deleting on Up");
                                try {
                                    new DeleteTask(STUDENTURL + id).execute();
                                    new DataFetcher(MainActivity.this,MainActivity.this,ref).execute();
                                    ref.setRefresh(true);
                                    //changeRefreshAllowerValue();
                                }catch(Exception e){
                                    e.printStackTrace();
                                }

                            }


                            action_down_x = 0;
                            action_up_x = 0;
                            difference = 0;

                            break;
                        case   MotionEvent.ACTION_CANCEL:
                            Log.d("action", "ACTION_CANCEL - ");
                            if (difference == 0) {
                                RelativeLayout layout = (RelativeLayout)findViewById(R.id.button_layout);


                                if(colorId == 1 && previousTap == currentTap){

                                    view.setBackgroundColor(Color.WHITE);
                                    colorId = 0;
                                }else{
                                    view.setBackgroundColor(Color.CYAN);
                                    colorId = 1;
                                }
                                if(layout.getVisibility() == View.INVISIBLE ){
                                    layout.setVisibility(View.VISIBLE);

                                }else{
                                    if(previousTap == currentTap) {
                                        layout.setVisibility(View.INVISIBLE);
                                        ref.setRefresh(true);
                                    }

                                    //changeRefreshAllowerValue();

                                }
                                // if(linLayout.getBackground() == "ff000000")
                            }
                            if (difference < -75) {
                                view.setVisibility(View.GONE);
                                System.out.println("Deleting on Cancel");
                                try {
                                    new DeleteTask(STUDENTURL + id).execute();
                                    new DataFetcher(MainActivity.this,MainActivity.this,ref).execute();
                                   // changeRefreshAllowerValue();
                                    ref.setRefresh(true);
                                }catch(Exception e){
                                    e.printStackTrace();
                                }

                            }


                            action_down_x = 0;
                            action_up_x = 0;
                            difference = 0;

                            break;
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                return true;
            }

        };
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_add:
                //Toast.makeText(getApplicationContext(), "Add Button Pressed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,FormActivity.class);
                startActivity(intent);
           default:return super.onOptionsItemSelected(item);

        }

    }

    @Override
    public void takeData(List<Student> studentList) {
        ListView list = (ListView) findViewById(R.id.list);
        ArrayList<HashMap<String, String>> myList = new ArrayList<HashMap<String, String>>();

        for(int i=0; i<studentList.size();i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("name", studentList.get(i).getName());
            map.put("age", String.valueOf(studentList.get(i).getAge()));
            map.put("grade",String.valueOf(studentList.get(i).getGrade()));
            map.put("id", String.valueOf(studentList.get(i).getId()));
            map.put("email", studentList.get(i).getEmail());
            myList.add(map);
        }
// ...
        StudentAdapter adapter = new StudentAdapter(this, myList, R.layout.row_layout,
                new String[]{"name", "age","grade","email"}, new int[]{R.id.name, R.id.age, R.id.grade, R.id.email}, gestureListener);
        list.setAdapter(adapter);

    }

    private void beginDataRefresh() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                new DataFetcher(MainActivity.this, MainActivity.this,ref).execute();
                String value = Boolean.toString(ref.getRefreshValue());
                Log.d("value",value);
               // while(ref.getRefreshValue() == true) {
                        handler.postDelayed(this, 2000);
                    }
               // }

        }, 2000);
    }

    public void changeRefreshAllowerValue(){
        if(allowRefresh == true){
            allowRefresh = false;
        }else{
            allowRefresh = true;
        }

    }
}
