package info.androidhive.audlandroid.model;

import java.util.ArrayList;

import com.google.gson.Gson;

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
	public String toJSON(){
		Gson gson= new Gson();
		return gson.toJson(this);
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
	public void setHomeTeamID(String homeTeamID) {
		this.homeTeamID = homeTeamID;
	}
	public String getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
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
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
	
	
}
