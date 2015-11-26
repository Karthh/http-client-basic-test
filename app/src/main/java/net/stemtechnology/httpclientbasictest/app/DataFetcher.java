package net.stemtechnology.httpclientbasictest.app;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by karthik on 7/27/14.
 */
public class DataFetcher extends AsyncTask<Void, Void, List<Student>>{

    private JSONParser parser;
    private Context appContext;
    private DataSink dataSink;
    private RefreshAllower ref;

    public DataFetcher(Context context, DataSink aDataSink, RefreshAllower ref) {
        appContext = context;
        dataSink = aDataSink;
        this.ref = ref;
    }

    @Override
    protected List<Student> doInBackground(Void... Void) {

            parser = new JSONParser();
            List<Student> students = parser.parseJson(appContext);
            return students;

    }


    protected void onPostExecute(List<Student> students) {
       // while(ref.getRefreshValue()==true) {
            MainActivity main = new MainActivity();
            super.onPostExecute(students);
            dataSink.takeData(students);
        //}
    }
}
