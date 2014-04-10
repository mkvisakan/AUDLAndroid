package info.androidhive.audlandroid.adapter;
 
import info.androidhive.audlandroid.ScheduleDivisionFragment;
import info.androidhive.audlandroid.model.ScheduleListItem;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
 
public class ScheduleListTabsPagerAdapter extends FragmentStatePagerAdapter {
    HashMap<String, ArrayList<ScheduleListItem>> schedLists;
    Object[] pageTitles;
    public ScheduleListTabsPagerAdapter(FragmentManager fm, HashMap<String, ArrayList<ScheduleListItem>> schLs) {
        super(fm);
        schedLists = schLs;
        pageTitles = schLs.keySet().toArray();
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
    		ScheduleDivisionFragment divisionScheduleList = new ScheduleDivisionFragment();
        	Bundle args = new Bundle();
        	args.putString("DIVISION_NAME", (String)pageTitles[index]);
        	args.putParcelableArrayList("DIVISION_DATA", schedLists.get((String)pageTitles[index]));
        	divisionScheduleList.setArguments(args);
            return divisionScheduleList;
    	}
       	
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return pageTitles.length;
    }
 
}
