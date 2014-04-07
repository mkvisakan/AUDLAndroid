package info.androidhive.audlandroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import info.androidhive.audlandroid.R;
import info.androidhive.audlandroid.adapter.ExpandableListAdapter;
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
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TeamsStatsFragment extends Fragment {
	
	public TeamsStatsFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		String team_name = getArguments().getString("TEAM_NAME");
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
		
		Log.i("TeamStatsInfo", "assists : " + assistPlayers);
		
		View rootView = inflater.inflate(R.layout.team_stats_expandable, container, false);
		
		ArrayList<String> listDataHeader = new ArrayList<String>();
		HashMap<String, List<String>> listDataChild = new HashMap<String, List<String>>();
		
		listDataHeader.add("Goals");
		listDataHeader.add("Assists");
		listDataHeader.add("Drops");
		listDataHeader.add("Throwaways");
		listDataHeader.add("+/- Counts");
		listDataHeader.add("D's");
		
		List<String> goalsData = new ArrayList<String>();
		for (int i=0; i<goalPlayers.size(); i++){
			goalsData.add(goalPlayers.get(i)+ " : " + goals.get(i));
		}
		
		List<String> assistData = new ArrayList<String>();
		for (int i=0; i<assistPlayers.size(); i++){
        	assistData.add(assistPlayers.get(i) + " - " + assists.get(i));
        }
        
		List<String> dropData = new ArrayList<String>();
        for (int i=0; i<dropPlayers.size(); i++){
        	dropData.add(dropPlayers.get(i) + " - " + drops.get(i));
        }
        
        List<String> throwAwayData = new ArrayList<String>();
        for (int i=0; i<throwAwayPlayers.size(); i++){
        	throwAwayData.add(throwAwayPlayers.get(i) + " - " + throwAways.get(i));
        }
        
        List<String> pmcData = new ArrayList<String>();
        for (int i=0; i<pmcPlayers.size(); i++){
        	pmcData.add(pmcPlayers.get(i) + " - " + pmc.get(i));
        }
        
        List<String> dsData = new ArrayList<String>();
        for (int i=0; i<dsPlayers.size(); i++){
        	dsData.add(dsPlayers.get(i) + " - " + ds.get(i));
        }
		
        listDataChild.put(listDataHeader.get(0), goalsData);
        listDataChild.put(listDataHeader.get(1), assistData);
        listDataChild.put(listDataHeader.get(2), dropData);
        listDataChild.put(listDataHeader.get(3), throwAwayData);
        listDataChild.put(listDataHeader.get(4), pmcData);
        listDataChild.put(listDataHeader.get(5), dsData);
        
        ExpandableListAdapter listAdapter = new ExpandableListAdapter(getActivity().getApplicationContext(), listDataHeader, listDataChild);
        
        ExpandableListView expListView = (ExpandableListView) rootView.findViewById(R.id.lvExp);
        // setting list adapter
        expListView.setAdapter(listAdapter);
        
        
		
		/**
		//goals
		TextView gTxtView = (TextView) rootView.findViewById(R.id.goals_list_header);
        gTxtView.setText("Goals :");
        ArrayList<String> gListVal = new ArrayList<String>();
        for (int i=0; i<goalPlayers.size(); i++){
        	gListVal.add(goalPlayers.get(i) + " - " + goals.get(i));
        }
        final ListView gListview = (ListView) rootView.findViewById(R.id.goals_listview);
        
        final ListAdapter gAdapter = new ListAdapter(this.getActivity(), android.R.layout.simple_list_item_1, gListVal);        
        gListview.setAdapter(gAdapter);
        
        //assists
        TextView aTxtView = (TextView) rootView.findViewById(R.id.assists_list_header);
        aTxtView.setText("Assists :");
        
        ArrayList<String> aListVal = new ArrayList<String>();
        for (int i=0; i<assistPlayers.size(); i++){
        	aListVal.add(assistPlayers.get(i) + " - " + assists.get(i));
        }
        final ListView aListview = (ListView) rootView.findViewById(R.id.assists_listview);
        
        final ListAdapter aAdapter = new ListAdapter(this.getActivity(), android.R.layout.simple_list_item_1, aListVal);        
        aListview.setAdapter(aAdapter);
        
        //drops
        TextView dTxtView = (TextView) rootView.findViewById(R.id.drops_list_header);
        dTxtView.setText("Drops :");
        
        ArrayList<String> dListVal = new ArrayList<String>();
        for (int i=0; i<dropPlayers.size(); i++){
        	dListVal.add(dropPlayers.get(i) + " - " + drops.get(i));
        }
        final ListView dListview = (ListView) rootView.findViewById(R.id.drops_listview);
        
        final ListAdapter dAdapter = new ListAdapter(this.getActivity(), android.R.layout.simple_list_item_1, dListVal);        
        dListview.setAdapter(dAdapter);
		
        TextView tTxtView = (TextView) rootView.findViewById(R.id.throwaways_list_header);
        tTxtView.setText("Throwaways :");
        
        //throwaways
        ArrayList<String> tListVal = new ArrayList<String>();
        for (int i=0; i<throwAwayPlayers.size(); i++){
        	tListVal.add(throwAwayPlayers.get(i) + " - " + throwAways.get(i));
        }
        final ListView tListview = (ListView) rootView.findViewById(R.id.throwaways_listview);
        
        final ListAdapter tAdapter = new ListAdapter(this.getActivity(), android.R.layout.simple_list_item_1, tListVal);        
        tListview.setAdapter(tAdapter);
        
        //pmc
        TextView pTxtView = (TextView) rootView.findViewById(R.id.pmc_list_header);
        pTxtView.setText("+/- Counts :");
        
        ArrayList<String> pListVal = new ArrayList<String>();
        for (int i=0; i<pmcPlayers.size(); i++){
        	pListVal.add(pmcPlayers.get(i) + " - " + pmc.get(i));
        }
        final ListView pListview = (ListView) rootView.findViewById(R.id.pmc_listview);
        
        final ListAdapter pAdapter = new ListAdapter(this.getActivity(), android.R.layout.simple_list_item_1, aListVal);        
        pListview.setAdapter(pAdapter);
        
        //ds
        TextView dsTxtView = (TextView) rootView.findViewById(R.id.ds_list_header);
        dsTxtView.setText("D's :");
        
        ArrayList<String> dsListVal = new ArrayList<String>();
        for (int i=0; i<dsPlayers.size(); i++){
        	dsListVal.add(dsPlayers.get(i) + " - " + ds.get(i));
        }
        final ListView dsListview = (ListView) rootView.findViewById(R.id.ds_listview);
        
        final ListAdapter dsAdapter = new ListAdapter(this.getActivity(), android.R.layout.simple_list_item_1, dsListVal);        
        dsListview.setAdapter(dsAdapter);
		
		View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		
		TextView txtView = (TextView) rootView.findViewById(R.id.list_header);
        txtView.setText(team_name + " Stats");
        
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
        **/

        return rootView;
    }		
}

