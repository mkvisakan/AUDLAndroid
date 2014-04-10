package info.androidhive.audlandroid.adapter;
 
import info.androidhive.audlandroid.StatFragment;
import info.androidhive.audlandroid.model.StatsListItem;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
 
public class StatsListTabsPagerAdapter extends FragmentStatePagerAdapter {
	HashMap<String, ArrayList<StatsListItem>> statsLists;
    Object[] pageTitles;
    public StatsListTabsPagerAdapter(FragmentManager fm, HashMap<String, ArrayList<StatsListItem>> statLs) {
        super(fm);
        statsLists = statLs;
        pageTitles = statLs.keySet().toArray();
    }
    @Override
    public CharSequence getPageTitle(int position) {
    	
    	if(position >=0 && position < pageTitles.length){
    		return (String)pageTitles[position];
    	}
    	
    	return null;
    }
    @Override
    public Fragment getItem(int index) {
 
    	if(index >=0 && index < pageTitles.length){
    		StatFragment statList = new StatFragment();
        	Bundle args = new Bundle();
        	args.putString("STAT_NAME", (String)pageTitles[index]);
        	args.putParcelableArrayList("STAT_DATA", statsLists.get((String)pageTitles[index]));
        	statList.setArguments(args);
            return statList;
    	}
       	
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
    	return pageTitles.length;
    }
 
}
