package info.androidhive.audlandroid.adapter;

import info.androidhive.audlandroid.R;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;

public class SettingsListBaseAdapter extends BaseAdapter {
	private Activity activity;
    private ArrayList<String> data;
    private LayoutInflater inflater=null;
    
    public SettingsListBaseAdapter(Activity a, ArrayList<String> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        
        if(data.get(position).equals("Notifications")){
        	
        	if(convertView==null)
                vi = inflater.inflate(R.layout.switch_item, null);
     
            TextView name = (TextView)vi.findViewById(R.id.name); // title
     
            name.setText(data.get(position));
            
        	Switch onOffSwitch = (Switch)vi.findViewById(R.id.on_off_switch);
        	final SharedPreferences sharedPref = activity.getSharedPreferences(activity.getResources().getString(R.string.notifications_on_saved), Context.MODE_PRIVATE);
        	boolean currentValue = sharedPref.getBoolean(activity.getResources().getString(R.string.notifications_on_saved), false);
        	onOffSwitch.setChecked(currentValue);
        	onOffSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

        	@Override
        	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        	    Log.v("Switch State=", ""+isChecked);
        	    SharedPreferences.Editor editor = sharedPref.edit();
        	    editor.putBoolean(activity.getResources().getString(R.string.notifications_on_saved), isChecked);
        	    editor.commit();
        	}       

        	});
        	return vi;
        }
        
        
        if(convertView==null)
            vi = inflater.inflate(R.layout.simple_list_item, null);
 
        TextView name = (TextView)vi.findViewById(R.id.name); // title
 
        name.setText(data.get(position));
        return vi;
    }
}