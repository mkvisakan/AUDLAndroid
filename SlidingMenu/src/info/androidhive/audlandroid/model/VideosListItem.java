package info.androidhive.audlandroid.model;

public class VideosListItem {
	private String videoName;
	private String videoURL;
	private String videoThumbnail;
	
	public VideosListItem(String name, String url, String thumbnail){
		videoName = name;
		videoURL = url;
		videoThumbnail = thumbnail;
	}
	
	public String getVideoName(){
		return videoName;
	}
	
	public String getVideoURL(){
		return videoURL;
	}
	
	public String getVideoThumnbnail(){
		return videoThumbnail;
	}

}
