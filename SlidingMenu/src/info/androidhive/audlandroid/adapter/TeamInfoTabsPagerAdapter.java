package info.androidhive.audlandroid.adapter;
 
import org.json.JSONArray;

import info.androidhive.audlandroid.NewsListFragment;
import info.androidhive.audlandroid.TeamsListFragment;
import info.androidhive.audlandroid.TeamsRosterFragment;
import info.androidhive.audlandroid.TeamsScheduleFragment;
import info.androidhive.audlandroid.model.TeamsListItem;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
 
public class TeamInfoTabsPagerAdapter extends FragmentStatePagerAdapter {
	private TeamsListItem team;
 
    public TeamInfoTabsPagerAdapter(FragmentManager fm, TeamsListItem team) {
        super(fm);
        this.team = team;
    }
    @Override
    public CharSequence getPageTitle(int position) {
    	if(position == 0){
    		return "Players";
    	}
    	else{
    		return "Schedule";
    	}
    }
    @Override
    public Fragment getItem(int index) {
    	Log.i("TeamInfoTabsPagerAdapter", "index : " + index);
        switch (index) {
        case 0:
            // Roster fragment activity
        	TeamsRosterFragment rosterFrag = new TeamsRosterFragment();
        	Bundle args = new Bundle();
        	args.putStringArrayList("PLAYER_NAMES", team.getPlayerNames());
        	args.putStringArrayList("PLAYER_IDS", team.getPlayerIds());
        	rosterFrag.setArguments(args);
            return rosterFrag;
        case 1:
        	// Schedule fragment
        	TeamsScheduleFragment schedFrag = new TeamsScheduleFragment();
        	Bundle schedArgs = new Bundle();
        	schedArgs.putStringArrayList("SCHED_TEAMS",	team.getSchedTeams());
        	schedArgs.putStringArrayList("SCHED_DATETIMES",	team.getSchedDateTimes());
        	schedFrag.setArguments(schedArgs);
        	return schedFrag;
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }
 
}

