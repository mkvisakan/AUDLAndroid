package info.androidhive.audlandroid;

import info.androidhive.audlandroid.adapter.StatsListTabsPagerAdapter;
import info.androidhive.audlandroid.interfaces.FragmentCallback;
import info.androidhive.audlandroid.model.StatsListItem;
import info.androidhive.audlandroid.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StatsListFragment extends Fragment {
	
	public StatsListFragment(){}
	
	SharedPreferences sharedPrefStats;
    HashMap<String, ArrayList<StatsListItem>> statsLists = new HashMap<String, ArrayList<StatsListItem>>();
	JSONObject jsonResult = null;
    String response = null;
	
	public HashMap<String, ArrayList<StatsListItem>> parseJSON(JSONObject jsonResult){
		HashMap<String, ArrayList<StatsListItem>> statsLists = new HashMap<String, ArrayList<StatsListItem>>();
		try {
			Iterator keys = jsonResult.keys();
			while(keys.hasNext()){
				ArrayList<StatsListItem> statsList = new ArrayList<StatsListItem>();
				String statName = (String) keys.next();
				statsLists.put(statName, statsList);
				JSONArray statValues = jsonResult.getJSONArray(statName);
				for(int i=0; i<statValues.length(); i++){
					statsList.add(new StatsListItem(statName, statValues.getJSONArray(i).getString(0), statValues.getJSONArray(i).getString(1), statValues.getJSONArray(i).getString(2)));
				}
			}
					
		} catch (Exception e) {
			Log.e("StatsListFragment", "Error when trying to create news objects from json : " + e.toString());
		}
		return statsLists;
	}
	
	public void startCacheHandler(final View rootView, final StatsListFragment frag) {
		final EmptyRequest emptyRequest = new EmptyRequest(
				new FragmentCallback() {
					@Override
					public void onTaskFailure(){
						Utils.ServerError(getActivity());
					}
					@Override
					public void onTaskDone(String response) {
						sharedPrefStats = frag.getActivity().getSharedPreferences(frag.getActivity().getResources().getString(R.string.StatsListCache), Context.MODE_PRIVATE);
						String oldResponse = sharedPrefStats.getString(frag.getActivity().getResources().getString(R.string.StatsListCache), "");
						
						if(!oldResponse.equals("")){
							try{
					            jsonResult = new JSONObject(oldResponse);
					        } catch (Exception e) {
					        	Log.e("StatsListFragment", "Response: " + oldResponse + ". Error creating json " + e.toString());
					        }
					        
					        statsLists = parseJSON(jsonResult);
					        
					        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.pager);
					        final StatsListTabsPagerAdapter adapter = new StatsListTabsPagerAdapter(frag.getActivity().getSupportFragmentManager(), statsLists);        
					        viewPager.setAdapter(adapter);
					        
					        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
					        	 
					            @Override
					            public void onPageSelected(int position) {
					                // on changing the page
					                // make respected tab selected
					               // actionBar.setSelectedNavigationItem(position);
					            }
					         
					            @Override
					            public void onPageScrolled(int arg0, float arg1, int arg2) {
					            }
					         
					            @Override
					            public void onPageScrollStateChanged(int arg0) {
					            }
					        });
						}
					}
				});
			emptyRequest.execute("empty");
	}
	
	public void startAsyncTask(final View rootView, final StatsListFragment frag){
		final AUDLHttpRequest httpRequester = new AUDLHttpRequest(new FragmentCallback() {
			@Override
			public void onTaskFailure(){
				Utils.ServerError(getActivity());
			}
			@Override
			public void onTaskDone(String response) {
				sharedPrefStats = frag.getActivity().getSharedPreferences(frag.getActivity().getResources().getString(R.string.StatsListCache), Context.MODE_PRIVATE);
				String oldResponse = sharedPrefStats.getString(frag.getActivity().getResources().getString(R.string.StatsListCache), "");
				if(!oldResponse.equals(response)){
					
			        try{
			            jsonResult = new JSONObject(response);
			        } catch (Exception e) {
			        	Log.e("StatsListFragment", "Response: " + response + ". Error creating json " + e.toString());
			        }
			        
			        if(jsonResult != null && response.length() > 0){
						SharedPreferences.Editor editor = sharedPrefStats.edit();
		        	    editor.putString(frag.getActivity().getResources().getString(R.string.StatsListCache), response);
		        	    editor.commit();
			        }
			        
			        statsLists = parseJSON(jsonResult);
			        
			        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.pager);
			        final StatsListTabsPagerAdapter adapter = new StatsListTabsPagerAdapter(frag.getActivity().getSupportFragmentManager(), statsLists);        
			        viewPager.setAdapter(adapter);
			        
			        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			        	 
			            @Override
			            public void onPageSelected(int position) {
			                // on changing the page
			                // make respected tab selected
			               // actionBar.setSelectedNavigationItem(position);
			            }
			         
			            @Override
			            public void onPageScrolled(int arg0, float arg1, int arg2) {
			            }
			         
			            @Override
			            public void onPageScrollStateChanged(int arg0) {
			            }
			        });
				}
			}
		});
		String serverURL = getResources().getString(R.string.ServerURL);
		httpRequester.execute(serverURL + "/Stats");
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        startCacheHandler(rootView, this);
        startAsyncTask(rootView, this);         
        return rootView;
    }
}
