package info.androidhive.audlandroid.adapter;

import info.androidhive.audlandroid.R;
import info.androidhive.audlandroid.utils.ImageLoader;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ScoreListBaseAdapter extends BaseAdapter{
	private Activity activity;
    private ArrayList<ArrayList<String>> data;
    private LayoutInflater inflater=null;
    public ImageLoader imageLoader;
    
    public ScoreListBaseAdapter(Activity a, ArrayList<ArrayList<String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }
    
    public int getCount() {
        return data.size();
    }
 
    public Object getItem(int position) {
        return position;
    }
 
    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View sch =convertView;
        if(convertView==null)
            sch = inflater.inflate(R.layout.score_list_item, null);
 
        TextView title = (TextView)sch.findViewById(R.id.item_score); // title
        ImageView homeTeamImage=(ImageView)sch.findViewById(R.id.hTeamIcon); // home team image
        ImageView awayTeamImage=(ImageView)sch.findViewById(R.id.aTeamIcon); // away team image
 
        // Setting all values in listview
        title.setText(data.get(6).get(position) + "\n" + data.get(0).get(position) + "\nvs.\n" + data.get(2).get(position) + "\n" + data.get(5).get(position) + " on " + data.get(4).get(position));
      
        imageLoader.DisplayImage("http://ec2-54-186-184-48.us-west-2.compute.amazonaws.com:4000/Icons/" + data.get(1).get(position), homeTeamImage);
        imageLoader.DisplayImage("http://ec2-54-186-184-48.us-west-2.compute.amazonaws.com:4000/Icons/" + data.get(3).get(position), awayTeamImage);        
        return sch;
    }
}
