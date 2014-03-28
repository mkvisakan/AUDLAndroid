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
	private ArrayList<String> divisionScores;
	private String TAG = "info.androidhive.audlandroid.adapter";
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
		divisionScores = new ArrayList<String>();
		Bundle bundle = new Bundle();
		for(int i=0;i<leagueScores.get(position).size();i++){
			divisionScores.add(leagueScores.get(position).get(i).toJSON());
		}
		bundle.putStringArrayList("ScoreItemList", divisionScores);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public int getCount() {
		return divisionNames.size();
	}
	
	
}
