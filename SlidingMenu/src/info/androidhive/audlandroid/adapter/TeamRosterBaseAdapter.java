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

public class TeamRosterBaseAdapter extends BaseAdapter{
	private Activity activity;
    private ArrayList<String> player_ids;
    private ArrayList<String> player_names;
    private LayoutInflater inflater=null;
    
    public TeamRosterBaseAdapter(Activity a, ArrayList<String> ids, ArrayList<String> names) {
        activity = a;
        player_ids=ids;
        player_names=names;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
    public int getCount() {
        return player_ids.size();
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
            vi = inflater.inflate(R.layout.team_roster, null);
 
        TextView id = (TextView)vi.findViewById(R.id.player_id); // title
        TextView name =(TextView)vi.findViewById(R.id.player_name); // thumb image
 
        // Setting all values in listview
        id.setText(player_ids.get(position));
        name.setText(player_names.get(position));
        return vi;
    }

}
