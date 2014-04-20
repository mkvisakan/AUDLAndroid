package info.androidhive.audlandroid;

import info.androidhive.audlandroid.adapter.NowListBaseAdapter;
import info.androidhive.audlandroid.interfaces.FragmentCallback;
import info.androidhive.audlandroid.model.Twitter;
import info.androidhive.audlandroid.utils.Utils;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

public class NowFragment extends Fragment {

	public NowFragment() {
	}
	SharedPreferences sharedPref;
	final static String ScreenName = "theaudl";
	final static String LOG_TAG = "audl";
	
	SwipeRefreshLayout swipeLayout;

	// download twitter timeline after first checking to see if there is a
	// network connection

	// converts a string of JSON data into a Twitter object
	private Twitter jsonToTwitter(String result) {

		Twitter twits = null;

		if (result != null && result.length() > 0) {

			try {
				Gson gson = new Gson();
				twits = gson.fromJson(result, Twitter.class);
			} catch (IllegalStateException ex) {
				Log.i("In this exception", "EXCEPTION");
			}
		}
		return twits;
	}
	
	public void startCacheHandler(final ListView listview, final Activity activity) {
		final EmptyRequest emptyRequest = new EmptyRequest(
				new FragmentCallback() {
					@Override
					public void onTaskDone(String response) {
						sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
						String oldResponse = sharedPref.getString(activity.getResources().getString(R.string.NowListCache), "");
						final Twitter twits = jsonToTwitter(oldResponse);
				
						// send the tweets to the adapter for rendering
						if(twits != null){
							final NowListBaseAdapter adapter = new NowListBaseAdapter(activity, twits);
					        listview.setAdapter(adapter);
					        swipeLayout.setRefreshing(false);
					        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					
					            @Override
					            public void onItemClick(AdapterView<?> parent, final View view,
					                int position, long id) {
					            	String text = twits.get(position).getText();
					            	int index = text.indexOf(".co");
					            	if(index != -1){
					            		Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(text.substring(text.lastIndexOf(" ", index) + 1, (text + " ").indexOf(" ", index))));
					            		startActivity(myIntent);
					            	}
					            }
					
					          });
						}
					}
				});
			emptyRequest.execute("empty");
		}

	public void startAsyncTask(final ListView listview, final Activity activity) {
		final TwitterRequest twitterDownloader = new TwitterRequest(
				new FragmentCallback() {
					@Override
					public void onTaskFailure(){
						Utils.ServerError(activity);
					}
					@Override
					public void onTaskDone(String response) {
						
						sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
						String oldResponse = sharedPref.getString(activity.getResources().getString(R.string.NowListCache), "");
						if(!oldResponse.equals(response)){
							
						
							if (response != null && response.length() > 0){
								sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
								SharedPreferences.Editor editor = sharedPref.edit();
				        	    editor.putString(activity.getResources().getString(R.string.NowListCache), response);
				        	    editor.commit();
							}
							final Twitter twits = jsonToTwitter(response);
	
							// send the tweets to the adapter for rendering
	
							final NowListBaseAdapter adapter = new NowListBaseAdapter(activity, twits);
					        listview.setAdapter(adapter);
					        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	
					            @Override
					            public void onItemClick(AdapterView<?> parent, final View view,
					                int position, long id) {
					            	String text = twits.get(position).getText();
					            	int index = text.indexOf(".co");
					            	if(index != -1){
					            		Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(text.substring(text.lastIndexOf(" ", index) + 1, (text + " ").indexOf(" ", index))));
					            		startActivity(myIntent);
					            	}
					            }
	
					          });
						}
				        swipeLayout.setRefreshing(false);
					}
				});
		twitterDownloader.execute(ScreenName);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.swipe_refresh_list, container,
				false);

		//TextView txtView = (TextView) rootView.findViewById(R.id.list_header);
		//txtView.setText("AUDL Twitter Feed");
		final ListView listview = (ListView) rootView.findViewById(R.id.listview);
		swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
		swipeLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
		swipeLayout.setOnRefreshListener(new OnRefreshListener(){
			@Override
			public void onRefresh() {
			    startAsyncTask(listview, getActivity());
			}
		}
		);
		
		startCacheHandler(listview, getActivity());
		
		startAsyncTask(listview, getActivity());
		return rootView;
	}
	
}
