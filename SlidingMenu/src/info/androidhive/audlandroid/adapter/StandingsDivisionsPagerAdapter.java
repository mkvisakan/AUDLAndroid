package info.androidhive.audlandroid.adapter;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import info.androidhive.audlandroid.DivisionListFragment;
import info.androidhive.audlandroid.model.TeamRecordItem;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class StandingsDivisionsPagerAdapter extends FragmentStatePagerAdapter{
	private ArrayList<String> tabNames;
	private ArrayList<ArrayList<TeamRecordItem>> leagueRecords;
	private ArrayList<ArrayList<String>> teamNames;
	private ArrayList<ArrayList<String>> teamIDs;
	private ArrayList<ArrayList<String>> teamWins;
	private ArrayList<ArrayList<String>> teamLosses;
	private ArrayList<ArrayList<String>> teamPointsDiff;
	private String TAG = "info.androidhive.audlandroid.StandingsDivisionsPagerAdapter";
	public StandingsDivisionsPagerAdapter(FragmentManager fm,ArrayList<String> tabValues,ArrayList<ArrayList<TeamRecordItem>> records) {
		super(fm);
		tabNames = tabValues;	
		this.leagueRecords = records;
		teamNames = new ArrayList<ArrayList<String>>();
		teamIDs = new ArrayList<ArrayList<String>>();
		teamWins = new ArrayList<ArrayList<String>>();
		teamLosses = new ArrayList<ArrayList<String>>();
		teamPointsDiff = new ArrayList<ArrayList<String>>();
		Log.i(TAG,"Size" + leagueRecords.size());
		for(int i = 0;i<leagueRecords.size();i++){
			ArrayList<String> divisionTeamNames = new ArrayList<String>();
			ArrayList<String> divisionTeamIDs = new ArrayList<String>();
			ArrayList<String> divisionTeamWins = new ArrayList<String>();
			ArrayList<String> divisionTeamLosses = new ArrayList<String>();
			ArrayList<String> divisionTeamPointsDiff = new ArrayList<String>();
			for(int j=0;j<leagueRecords.get(i).size();j++){
				divisionTeamNames.add(leagueRecords.get(i).get(j).getTeamName());
				divisionTeamIDs.add(leagueRecords.get(i).get(j).getTeamID());
				divisionTeamWins.add(leagueRecords.get(i).get(j).getWins());
				divisionTeamLosses.add(leagueRecords.get(i).get(j).getLosses());
				divisionTeamPointsDiff.add(leagueRecords.get(i).get(j).getPointsDiff());
			}
			teamNames.add(divisionTeamNames);
			teamIDs.add(divisionTeamIDs);
			teamWins.add(divisionTeamWins);
			teamLosses.add(divisionTeamLosses);
			teamPointsDiff.add(divisionTeamPointsDiff);
		}
	}
	
	    @Override
	    public CharSequence getPageTitle(int position) {
	    	return tabNames.get(position);
	    }
	    @Override
	    public Fragment getItem(int index) {
	        DivisionListFragment fragment =  new DivisionListFragment(); 
	        Bundle bundle = new Bundle();
	        bundle.putStringArrayList("divisionTeamNames", teamNames.get(index));
			bundle.putStringArrayList("divisionTeamIDs", teamIDs.get(index));
	        bundle.putStringArrayList("divisionTeamWins",teamWins.get(index));
			bundle.putStringArrayList("divisionTeamLosses", teamLosses.get(index));
			bundle.putStringArrayList("divisionTeamPointsDiff", teamPointsDiff.get(index));
		    fragment.setArguments(bundle);
	        return fragment;
	    }
	 
	    @Override
	    public int getCount() {
	        // get item count - equal to number of tabs
	        return tabNames.size();
	    }
	 
}
