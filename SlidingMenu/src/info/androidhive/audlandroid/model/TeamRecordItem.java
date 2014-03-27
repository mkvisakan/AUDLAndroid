package info.androidhive.audlandroid.model;

public class TeamRecordItem {
	private String teamName;
	private int wins;
	private int losses;
	
	public TeamRecordItem(String teamName,int wins,int losses) {
		this.teamName = teamName;
		this.wins = wins;
		this.losses = losses;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}
	public String toString(){
		return teamName + " " + wins + " " + losses;
	}
	
}
