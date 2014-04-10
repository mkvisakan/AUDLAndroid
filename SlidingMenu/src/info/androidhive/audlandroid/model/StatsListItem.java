package info.androidhive.audlandroid.model;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class StatsListItem implements Parcelable {

	private String statType;
	private String playerName;
	private String statValue;
	private String playerTeamID;
	
	
	public StatsListItem(String statType, String playerName, String statValue, String playerTeamID){
		this.statType = statType;
		this.playerName = playerName;
		this.statValue = statValue;
		this.playerTeamID = playerTeamID;
	}
	
	// dummy constructor that takes a Parcel and gives you an object populated with it's values
	private StatsListItem(Parcel in) {
    	List<String> parcelData= new ArrayList<String>();
    	in.readStringList(parcelData);
    	statType = parcelData.get(0);
    	playerName = parcelData.get(1);
    	statValue = parcelData.get(2);
    	playerTeamID = parcelData.get(3);
	}
	public String getStatType(){
		return statType;
	}
	
	public String getPlayerName(){
		return playerName;
	}
	
	public String getStatValue(){
		return statValue;
	}
	
	public String getPlayerTeamID(){
		return playerTeamID;
	}
	
	//Implementing Parcelable
	
	
	// 99.9% of the time you can just ignore this
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    public void writeToParcel(Parcel out, int flags) {
    	List<String> parcelData = new ArrayList<String>();
    	parcelData.add(statType);
    	parcelData.add(playerName);
    	parcelData.add(statValue);
    	parcelData.add(playerTeamID);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<StatsListItem> CREATOR = new Parcelable.Creator<StatsListItem>() {
        public StatsListItem createFromParcel(Parcel in) {
            return new StatsListItem(in);
        }

        public StatsListItem[] newArray(int size) {
            return new StatsListItem[size];
        }
    };

}
