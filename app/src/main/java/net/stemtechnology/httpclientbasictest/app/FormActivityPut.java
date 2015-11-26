package net.stemtechnology.httpclientbasictest.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;


public class FormActivityPut extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_layout);
        final Button button = (Button) findViewById(R.id.button);
        button.setText("Update");
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(createJson() != null) {
                    String json = createJson().toString();
                    String url = getIntent().getStringExtra("url");
                    new PutTask(url, createJson()).execute();
                    Intent intent = new Intent(FormActivityPut.this, MainActivity.class);
                    startActivity(intent);
                }

                }

        });
    }

    public JSONObject createJson(){
        try {
            JSONObject jsonObject = new JSONObject();
            EditText nameView = (EditText) findViewById(R.id.editText2);
            EditText ageView = (EditText) findViewById(R.id.editText);
            EditText gradeView = (EditText) findViewById(R.id.editText3);
            EditText emailView = (EditText) findViewById(R.id.editText4);
            if (nameView.getText().length() != 0 && ageView.getText().length() != 0 && gradeView.getText().length() != 0 && emailView.getText().length() != 0) {
                jsonObject.put("name", nameView.getText());
                jsonObject.put("age", ageView.getText());
                jsonObject.put("grade", gradeView.getText());
                jsonObject.put("email", emailView.getText());
                return jsonObject;
            }else{
                Toast.makeText(getApplicationContext(), "You Must Fill Out All Fields Before Adding To The List!", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.form_activity_put, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
