package info.androidhive.audlandroid.adapter;

import info.androidhive.audlandroid.ScoresDivisionListFragment;
import info.androidhive.audlandroid.model.ScoreListItem;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class ScoreDivisionsPagerAdapter extends FragmentStatePagerAdapter{
	private ArrayList<String> divisionNames;
	private ArrayList<ArrayList<ScoreListItem>> leagueScores;
	private ArrayList<String> homeTeams;
	private ArrayList<String> homeTeamIDs;
	private ArrayList<String> awayTeams;
	private ArrayList<String> awayTeamIDs;
	private ArrayList<String> dates;
	private ArrayList<String> times;
	private ArrayList<String> homeTeamScores;
	private ArrayList<String> awayTeamScores;
	private ArrayList<String> gameStatus;
	
	public ScoreDivisionsPagerAdapter(FragmentManager fm,ArrayList<String> divisionNames,ArrayList<ArrayList<ScoreListItem>> leagueScores) {
		super(fm);
		this.divisionNames = divisionNames;
		this.leagueScores = leagueScores;
	}
	@Override
	public CharSequence getPageTitle(int position) {
	    return divisionNames.get(position);
	}
	@Override
	public Fragment getItem(int position) {
		ScoresDivisionListFragment fragment = new ScoresDivisionListFragment();
		homeTeams = new ArrayList<String>();
		homeTeamIDs = new ArrayList<String>();
		awayTeams = new ArrayList<String>();
		awayTeamIDs = new ArrayList<String>();
		dates = new ArrayList<String>();
		times = new ArrayList<String>();
		homeTeamScores = new ArrayList<String>();
		awayTeamScores = new ArrayList<String>();
		gameStatus = new ArrayList<String>();
		
		Bundle bundle = new Bundle();
		for(int i=0;i<leagueScores.get(position).size();i++){
			homeTeams.add(leagueScores.get(position).get(i).getHomeTeam());
			homeTeamIDs.add(leagueScores.get(position).get(i).getHomeTeamID());
			awayTeams.add(leagueScores.get(position).get(i).getAwayTeam());
			awayTeamIDs.add(leagueScores.get(position).get(i).getAwayTeamID());
			dates.add(leagueScores.get(position).get(i).getDate());
			times.add(leagueScores.get(position).get(i).getTime());
			homeTeamScores.add(leagueScores.get(position).get(i).getHomeTeamScore());
			awayTeamScores.add(leagueScores.get(position).get(i).getAwayTeamScore());
			gameStatus.add(leagueScores.get(position).get(i).getGameStatus());
		}
		bundle.putStringArrayList("homeTeamList", homeTeams);
		bundle.putStringArrayList("homeTeamIDList", homeTeamIDs);
		bundle.putStringArrayList("awayTeamList", awayTeams);
		bundle.putStringArrayList("awayTeamIDList", awayTeamIDs);
		bundle.putStringArrayList("dateList", dates);
		bundle.putStringArrayList("timeList", times);
		bundle.putStringArrayList("homeTeamScoreList", homeTeamScores);
		bundle.putStringArrayList("awayTeamScoreList", awayTeamScores);
		bundle.putStringArrayList("gameScoreList", gameStatus);
		bundle.putString("divisionName", divisionNames.get(position));
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public int getCount() {
		return divisionNames.size();
	}
	
	
}
