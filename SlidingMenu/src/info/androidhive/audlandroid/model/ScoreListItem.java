package info.androidhive.audlandroid.model;

public class ScoreListItem {
	private String homeTeam;
	private String homeTeamID;
	private String awayTeam;
	private String awayTeamID;
	private String date;
	private String time;
	private String homeTeamScore;
	private String awayTeamScore;
	private String gameStatus;
	public ScoreListItem(String homeTeam,String homeTeamID,String awayTeam,String awayTeamID,String date,
			String time,String homeTeamScore, String awayTeamScore, String gameStatus){
		this.homeTeam = homeTeam;
		this.homeTeamID = homeTeamID;
		this.awayTeam = awayTeam;
		this.awayTeamID = awayTeamID;
		this.date = date;
		this.time = time;
		this.homeTeamScore = homeTeamScore;
		this.awayTeamScore = awayTeamScore;
		this.gameStatus = gameStatus;
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
	
	public String getHomeTeamScore() {
		return homeTeamScore;
	}
	
	public String getAwayTeamScore(){
		return awayTeamScore;
	}
	
	public String getGameStatus(){
		return gameStatus;
	}
	
}
