package info.androidhive.audlandroid.model;

import java.util.ArrayList;

public class TeamsListItem {
	
	private String teamName;
	private String teamId;
	private ArrayList<String> player_names;
	private ArrayList<String> player_ids;
	private ArrayList<String> schedTeams;
	private ArrayList<String> schedDateTimes;
	
	public TeamsListItem(String name, String id){
		this.teamName = name;
		this.teamId = id;
		this.player_names = new ArrayList<String>();
		this.player_ids = new ArrayList<String>();
		this.schedTeams = new ArrayList<String>();
		this.schedDateTimes = new ArrayList<String>();
	}
	
	public String getTeamName(){
		return this.teamName;
	}
	
	public String getTeamId(){
		return this.teamId;
	}
	
	public void setTeamName(String name){
		this.teamName = name;
	}
	
	public void setNewsURL(String id){
		this.teamId = id;
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
	
	public void addSchedule(String team, String date, String time){
		schedTeams.add(team);
		schedDateTimes.add(date + " " + time);
	}
	
	public ArrayList<String> getSchedTeams(){
		return schedTeams;
	}
	
	public ArrayList<String> getSchedDateTimes(){
		return schedDateTimes;
	}

}
