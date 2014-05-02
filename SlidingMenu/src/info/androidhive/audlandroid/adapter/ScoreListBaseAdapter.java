package info.androidhive.audlandroid.adapter;

import info.androidhive.audlandroid.R;
import info.androidhive.audlandroid.utils.ImageLoader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import android.app.Activity;
import android.content.Context;
import android.opengl.Visibility;
import android.util.Log;
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
        return data.get(0).size();
    }
 
    public Object getItem(int position) {
        return position;
    }
 
    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View sch;
        if(convertView==null)
            sch = inflater.inflate(R.layout.score_list_item, null);
        else{
        	sch=convertView;
        }
        TextView separator = (TextView) sch.findViewById(R.id.separator);
        separator.setVisibility(View.INVISIBLE);
        TextView homeTeamName = (TextView)sch.findViewById(R.id.homeTeamName);
        TextView awayTeamName = (TextView)sch.findViewById(R.id.awayTeamName);
        TextView status = (TextView)sch.findViewById(R.id.game_status); // title
        TextView homeTeamScore = (TextView)sch.findViewById(R.id.homeTeamScore);
        TextView awayTeamScore = (TextView)sch.findViewById(R.id.awayTeamScore);
        TextView gameTime = (TextView)sch.findViewById(R.id.game_time);
        ImageView homeTeamImage=(ImageView)sch.findViewById(R.id.hTeamIcon); // home team image
        ImageView awayTeamImage=(ImageView)sch.findViewById(R.id.aTeamIcon); // away team image
        // Code to convert to local time
        DateTimeFormatter parser = ISODateTimeFormat.dateTimeNoMillis();
        String time = data.get(9).get(position);
        DateFormat formatter = new SimpleDateFormat("hh:mm a");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(parser.parseDateTime(time).getMillis());
        String timeString = formatter.format(calendar.getTime());
        
        if(position == 0){
        	separator.setVisibility(View.VISIBLE);
        	separator.setText(data.get(4).get(position));
        }
        else{
        	if(data.get(4).get(position).compareTo(data.get(4).get(position-1)) != 0){
        			separator.setVisibility(View.VISIBLE);
        			separator.setText(data.get(4).get(position));
        	}
        }
        homeTeamName.setText(data.get(0).get(position));
        awayTeamName.setText(data.get(2).get(position));
        if(data.get(8).get(position).compareTo("0")!=0){
        	homeTeamScore.setText(data.get(6).get(position));
        	awayTeamScore.setText(data.get(7).get(position));
        }
        else{
        	homeTeamScore.setText("");
        	awayTeamScore.setText("");
        }
        gameTime.setText(timeString);
        String gameStatus="";
        if(data.get(8).get(position).compareTo("0")==0){
        	gameStatus="Upcoming";
        }else if(data.get(8).get(position).compareTo("1")==0){
        	gameStatus="Ongoing";
        }else{
        	gameStatus="Final";
        }
        status.setText("" + gameStatus);
        imageLoader.DisplayImage("http://ec2-54-186-184-48.us-west-2.compute.amazonaws.com:4000/Icons/" + data.get(1).get(position), homeTeamImage);
        imageLoader.DisplayImage("http://ec2-54-186-184-48.us-west-2.compute.amazonaws.com:4000/Icons/" + data.get(3).get(position), awayTeamImage);        
        return sch;
    }
}
