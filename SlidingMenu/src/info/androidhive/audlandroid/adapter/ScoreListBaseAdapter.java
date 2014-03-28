package info.androidhive.audlandroid.adapter;

import info.androidhive.audlandroid.R;
import info.androidhive.audlandroid.model.ScoreListItem;
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
    private ArrayList<ScoreListItem> data;
    private LayoutInflater inflater=null;
    public ImageLoader imageLoader;
    
    public ScoreListBaseAdapter(Activity a, ArrayList<ScoreListItem> d) {
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
        
        ScoreListItem scoreItem = data.get(position);
 
        // Setting all values in listview
        title.setText(scoreItem.getScore() + "\n" + scoreItem.getHomeTeam() + "\nvs.\n" + scoreItem.getAwayTeam() + "\n" + scoreItem.getTime() + " on " + scoreItem.getDate());
        
        
        imageLoader.DisplayImage("http://ec2-54-186-184-48.us-west-2.compute.amazonaws.com:4000/Icons/" + scoreItem.getHomeTeamID(), homeTeamImage);
        imageLoader.DisplayImage("http://ec2-54-186-184-48.us-west-2.compute.amazonaws.com:4000/Icons/" + scoreItem.getAwayTeamID(), awayTeamImage);
        
        return sch;
    }
}
