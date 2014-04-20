package info.androidhive.audlandroid.model;

public class TeamRecordItem {
	private String teamName;
	private String teamID;
	private String wins;
	private String losses;
	private String pointsDiff;
	
	public TeamRecordItem(String teamName,String teamID,String wins,String losses,String pointsDiff) {
		this.teamName = teamName;
		this.teamID = teamID;
		this.wins = wins;
		this.losses = losses;
		this.pointsDiff = pointsDiff;
	}
	public String getTeamName() {
		return teamName;
	}
	
	public String getTeamID() {
		return teamID;
	}
	
	public String getWins() {
		return wins;
	}
	
	public String getLosses() {
		return losses;
	}
	
	public String getPointsDiff(){
		return pointsDiff;
	}
	
	public String toString(){
		return teamName + " " + wins + " " + losses;
	}
	
}
