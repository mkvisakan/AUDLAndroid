package info.androidhive.audlandroid.model;

public class TeamRecordItem {
	private String teamName;
	private String wins;
	private String losses;
	
	public TeamRecordItem(String teamName,String wins,String losses) {
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
	public String getWins() {
		return wins;
	}
	public void setWins(String wins) {
		this.wins = wins;
	}
	public String getLosses() {
		return losses;
	}
	public void setLosses(String losses) {
		this.losses = losses;
	}
	public String toString(){
		return teamName + " " + wins + " " + losses;
	}
	public boolean equals(TeamRecordItem i){
		if((i.getTeamName().compareTo(teamName)==0) &&  
				(i.getLosses().compareTo(losses)==0) &&
				(i.getWins().compareTo(wins)==0)){
			return true;
		}
		return false;
	}
	
}
