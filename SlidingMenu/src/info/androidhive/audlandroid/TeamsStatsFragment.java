package info.androidhive.audlandroid;

import info.androidhive.audlandroid.adapter.SettingsListBaseAdapter;

import java.util.ArrayList;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


public class TeamsStatsFragment extends Fragment {
	
	public TeamsStatsFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		final String team_name = getArguments().getString("TEAM_NAME");
		final String team_id = getArguments().getString("TEAM_ID");
		//Arjun: This is all being commented out to disable team stats because they are not accurate, according to server team
		
		/*ArrayList<String> listDataHeader = getArguments().getStringArrayList("STAT_KEYS");
		HashMap<String, List<String>> listDataPlayers = new HashMap<String, List<String>>();
		HashMap<String, List<String>> listDataVal = new HashMap<String, List<String>>();
		
		for (String key : listDataHeader) {
			ArrayList<StatsListItem> statData = getArguments().getParcelableArrayList(key);
			ArrayList<String> playersList = new ArrayList<String>();
			ArrayList<String> statsList = new ArrayList<String>();
			for (int i=0; i<statData.size(); i++){
				playersList.add(statData.get(i).getPlayerName());
				statsList.add(statData.get(i).getStatValue());
			}
			listDataPlayers.put(key, playersList);
			listDataVal.put(key, statsList);
		}
		
		View rootView = inflater.inflate(R.layout.team_stats_expandable, container, false);
		
        ExpandableListAdapter listAdapter = new ExpandableListAdapter(getActivity().getApplicationContext(), listDataHeader, listDataPlayers, listDataVal);
        
        ExpandableListView expListView = (ExpandableListView) rootView.findViewById(R.id.lvExp);
        // setting list adapter
        expListView.setAdapter(listAdapter);
        
        Button mButton = (Button) rootView.findViewById(R.id.more);
        mButton.setText("More " + team_name + " Stats...");*/
		/*Arjun: I am using a listview entry instead of a button to implement the More team stats thing because it makes more sense
		  when the expandable view isn't there*/
		View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        		
        final ListView listview = (ListView) rootView.findViewById(R.id.listview);
        ArrayList<String> teamStats = new ArrayList<String>();
        teamStats.add(team_name + " Stats");
        listview.setAdapter(new SettingsListBaseAdapter(this.getActivity(), teamStats));
        
        //mButton.setOnClickListener(new View.OnClickListener() {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {	
			@Override
			//public void onClick(View arg0) {
			public void onItemClick(AdapterView<?> parent, final View view,
	                int position, long id) {
				String ultimateURL = getResources().getString(R.string.UltimateTeamStatsURL);
				Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ultimateURL+ team_id + "/main"));
	            startActivity(myIntent);
			}
		});
       
        return rootView;
    }		
}

