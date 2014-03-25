package info.androidhive.audlandroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import info.androidhive.audlandroid.R;
import info.androidhive.audlandroid.adapter.ListAdapter;
import info.androidhive.audlandroid.adapter.TeamsListBaseAdapter;
import info.androidhive.audlandroid.model.TeamsListItem;
import info.androidhive.audlandroid.utils.ImageLoader;


import org.json.JSONArray;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Context;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TeamsScheduleFragment extends Fragment {
	
	public TeamsScheduleFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		String team_name = getArguments().getString("TEAM_NAME");
		ArrayList<String> sched_teams = getArguments().getStringArrayList("SCHED_TEAMS");
		ArrayList<String> sched_datetimes = getArguments().getStringArrayList("SCHED_DATETIMES");
		
		View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		
		TextView txtView = (TextView) rootView.findViewById(R.id.list_header);
        txtView.setText(team_name + " Schedule");
        
        ArrayList<String> listVal = new ArrayList<String>();
        for (int i=0; i<sched_teams.size(); i++){
        	listVal.add("Vs  " + sched_teams.get(i) + " (" + sched_datetimes.get(i) + ")");
        }
		
		final ListView listview = (ListView) rootView.findViewById(R.id.listview);
        
        final ListAdapter adapter = new ListAdapter(this.getActivity(), android.R.layout.simple_list_item_1, listVal);        
        listview.setAdapter(adapter);

        return rootView;
    }		
}
