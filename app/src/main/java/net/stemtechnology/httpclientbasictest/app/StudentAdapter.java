package net.stemtechnology.httpclientbasictest.app;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by karthik on 8/5/14.
 */
public class StudentAdapter extends SimpleAdapter{
    private View.OnTouchListener listener;
    public StudentAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to, View.OnTouchListener _listener) {
        super(context, data, resource, from, to);
        this.listener = _listener;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = super.getView(position,convertView,parent);
        HashMap<String,String> map = (HashMap)getItem(position);
        if(map!= null)
        view.setId(Integer.parseInt(map.get("id")));
        if(this.listener != null){
            view.setOnTouchListener(this.listener);
        }
        return view;
    }
}
