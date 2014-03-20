package info.androidhive.audlandroid.model;

public class NewsListItem {
	
	private String newsHeadline;
	private String newsURL;
	
	public NewsListItem(String headline, String URL){
		this.newsHeadline = headline;
		this.newsURL = URL;
	}
	
	public String getNewsHeadline(){
		return this.newsHeadline;
	}
	
	public String getNewsURL(){
		return this.newsURL;
	}
	
	public void setNewsHeadline(String headline){
		this.newsHeadline = headline;
	}
	
	public void setNewsURL(String URL){
		this.newsURL = URL;
	}
}
