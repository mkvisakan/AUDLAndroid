package info.androidhive.audlandroid.adapter;

import info.androidhive.audlandroid.R;
import info.androidhive.audlandroid.model.StatsListItem;
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

public class StatsListBaseAdapter extends BaseAdapter {
	private Activity activity;
    private ArrayList<StatsListItem> data;
    private LayoutInflater inflater=null;
    public ImageLoader imageLoader;
    
    public StatsListBaseAdapter(Activity a, ArrayList<StatsListItem> d) {
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
        View stat =convertView;
        if(convertView==null)
            stat = inflater.inflate(R.layout.thumbnail_text_item, null);
 
        TextView title = (TextView)stat.findViewById(R.id.name); // title
        ImageView playerTeamImage =(ImageView)stat.findViewById(R.id.icon); // Player's Team Icon
        
        StatsListItem statistic = data.get(position);
 
        // Setting all values in listview
        title.setText(statistic.getPlayerName() + " - " + statistic.getStatValue());
        
        
        imageLoader.DisplayImage("http://ec2-54-186-184-48.us-west-2.compute.amazonaws.com:4000/Icons/" + statistic.getPlayerTeamID(), playerTeamImage);
        
        
        return stat;
    }
}