package info.androidhive.audlandroid.model;

public class NewsListItem {
	
	private String newsHeadline;
	private String newsURL;
	private String datetimeString;
	
	public NewsListItem(String headline, String URL, String datetime){
		this.newsHeadline = headline;
		this.newsURL = URL;
		this.datetimeString = datetime;
	}
	
	public String getNewsHeadline(){
		return this.newsHeadline;
	}
	
	public String getNewsURL(){
		return this.newsURL;
	}
	
	public String getDatetime() {
		return this.datetimeString;
	}
}
