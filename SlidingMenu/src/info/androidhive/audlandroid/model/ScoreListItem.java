package info.androidhive.audlandroid.model;

public class ScoreListItem {
	private String homeTeam;
	private String homeTeamID;
	private String awayTeam;
	private String awayTeamID;
	private String date;
	private String time;
	private String score;
	
	public ScoreListItem(String homeTeam,String homeTeamID,String awayTeam,String awayTeamID,String date,
			String time,String score){
		this.homeTeam = homeTeam;
		this.homeTeamID = homeTeamID;
		this.awayTeam = awayTeam;
		this.awayTeamID = awayTeamID;
		this.date = date;
		this.time = time;
		this.score = score;
	}
	public String getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}
	public String getHomeTeamID() {
		return homeTeamID;
	}
	
	public String getAwayTeam() {
		return awayTeam;
	}
	
	public String getAwayTeamID() {
		return awayTeamID;
	}
	public void setAwayTeamID(String awayTeamID) {
		this.awayTeamID = awayTeamID;
	}
	public String getDate() {
		return date;
	}
	
	public String getTime() {
		return time;
	}
	
	public String getScore() {
		return score;
	}
	
	public boolean equals(ScoreListItem scoreItem){
		if(awayTeam.compareTo(scoreItem.getAwayTeam())==0 &&
				homeTeam.compareTo(scoreItem.getHomeTeam())==0 &&
				awayTeamID.compareTo(scoreItem.getAwayTeamID())==0 &&
				homeTeamID.compareTo(scoreItem.getHomeTeamID())==0 &&
				time.compareTo(scoreItem.getTime())==0 &&
				date.compareTo(scoreItem.getDate())==0 &&
				score.compareTo(scoreItem.getScore())==0){
			return true;
		}
		return false;
	}
}
