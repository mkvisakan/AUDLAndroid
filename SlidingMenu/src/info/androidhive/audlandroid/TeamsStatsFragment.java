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

public class TeamsStatsFragment extends Fragment {
	
	public TeamsStatsFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		ArrayList<String> goalPlayers = getArguments().getStringArrayList("GOAL_PLAYERS");
		ArrayList<String> goals = getArguments().getStringArrayList("GOALS");
		ArrayList<String> assistPlayers = getArguments().getStringArrayList("ASSIST_PLAYERS");
		ArrayList<String> assists = getArguments().getStringArrayList("ASSISTS");
		ArrayList<String> dropPlayers = getArguments().getStringArrayList("DROP_PLAYERS");
		ArrayList<String> drops = getArguments().getStringArrayList("DROPS");
		ArrayList<String> throwAwayPlayers = getArguments().getStringArrayList("THROWAWAY_PLAYERS");
		ArrayList<String> throwAways = getArguments().getStringArrayList("THROWAWAYS");
		ArrayList<String> pmcPlayers = getArguments().getStringArrayList("PMC_PLAYERS");
		ArrayList<String> pmc = getArguments().getStringArrayList("PMC");
		ArrayList<String> dsPlayers = getArguments().getStringArrayList("DS_PLAYERS");
		ArrayList<String> ds = getArguments().getStringArrayList("DS");
		
		View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		
		TextView txtView = (TextView) rootView.findViewById(R.id.list_header);
        txtView.setText("Team Stats");
        
        ArrayList<String> listVal = new ArrayList<String>();
        for (int i=0; i<goalPlayers.size(); i++){
        	listVal.add(goalPlayers.get(i) + " - " + goals.get(i) + " goals");
        }
        
        for (int i=0; i<assistPlayers.size(); i++){
        	listVal.add(assistPlayers.get(i) + " - " + assists.get(i) + " assists");
        }
        
        for (int i=0; i<dropPlayers.size(); i++){
        	listVal.add(dropPlayers.get(i) + " - " + drops.get(i) + " drops");
        }
        
        for (int i=0; i<throwAwayPlayers.size(); i++){
        	listVal.add(throwAwayPlayers.get(i) + " - " + throwAways.get(i) + " throw aways");
        }
        
        for (int i=0; i<pmcPlayers.size(); i++){
        	listVal.add(pmcPlayers.get(i) + " - " + pmc.get(i) + " +/- counts");
        }
        
        for (int i=0; i<dsPlayers.size(); i++){
        	listVal.add(dsPlayers.get(i) + " - " + ds.get(i) + " D's");
        }
		
		final ListView listview = (ListView) rootView.findViewById(R.id.listview);
        
        final ListAdapter adapter = new ListAdapter(this.getActivity(), android.R.layout.simple_list_item_1, listVal);        
        listview.setAdapter(adapter);

        return rootView;
    }		
}

