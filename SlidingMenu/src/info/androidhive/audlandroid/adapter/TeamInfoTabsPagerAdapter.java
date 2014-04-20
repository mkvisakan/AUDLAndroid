package info.androidhive.audlandroid.adapter;
 
import java.util.ArrayList;

import info.androidhive.audlandroid.TeamsRosterFragment;
import info.androidhive.audlandroid.TeamsScheduleFragment;
import info.androidhive.audlandroid.TeamsStatsFragment;

import info.androidhive.audlandroid.model.TeamsListItem;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

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
    	else if (position == 1){
    		return "Schedule";
    	}
    	else {
    		return "Stats";
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
        	args.putString("TEAM_NAME", team.getTeamName());
        	args.putStringArrayList("PLAYER_NAMES", team.getPlayerNames());
        	args.putStringArrayList("PLAYER_IDS", team.getPlayerIds());
        	rosterFrag.setArguments(args);
            return rosterFrag;
        case 1:
        	// Schedule fragment
        	TeamsScheduleFragment schedFrag = new TeamsScheduleFragment();
        	Bundle schedArgs = new Bundle();
        	schedArgs.putString("TEAM_NAME", team.getTeamName());
        	schedArgs.putString("TEAM_ID", team.getTeamId());
        	schedArgs.putStringArrayList("SCHED_TEAMS",	team.getSchedTeams());
        	schedArgs.putStringArrayList("SCHED_TEAMIDS", team.getSchedTeamIds());
        	schedArgs.putStringArrayList("SCHED_DATES",	team.getSchedDates());
        	schedArgs.putStringArrayList("SCHED_TIMES", team.getSchedTimes());
        	schedFrag.setArguments(schedArgs);
        	return schedFrag;
        case 2:
        	//Stats fragment
        	TeamsStatsFragment statsFrag = new TeamsStatsFragment();
        	Bundle statsArgs = new Bundle();
        	statsArgs.putString("TEAM_NAME", team.getTeamName());
        	statsArgs.putString("TEAM_ID", team.getTeamId());
        	ArrayList<String> statsKeys = team.getStatsKeys();
        	statsArgs.putStringArrayList("STAT_KEYS", statsKeys);
        	for (String key : statsKeys){
        		statsArgs.putParcelableArrayList(key, team.getStatsList(key));
        	}
        	statsFrag.setArguments(statsArgs);
        	return statsFrag;
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
 
}

