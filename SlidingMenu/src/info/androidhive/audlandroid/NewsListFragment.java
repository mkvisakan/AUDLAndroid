package info.androidhive.audlandroid;

import info.androidhive.audlandroid.adapter.NewsListBaseAdapter;
import info.androidhive.audlandroid.interfaces.FragmentCallback;
import info.androidhive.audlandroid.model.NewsListItem;

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

public class NewsListFragment extends Fragment {
	
	SharedPreferences sharedPrefNews;
	ArrayList<NewsListItem> newsList;
	JSONArray jsonResult = null;
    String response = null;
	
	public NewsListFragment(){}
	
	public ArrayList<NewsListItem> parseJSON(JSONArray jsonResult){
		ArrayList<NewsListItem> newsList = new ArrayList<NewsListItem>();
		try {
			for (int i=1; i<jsonResult.length(); i++){
				newsList.add(new NewsListItem(jsonResult.getJSONArray(i).getString(0), jsonResult.getJSONArray(i).getString(1), jsonResult.getJSONArray(i).getString(2)));
			}
		} catch (Exception e) {
			Log.e("NewsListFragment", "Error when trying to create news objects from json : " + e.toString());
		}
		return newsList;
	}
	
	
	public void startCacheHandler(final ListView listview, final Activity activity) {
		final EmptyRequest emptyRequest = new EmptyRequest(
				new FragmentCallback() {
					@Override
					public void onTaskDone(String response) {
						sharedPrefNews = activity.getSharedPreferences(activity.getResources().getString(R.string.NewsListCache), Context.MODE_PRIVATE);
						String oldResponse = sharedPrefNews.getString(activity.getResources().getString(R.string.NewsListCache), "");
						
						if(!oldResponse.equals("")){
							try{
					            jsonResult = new JSONArray(oldResponse);
					        } catch (Exception e) {
					        	Log.e("NewsListFragment", "Response: " + oldResponse + ". Error creating json " + e.toString());
					        }
							newsList = parseJSON(jsonResult);
							
					        final ArrayList<String> list = new ArrayList<String>();
					        for (int i = 0; i < newsList.size(); ++i) {
					          list.add(newsList.get(i).getNewsHeadline());
					        }
					        
					        final NewsListBaseAdapter adapter = new NewsListBaseAdapter(activity, newsList);
					        listview.setAdapter(adapter);
					        
					        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	
					            @Override
					            public void onItemClick(AdapterView<?> parent, final View view,
					                int position, long id) {
					            	
					              Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsList.get(position).getNewsURL()));
					              startActivity(myIntent);
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
				sharedPrefNews = activity.getSharedPreferences(activity.getResources().getString(R.string.NewsListCache), Context.MODE_PRIVATE);
				String oldResponse = sharedPrefNews.getString(activity.getResources().getString(R.string.NewsListCache), "");
				if(!oldResponse.equals(response)){
			        try{
			            jsonResult = new JSONArray(response);
			        } catch (Exception e) {
			        	Log.e("NewsListFragment", "Response: " + response + ". Error creating json " + e.toString());
			        }
			        
			        if(jsonResult != null && response.length() > 0){
						SharedPreferences.Editor editor = sharedPrefNews.edit();
		        	    editor.putString(activity.getResources().getString(R.string.NewsListCache), response);
		        	    editor.commit();
			        }
			        newsList = parseJSON(jsonResult);
		
			        final ArrayList<String> list = new ArrayList<String>();
			        for (int i = 0; i < newsList.size(); ++i) {
			          list.add(newsList.get(i).getNewsHeadline());
			        }
			        
			        final NewsListBaseAdapter adapter = new NewsListBaseAdapter(activity, newsList);
			        listview.setAdapter(adapter);
			        
			        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		
			            @Override
			            public void onItemClick(AdapterView<?> parent, final View view,
			                int position, long id) {
			            	
			              Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsList.get(position).getNewsURL()));
			              startActivity(myIntent);
			            }
		
			          });
				}
			}
		});
		String serverURL = getResources().getString(R.string.ServerURL);
		httpRequester.execute(serverURL + "/News");
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        
        TextView txtView = (TextView) rootView.findViewById(R.id.list_header);
        txtView.setText("League News");
        
        final ListView listview = (ListView) rootView.findViewById(R.id.listview);
        startCacheHandler(listview, getActivity());
        startAsyncTask(listview, getActivity());         	
        return rootView;
    }
}

