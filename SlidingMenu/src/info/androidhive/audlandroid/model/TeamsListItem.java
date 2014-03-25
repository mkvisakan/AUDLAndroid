package info.androidhive.audlandroid.model;

import java.util.ArrayList;

public class TeamsListItem {
	
	private String teamName;
	private String teamId;
	private ArrayList<String> player_names;
	private ArrayList<String> player_ids;
	private ArrayList<String> schedTeams;
	private ArrayList<String> schedDateTimes;
	private ArrayList<String> goalsPlayers;
	private ArrayList<String> goals;
	private ArrayList<String> assistPlayers;
	private ArrayList<String> assists;
	private ArrayList<String> dropPlayers;
	private ArrayList<String> drops;
	private ArrayList<String> throwAwayPlayers;
	private ArrayList<String> throwAways;
	private ArrayList<String> pmcPlayers;
	private ArrayList<String> pmc;
	private ArrayList<String> dsPlayers;
	private ArrayList<String> ds;
	
	public TeamsListItem(String name, String id){
		this.teamName = name;
		this.teamId = id;
		this.player_names = new ArrayList<String>();
		this.player_ids = new ArrayList<String>();
		this.schedTeams = new ArrayList<String>();
		this.schedDateTimes = new ArrayList<String>();
		this.goalsPlayers = new ArrayList<String>();
		this.goals = new ArrayList<String>();
		this.assistPlayers = new ArrayList<String>();
		this.assists = new ArrayList<String>();
		this.dropPlayers = new ArrayList<String>();
		this.drops = new ArrayList<String>();
		this.throwAwayPlayers = new ArrayList<String>();
		this.throwAways = new ArrayList<String>();
		this.pmcPlayers = new ArrayList<String>();
		this.pmc = new ArrayList<String>();
		this.dsPlayers = new ArrayList<String>();
		this.ds = new ArrayList<String>();
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

	public void addGoals(String player, String goalsCount){
		goalsPlayers.add(player);
		goals.add(goalsCount);
	}
	
	public ArrayList<String> getGoalPlayers(){
		return goalsPlayers;
	}
	
	public ArrayList<String> getGoals(){
		return goals;
	}
	
	public void addAssists(String player, String count){
		assistPlayers.add(player);
		assists.add(count);
	}
	
	public ArrayList<String> getAssistPlayers(){
		return assistPlayers;
	}
	
	public ArrayList<String> getAssists(){
		return assists;
	}
	
    public void addDrops(String player, String count){
		dropPlayers.add(player);
		drops.add(count);
	}
    
    public ArrayList<String> getDropPlayers(){
		return dropPlayers;
	}
    
    public ArrayList<String> getDrops(){
		return drops;
	}
    
    public void addThrowAways(String player, String count){
    	throwAwayPlayers.add(player);
    	throwAways.add(count);
	}
    
    public ArrayList<String> getThrowAwayPlayers(){
		return throwAwayPlayers;
	}
    
    public ArrayList<String> getThrowAways(){
		return throwAways;
	}
    
    public void addPMC(String player, String count){
    	pmcPlayers.add(player);
		pmc.add(count);
	}
    
    public ArrayList<String> getPMCPlayers(){
		return pmcPlayers;
	}
    
    public ArrayList<String> getPMC(){
		return pmc;
	}
    
    public void addDs(String player, String count){
    	dsPlayers.add(player);
		ds.add(count);
	}
    
    public ArrayList<String> getDsPlayers(){
		return dsPlayers;
	}
    
    public ArrayList<String> getDs(){
		return ds;
	}
}
