package info.androidhive.audlandroid.adapter;

import info.androidhive.audlandroid.R;
import info.androidhive.audlandroid.model.ScheduleListItem;
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

public class ScheduleListBaseAdapter extends BaseAdapter {
	private Activity activity;
    private ArrayList<ScheduleListItem> data;
    private LayoutInflater inflater=null;
    public ImageLoader imageLoader;
    String serverURL;
    
    public ScheduleListBaseAdapter(Activity a, ArrayList<ScheduleListItem> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
        serverURL = a.getResources().getString(R.string.ServerURL);
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
            sch = inflater.inflate(R.layout.schedule_list_item, null);
 
        TextView title = (TextView)sch.findViewById(R.id.scheduleEntryText); // title
        ImageView homeTeamImage=(ImageView)sch.findViewById(R.id.homeTeamIcon); // home team image
        ImageView awayTeamImage=(ImageView)sch.findViewById(R.id.awayTeamIcon); // away team image
        
        ScheduleListItem scheduleEntry = data.get(position);
 
        // Setting all values in listview
        title.setText(scheduleEntry.getHomeTeam() + "\nvs.\n" + scheduleEntry.getAwayTeam() + "\n" + scheduleEntry.getTime() + " on " + scheduleEntry.getDate());
       
        
        imageLoader.DisplayImage(serverURL + "/Icons/" + scheduleEntry.getHomeTeamID(), homeTeamImage);
        imageLoader.DisplayImage(serverURL + "/Icons/" + scheduleEntry.getAwayTeamID(), awayTeamImage);
        
        
        return sch;
    }
}