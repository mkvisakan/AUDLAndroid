package info.androidhive.audlandroid.adapter;
 
import info.androidhive.audlandroid.NewsListFragment;
import info.androidhive.audlandroid.ScheduleDivisionFragment;
import info.androidhive.audlandroid.TeamsListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
 
public class ScheduleListTabsPagerAdapter extends FragmentStatePagerAdapter {
 
    public ScheduleListTabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public CharSequence getPageTitle(int position) {
    	if(position == 0){
    		return "Midwestern";
    	}
    	else{
    		return "Eastern";
    	}
    }
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
        	ScheduleDivisionFragment midwesternScheduleList = new ScheduleDivisionFragment();
        	Bundle midwesternArgs = new Bundle();
        	midwesternArgs.putString("DIVISION_NAME", "Midwestern");
        	midwesternScheduleList.setArguments(midwesternArgs);
            return midwesternScheduleList;
        case 1:
        	ScheduleDivisionFragment easternScheduleList = new ScheduleDivisionFragment();
        	Bundle easternArgs = new Bundle();
        	easternArgs.putString("DIVISION_NAME", "Eastern");
        	easternScheduleList.setArguments(easternArgs);
            return easternScheduleList;
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }
 
}
