package info.androidhive.audlandroid.adapter;

import info.androidhive.audlandroid.ScoreGraphFragment;
import info.androidhive.audlandroid.ScoreStatFragment;
import info.androidhive.audlandroid.model.ScoreListItem;
import info.androidhive.audlandroid.model.TopStatsItem;
import info.androidhive.audlandroid.utils.ImageLoader;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class ScoreInfoPagerAdapter extends FragmentStatePagerAdapter{
	private ArrayList<TopStatsItem> list;
	private ScoreListItem score;
	public ScoreInfoPagerAdapter(FragmentManager fm,ArrayList<TopStatsItem> list,ScoreListItem item,Activity activity) {
		super(fm);
		Log.i("","Constructor called");
		this.list=list;
		this.score=item;
	}
	@Override
    public CharSequence getPageTitle(int position) {
    	if(position == 0){
    		return "Top Players";
    	}
    	else {
    		return "Graphical View";
    	}
    }
	@Override
	public Fragment getItem(int index) {
		switch(index){
		case 0:
			ScoreStatFragment fragment = new ScoreStatFragment();
			Bundle args = new Bundle();
			ArrayList<String> tempList0 = new ArrayList<String>();
			tempList0.add(list.get(0).getTopGoals());
			tempList0.add(list.get(0).getTopAssists());
			tempList0.add(list.get(0).getTopDrops());
			tempList0.add(list.get(0).getTopTAs());
			tempList0.add(list.get(0).getTopDs());
			args.putStringArrayList("HOMEPLAYERS", tempList0);
			ArrayList<String> tempList1 = new ArrayList<String>();
			tempList1.add(list.get(0).getNumGoals());
			tempList1.add(list.get(0).getNumAssists());
			tempList1.add(list.get(0).getNumDrops());
			tempList1.add(list.get(0).getNumTAs());
			tempList1.add(list.get(0).getNumDs());
			args.putStringArrayList("HOMENUMBERS", tempList1);
			ArrayList<String> tempList2 = new ArrayList<String>();
			tempList2.add(list.get(1).getTopGoals());
			tempList2.add(list.get(1).getTopAssists());
			tempList2.add(list.get(1).getTopDrops());
			tempList2.add(list.get(1).getTopTAs());
			tempList2.add(list.get(1).getTopDs());
			args.putStringArrayList("AWAYPLAYERS", tempList2);
			ArrayList<String> tempList3 = new ArrayList<String>();
			tempList3.add(list.get(1).getNumGoals());
			tempList3.add(list.get(1).getNumAssists());
			tempList3.add(list.get(1).getNumDrops());
			tempList3.add(list.get(1).getNumTAs());
			tempList3.add(list.get(1).getNumDs());
			args.putStringArrayList("AWAYNUMBERS", tempList3);
			args.putString("HOMEID", score.getHomeTeamID());
			args.putString("AWAYID", score.getAwayTeamID());
			args.putString("HOMESCORE",score.getHomeTeamScore());
			args.putString("AWAYSCORE",score.getAwayTeamScore());
			fragment.setArguments(args);
			return fragment;
		case 1:
			ScoreGraphFragment mfragment = new ScoreGraphFragment();
			Bundle bundle = new Bundle();
			bundle.putString("HOMEID",score.getHomeTeamID());
			bundle.putString("DATE", score.getDate());
			bundle.putString("HOMETEAM", score.getHomeTeam());
			bundle.putString("AWAYTEAM", score.getAwayTeam());
			mfragment.setArguments(bundle);
			return mfragment;
		}
		return null;
	}

	@Override
	public int getCount() {
		return 2;
	}

}
