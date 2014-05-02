package info.androidhive.audlandroid.adapter;
 
import java.util.ArrayList;

import info.androidhive.audlandroid.TeamsRosterFragment;
import info.androidhive.audlandroid.TeamsScheduleFragment;
import info.androidhive.audlandroid.TeamsScoreFragment;
import info.androidhive.audlandroid.TeamsStatsFragment;

import info.androidhive.audlandroid.model.ScoreListItem;
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
    	else if (position == 2){
    		return "Scores";
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
        	//Scores fragment
        	TeamsScoreFragment scoresFrag = new TeamsScoreFragment();
        	Bundle scoresArgs = new Bundle();
        	scoresArgs.putString("TEAM_NAME", team.getTeamName());
        	scoresArgs.putString("TEAM_ID", team.getTeamId());
        	ArrayList<String> homeTeams = new ArrayList<String>();
        	ArrayList<String> homeTeamIDs = new ArrayList<String>();
        	ArrayList<String> awayTeams = new ArrayList<String>();
        	ArrayList<String> awayTeamIDs = new ArrayList<String>();
        	ArrayList<String> dates = new ArrayList<String>();
        	ArrayList<String> times = new ArrayList<String>();
        	ArrayList<String> homeTeamScores = new ArrayList<String>();
        	ArrayList<String> awayTeamScores = new ArrayList<String>();
        	ArrayList<String> gameStatus = new ArrayList<String>();
        	ArrayList<String> ISOTimeList = new ArrayList<String>();
        	ArrayList<ScoreListItem> scoresList = team.getScores();
        	for (int i=0; i<scoresList.size(); i++){
        		homeTeams.add(scoresList.get(i).getHomeTeam());
        		homeTeamIDs.add(scoresList.get(i).getHomeTeamID());
        		awayTeams.add(scoresList.get(i).getAwayTeam());
        		awayTeamIDs.add(scoresList.get(i).getAwayTeamID());
        		dates.add(scoresList.get(i).getDate());
        		times.add(scoresList.get(i).getTime());
        		homeTeamScores.add(scoresList.get(i).getHomeTeamScore());
        		awayTeamScores.add(scoresList.get(i).getAwayTeamScore());
        		gameStatus.add(scoresList.get(i).getGameStatus());
        		ISOTimeList.add(scoresList.get(i).getISOTime());
        	}
        	scoresArgs.putStringArrayList("homeTeamList", homeTeams);
    		scoresArgs.putStringArrayList("homeTeamIDList", homeTeamIDs);
    		scoresArgs.putStringArrayList("awayTeamList", awayTeams);
    		scoresArgs.putStringArrayList("awayTeamIDList", awayTeamIDs);
    		scoresArgs.putStringArrayList("dateList", dates);
    		scoresArgs.putStringArrayList("timeList", times);
    		scoresArgs.putStringArrayList("homeTeamScoreList", homeTeamScores);
    		scoresArgs.putStringArrayList("awayTeamScoreList", awayTeamScores);
    		scoresArgs.putStringArrayList("gameScoreList", gameStatus);
    		scoresArgs.putStringArrayList("ISOTimeList", ISOTimeList);
    		scoresFrag.setArguments(scoresArgs);
        	return scoresFrag;
        case 3:
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
        return 4;
    }
 
}

