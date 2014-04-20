package info.androidhive.audlandroid.model;

import java.util.ArrayList;
import java.util.HashMap;


public class TeamsListItem {
	
	private String teamName;
	private String teamId;
	private ArrayList<String> player_names;
	private ArrayList<String> player_ids;
	private ArrayList<String> schedTeams;
	private ArrayList<String> schedTeamIds;
	private ArrayList<String> schedDates;
	private ArrayList<String> schedTimes;
	private HashMap<String, ArrayList<StatsListItem>> statsList;
	private ArrayList<String> statsKeys;
	
	public TeamsListItem(String name, String id){
		this.teamName = name;
		this.teamId = id;
		this.player_names = new ArrayList<String>();
		this.player_ids = new ArrayList<String>();
		this.schedTeams = new ArrayList<String>();
		this.schedDates = new ArrayList<String>();
		this.schedTimes = new ArrayList<String>();
		this.schedTeamIds = new ArrayList<String>();
		this.statsList = new HashMap<String, ArrayList<StatsListItem>>();
		this.statsKeys = new ArrayList<String>();
	}
	
	public String getTeamName(){
		return this.teamName;
	}
	
	public String getTeamId(){
		return this.teamId;
	}
	
	
	public void addPlayer(String player_name, String player_id){
		player_names.add(player_name);
		player_ids.add(player_id);
	}
	
	public ArrayList<String> getPlayerIds(){
		return player_ids;
	}
	
	public ArrayList<String> getPlayerNames(){
		return player_names;
	}
	
	public void addSchedule(String team, String teamId, String date, String time){
		schedTeams.add(team);
		schedDates.add(date);
		schedTimes.add(time);
		schedTeamIds.add(teamId);
	}
	
	public ArrayList<String> getSchedTeams(){
		return schedTeams;
	}
	
	public ArrayList<String> getSchedDates(){
		return schedDates;
	}
	
	public ArrayList<String> getSchedTimes(){
		return schedTimes;
	}
	
	public ArrayList<String> getSchedTeamIds(){
		return schedTeamIds;
	}

	public void addStats(String statType, String statPlayer, String statValue){
		StatsListItem stat = new StatsListItem(statType, statPlayer, statValue, this.teamId);
		if (statsList.containsKey(statType)){
			statsList.get(statType).add(stat);
		} else {
			ArrayList<StatsListItem> sList = new ArrayList<StatsListItem>();
			statsKeys.add(statType);
			sList.add(stat);
			statsList.put(statType, sList);
		}
	}
	
	public ArrayList<String> getStatsKeys(){
        return this.statsKeys;
	}
	
	public ArrayList<StatsListItem> getStatsList(String key){
		return statsList.get(key);
	}
	
}
