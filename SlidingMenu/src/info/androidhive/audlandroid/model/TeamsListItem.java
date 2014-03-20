package info.androidhive.audlandroid.model;

public class TeamsListItem {
	
	private String teamName;
	private String teamId;
	
	public TeamsListItem(String name, String id){
		this.teamName = name;
		this.teamId = id;
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

}
