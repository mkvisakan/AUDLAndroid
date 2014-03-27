package info.androidhive.audlandroid.model;

public class StatsListItem {

	private String statType;
	private String playerName;
	private String statValue;
	private String playerTeamID;
	
	
	public StatsListItem(String statType, String playerName, String statValue, String playerTeamID){
		this.statType = statType;
		this.playerName = playerName;
		this.statValue = statValue;
		this.playerTeamID = playerTeamID;
	}
	
	public String getStatType(){
		return statType;
	}
	
	public String getPlayerName(){
		return playerName;
	}
	
	public String getStatValue(){
		return statValue;
	}
	
	public String getPlayerTeamID(){
		return playerTeamID;
	}

}
