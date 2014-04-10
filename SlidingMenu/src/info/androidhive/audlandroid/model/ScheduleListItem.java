package info.androidhive.audlandroid.model;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class ScheduleListItem implements Parcelable {
	
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
	
	// dummy constructor that takes a Parcel and gives you an object populated with it's values
    private ScheduleListItem(Parcel in) {
    	List<String> parcelData= new ArrayList<String>();
    	in.readStringList(parcelData);
    	division = parcelData.get(0);
    	homeTeam = parcelData.get(1);
    	homeTeamID = parcelData.get(2);
    	awayTeam = parcelData.get(3);
    	awayTeamID = parcelData.get(4);
    	date = parcelData.get(5); 
    	time = parcelData.get(6);
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
	
	//Implementing Parcelable
	
	
	// 99.9% of the time you can just ignore this
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    public void writeToParcel(Parcel out, int flags) {
    	List<String> parcelData= new ArrayList<String>();
    	parcelData.add(division);
    	parcelData.add(homeTeam);
    	parcelData.add(homeTeamID);
    	parcelData.add(awayTeam);
    	parcelData.add(awayTeamID);
    	parcelData.add(date); 
    	parcelData.add(time);
    	out.writeStringList(parcelData);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<ScheduleListItem> CREATOR = new Parcelable.Creator<ScheduleListItem>() {
        public ScheduleListItem createFromParcel(Parcel in) {
            return new ScheduleListItem(in);
        }

        public ScheduleListItem[] newArray(int size) {
            return new ScheduleListItem[size];
        }
    };

    

}
