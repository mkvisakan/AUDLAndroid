package info.androidhive.audlandroid;

import info.androidhive.audlandroid.adapter.NewsListBaseAdapter;
import info.androidhive.audlandroid.adapter.ScheduleListBaseAdapter;
import info.androidhive.audlandroid.adapter.VideosListBaseAdapter;
import info.androidhive.audlandroid.interfaces.FragmentCallback;
import info.androidhive.audlandroid.model.NewsListItem;
import info.androidhive.audlandroid.model.ScheduleListItem;
import info.androidhive.audlandroid.model.VideosListItem;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
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

public class HomeFragment extends Fragment {
	private JSONArray jsonResult;
	
	public HomeFragment(){}
	
	public JSONArray getTopNItems(JSONArray inputArray, int N) throws JSONException{
		JSONArray jsonRes =new JSONArray();
		for (int i=0; i<inputArray.length() && i < N; i++){
			jsonRes.put(inputArray.get(i));
		}
		return jsonRes;
	}
	
	public void startAsyncTask(final View rootView, final Activity activity){
		final AUDLHttpRequest httpRequester = new AUDLHttpRequest(new FragmentCallback() {			
			@Override
			public void onTaskDone(String response) {
				try{
		            jsonResult = new JSONArray(response);
		        } catch (Exception e) {
		        	Log.e("HomeFragment", "Response: " + response + ". Error creating json " + e.toString());
		        }
				
				ArrayList<ScheduleListItem> schedList = new ArrayList<ScheduleListItem>();
				try {
					NewsListFragment newsFrag = new NewsListFragment();
					final ArrayList<NewsListItem> newsList = newsFrag.parseJSON(getTopNItems(jsonResult.getJSONArray(0), 3));
					VideosListFragment vidFrag = new VideosListFragment();
					final ArrayList<VideosListItem> videosList = vidFrag.parseJSON(getTopNItems(jsonResult.getJSONArray(1), 2));
					ScheduleListFragment schedFrag = new ScheduleListFragment();
					HashMap<String, ArrayList<ScheduleListItem>> schedMap = schedFrag.parseJSON(jsonResult.getJSONArray(2));
					for (String key : schedMap.keySet()){
						schedList.add(schedMap.get(key).get(0));
					}
					ListView news_listview = (ListView)rootView.findViewById(R.id.news_listview);
					
					final NewsListBaseAdapter newsAdapter = new NewsListBaseAdapter(activity, newsList);
			        news_listview.setAdapter(newsAdapter);
			        
			        news_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			            @Override
			            public void onItemClick(AdapterView<?> parent, final View view,
			                int position, long id) {
			              Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsList.get(position).getNewsURL()));
			              startActivity(myIntent);
			            }
			          });
			        
			        TextView newsTxtView = (TextView) rootView.findViewById(R.id.news_list_header);
			        newsTxtView.setText("Top News");
			        newsTxtView.setBackgroundColor(Color.LTGRAY);
			        newsTxtView.setTypeface(null, Typeface.BOLD_ITALIC);
			        
			        ListView videos_listview = (ListView)rootView.findViewById(R.id.videos_listview);
					
					final VideosListBaseAdapter videosAdapter = new VideosListBaseAdapter(activity, videosList);
			        videos_listview.setAdapter(videosAdapter);
			        
			        videos_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			            @Override
			            public void onItemClick(AdapterView<?> parent, final View view,
			                int position, long id) {
			            	Intent intent = new Intent(Intent.ACTION_VIEW);
			                intent.setData(Uri.parse(videosList.get(position).getVideoURL()));
			                startActivity(intent);
			            }
			          });
			        
			        TextView vidTxtView = (TextView) rootView.findViewById(R.id.videos_list_header);
			        vidTxtView.setText("Recent Videos");
			        vidTxtView.setBackgroundColor(Color.LTGRAY);
			        vidTxtView.setTypeface(null, Typeface.BOLD_ITALIC);
			        
			        ListView sched_listview = (ListView)rootView.findViewById(R.id.sched_listview);
			        final ScheduleListBaseAdapter schedAdapter = new ScheduleListBaseAdapter(activity, schedList);        
			        sched_listview.setAdapter(schedAdapter);
			        
			        TextView schedTxtView = (TextView) rootView.findViewById(R.id.sched_list_header);
			        schedTxtView.setText("Upcoming matches");
			        schedTxtView.setBackgroundColor(Color.LTGRAY);
			        schedTxtView.setTypeface(null, Typeface.BOLD_ITALIC);
				} catch (Exception e) {
					Log.e("HomeFragment", "Error extracting results ! " + e.toString());
				}
				
				
			}
		});
		httpRequester.execute("http://ec2-54-186-184-48.us-west-2.compute.amazonaws.com:4000/Home");
	}
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.home_section, container, false);
        
        startAsyncTask(rootView, getActivity());         	
        return rootView;    
    }
}
