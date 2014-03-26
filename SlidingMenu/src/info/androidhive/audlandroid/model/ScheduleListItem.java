package info.androidhive.audlandroid.model;

public class ScheduleListItem {

	private String division;
	private String homeTeam;
	private String homeTeamID;
	private String awayTeam;
	private String awayTeamID;
	private String date;
	private String time;
	
	
	public ScheduleListItem(String division, String homeTeam, String homeTeamID, String awayTeam, String awayTeamID, String date, String time){
		this.division = division;
		this.homeTeam = homeTeam;
		this.homeTeamID = homeTeamID;
		this.awayTeam = awayTeam;
		this.awayTeamID = awayTeamID;
		this.date = date;
		this.time = time;
	}
	
	public String getDivision(){
		return division;
	}
	
	public String getHomeTeam(){
		return homeTeam;
	}
	
	public String getHomeTeamID(){
		return homeTeamID;
	}
	
	public String getAwayTeam(){
		return awayTeam;
	}
	
	public String getAwayTeamID(){
		return awayTeamID;
	}
	public String getDate(){
		return date;
	}
	
	public String getTime(){
		return time;
	}

}
