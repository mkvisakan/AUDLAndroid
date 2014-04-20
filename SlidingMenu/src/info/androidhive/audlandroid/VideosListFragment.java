package info.androidhive.audlandroid;

import info.androidhive.audlandroid.adapter.VideosListBaseAdapter;
import info.androidhive.audlandroid.interfaces.FragmentCallback;
import info.androidhive.audlandroid.model.VideosListItem;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class VideosListFragment extends Fragment {
	
	SharedPreferences sharedPrefVideos;
	ArrayList<VideosListItem> videosList;
	JSONArray jsonResult = null;
    String response = null;
	
	public VideosListFragment(){}
	
	public ArrayList<VideosListItem> parseJSON(JSONArray jsonResult){
		ArrayList<VideosListItem> videosList = new ArrayList<VideosListItem>();
		try {
			for (int i=0; i<jsonResult.length(); i++){
				videosList.add(new VideosListItem(jsonResult.getJSONArray(i).getString(0), jsonResult.getJSONArray(i).getString(1), jsonResult.getJSONArray(i).getString(2)));
			}
		} catch (Exception e) {
			Log.e("VideosListFragment", "Error when trying to create video objects from json : " + e.toString());
		}
		return videosList;
	}
	
	public void startCacheHandler(final ListView listview, final Activity activity) {
		final EmptyRequest emptyRequest = new EmptyRequest(
				new FragmentCallback() {
					@Override
					public void onTaskDone(String response) {
						sharedPrefVideos = activity.getSharedPreferences(activity.getResources().getString(R.string.VideoListCache), Context.MODE_PRIVATE);
						String oldResponse = sharedPrefVideos.getString(activity.getResources().getString(R.string.VideoListCache), "");
						
						if(!oldResponse.equals("")){
							try{
					            jsonResult = new JSONArray(oldResponse);
					        } catch (Exception e) {
					        	Log.e("VideosListFragment", "Response: " + oldResponse + ". Error creating json " + e.toString());
					        }
					        videosList = parseJSON(jsonResult);
					        
					        final VideosListBaseAdapter adapter = new VideosListBaseAdapter(activity, videosList);        
					        listview.setAdapter(adapter);
					        
					        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					            @Override
					            public void onItemClick(AdapterView<?> parent, final View view,
					                int position, long id) {
					            	Intent intent = new Intent(Intent.ACTION_VIEW);
					                intent.setData(Uri.parse(videosList.get(position).getVideoURL()));
					                startActivity(intent);
					            }
					          });
						}
					}
				});
			emptyRequest.execute("empty");
	}
	
	public void startAsyncTask(final ListView listview, final Activity activity){
		final AUDLHttpRequest httpRequester = new AUDLHttpRequest(new FragmentCallback() {			
			@Override
			public void onTaskDone(String response) {
				sharedPrefVideos = activity.getSharedPreferences(activity.getResources().getString(R.string.VideoListCache), Context.MODE_PRIVATE);
				String oldResponse = sharedPrefVideos.getString(activity.getResources().getString(R.string.VideoListCache), "");
				if(!oldResponse.equals(response)){
					
			        try{
			            jsonResult = new JSONArray(response);
			        } catch (Exception e) {
			        	Log.e("VideosListFragment", "Response: " + response + ". Error creating json " + e.toString());
			        }
			        
			        if(jsonResult != null && response.length() > 0){
						SharedPreferences.Editor editor = sharedPrefVideos.edit();
		        	    editor.putString(activity.getResources().getString(R.string.VideoListCache), response);
		        	    editor.commit();
			        }
			        
			        videosList = parseJSON(jsonResult);
			        
			        final VideosListBaseAdapter adapter = new VideosListBaseAdapter(activity, videosList);        
			        listview.setAdapter(adapter);
			        
			        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	
			            @Override
			            public void onItemClick(AdapterView<?> parent, final View view,
			                int position, long id) {
			            	Intent intent = new Intent(Intent.ACTION_VIEW);
			                intent.setData(Uri.parse(videosList.get(position).getVideoURL()));
			                startActivity(intent);
			            }
			          });
				}
			}
		});
        //httpRequester.execute("http://68.190.167.114:4000/News");
		String serverURL = getResources().getString(R.string.ServerURL);
		httpRequester.execute(serverURL + "/Videos");
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
		View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		TextView txtView = (TextView) rootView.findViewById(R.id.list_header);
        txtView.setText("League Videos");
        
        final ListView listview = (ListView) rootView.findViewById(R.id.listview);
        startCacheHandler(listview, getActivity());
        startAsyncTask(listview, getActivity());
        return rootView;
    }
}
