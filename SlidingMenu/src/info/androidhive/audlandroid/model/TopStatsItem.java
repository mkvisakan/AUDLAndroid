package info.androidhive.audlandroid.model;

public class TopStatsItem {
	private String topGoals;
	private String numGoals;
	private String topAssists;
	private String numAssists;
	private String topDrops;
	private String numDrops;
	private String topTAs;
	private String numTAs;
	private String topDs;
	private String numDs;
	
	
	public TopStatsItem(String topGoals,String numGoals,String topAssists,String numAssists,String topDrops,
			String numDrops,String topTAs,String numTAs,String topDs,String numDs){
		this.topGoals=topGoals;
		this.numGoals=numGoals;
		this.topAssists=topAssists;
		this.numAssists=numAssists;
		this.topDrops=topDrops;
		this.numDrops=numDrops;
		this.topTAs=topTAs;
		this.numTAs=numTAs;
		this.topDs=topDs;
		this.numDs=numDs;
	}
	public String getTopGoals() {
		return topGoals;
	}
	public String getNumGoals() {
		return numGoals;
	}
	public String getTopAssists() {
		return topAssists;
	}
	public String getNumAssists() {
		return numAssists;
	}
	public String getTopDrops() {
		return topDrops;
	}
	public String getNumDrops() {
		return numDrops;
	}
	public String getTopTAs() {
		return topTAs;
	}
	public String getNumTAs() {
		return numTAs;
	}
	public String getTopDs() {
		return topDs;
	}
	public String getNumDs() {
		return numDs;
	}
	
	
}
