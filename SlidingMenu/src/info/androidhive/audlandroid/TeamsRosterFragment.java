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

public class TeamsRosterFragment extends Fragment {
	
	public TeamsRosterFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		ArrayList<String> player_ids = getArguments().getStringArrayList("PLAYER_IDS");
		ArrayList<String> player_names = getArguments().getStringArrayList("PLAYER_NAMES");
		
		View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		
		TextView txtView = (TextView) rootView.findViewById(R.id.list_header);
        txtView.setText("AUDL Teams");
        
        ArrayList<String> listVal = new ArrayList<String>();
        for (int i=0; i<player_names.size(); i++){
        	listVal.add(player_ids.get(i) + " " + player_names.get(i));
        }
		
		final ListView listview = (ListView) rootView.findViewById(R.id.listview);
        
        final ListAdapter adapter = new ListAdapter(this.getActivity(), android.R.layout.simple_list_item_1, listVal);        
        listview.setAdapter(adapter);

        return rootView;
    }		
}
