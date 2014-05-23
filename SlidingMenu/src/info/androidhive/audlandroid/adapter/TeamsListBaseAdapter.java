package info.androidhive.audlandroid.adapter;

import info.androidhive.audlandroid.R;
import info.androidhive.audlandroid.model.TeamsListItem;
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

public class TeamsListBaseAdapter extends BaseAdapter {
	private Activity activity;
    private ArrayList<TeamsListItem> data;
    private LayoutInflater inflater=null;
    public ImageLoader imageLoader;
    String serverURL;
    
    public TeamsListBaseAdapter(Activity a, ArrayList<TeamsListItem> d) {
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
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.thumbnail_text_item, null);
 
        TextView title = (TextView)vi.findViewById(R.id.name); // title
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.icon); // thumb image
 
        TeamsListItem team = data.get(position);
 
        // Setting all values in listview
        title.setText(team.getTeamName());
        imageLoader.DisplayImage(serverURL + "/Icons/" + team.getTeamId(), thumb_image);
        return vi;
    }
}
