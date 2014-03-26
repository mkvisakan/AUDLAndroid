package info.androidhive.audlandroid.model;

public class ScheduleListItem {

	private String division;
	private String homeTeam;
	private String awayTeam;
	private String date;
	private String time;
	
	public ScheduleListItem(String division, String homeTeam, String awayTeam, String date, String time){
		this.division = division;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.date = date;
		this.time = time;
	}
	
	public String getDivision(){
		return division;
	}
	
	public String getHomeTeam(){
		return homeTeam;
	}
	
	public String getAwayTeam(){
		return awayTeam;
	}
	
	public String getDate(){
		return date;
	}
	
	public String getTime(){
		return time;
	}

}
