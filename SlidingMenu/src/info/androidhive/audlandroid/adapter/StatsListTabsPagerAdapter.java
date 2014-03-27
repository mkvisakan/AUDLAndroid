package info.androidhive.audlandroid.adapter;
 
import info.androidhive.audlandroid.StatFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
 
public class StatsListTabsPagerAdapter extends FragmentStatePagerAdapter {
 
    public StatsListTabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public CharSequence getPageTitle(int position) {
    	
    	switch(position) {
    	case 0:
    		return "Throwaways";
    	case 1:
    		return "Assists";
    	case 2:
    		return "Goals";
    	case 3:
    		return "PMC";
    	case 4:
    		return "Drops";
    	case 5:
    		return "D's";
    	}
    	return null;
    }
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
        	StatFragment throwawaysStatList = new StatFragment();
        	Bundle throwawaysArgs = new Bundle();
        	throwawaysArgs.putString("STAT_NAME", "Throwaways");
        	throwawaysStatList.setArguments(throwawaysArgs);
            return throwawaysStatList;
        case 1:
        	StatFragment assistsStatList = new StatFragment();
        	Bundle assistsArgs = new Bundle();
        	assistsArgs.putString("STAT_NAME", "Assists");
        	assistsStatList.setArguments(assistsArgs);
            return assistsStatList;
        case 2:
        	StatFragment goalsStatList = new StatFragment();
        	Bundle goalsArgs = new Bundle();
        	goalsArgs.putString("STAT_NAME", "Goals");
        	goalsStatList.setArguments(goalsArgs);
            return goalsStatList;
        case 3:
        	StatFragment pmcStatList = new StatFragment();
        	Bundle pmcArgs = new Bundle();
        	pmcArgs.putString("STAT_NAME", "PMC");
        	pmcStatList.setArguments(pmcArgs);
            return pmcStatList;
        case 4:
        	StatFragment dropsStatList = new StatFragment();
        	Bundle dropsArgs = new Bundle();
        	dropsArgs.putString("STAT_NAME", "Drops");
        	dropsStatList.setArguments(dropsArgs);
            return dropsStatList;
        case 5:
        	StatFragment dsStatList = new StatFragment();
        	Bundle dsArgs = new Bundle();
        	dsArgs.putString("STAT_NAME", "Ds");
        	dsStatList.setArguments(dsArgs);
            return dsStatList;
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 6;
    }
 
}
