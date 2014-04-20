package info.androidhive.audlandroid;

import info.androidhive.audlandroid.adapter.ScheduleListTabsPagerAdapter;
import info.androidhive.audlandroid.interfaces.FragmentCallback;
import info.androidhive.audlandroid.model.ScheduleListItem;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ScheduleListFragment extends Fragment {
	
	public ScheduleListFragment(){}
    
    HashMap<String, ArrayList<ScheduleListItem>> schedLists = new HashMap<String, ArrayList<ScheduleListItem>>();
	JSONArray jsonResult = null;
    String response = null;
	
	public HashMap<String, ArrayList<ScheduleListItem>> parseJSON(JSONArray jsonResult){
		HashMap<String, ArrayList<ScheduleListItem>> schedLists = new HashMap<String, ArrayList<ScheduleListItem>>();
		try {
			for (int i=0; i<jsonResult.length(); i++){
				ArrayList<ScheduleListItem> schedList = new ArrayList<ScheduleListItem>();
				schedLists.put(jsonResult.getJSONArray(i).getString(0), schedList);
				for(int j = 0; j < jsonResult.getJSONArray(i).getJSONArray(1).length(); j++){
					schedList.add(new ScheduleListItem(jsonResult.getJSONArray(i).getString(0), jsonResult.getJSONArray(i).getJSONArray(1).getJSONArray(j).getString(0), jsonResult.getJSONArray(i).getJSONArray(1).getJSONArray(j).getString(1), jsonResult.getJSONArray(i).getJSONArray(1).getJSONArray(j).getString(2), jsonResult.getJSONArray(i).getJSONArray(1).getJSONArray(j).getString(3), jsonResult.getJSONArray(i).getJSONArray(1).getJSONArray(j).getString(4), jsonResult.getJSONArray(i).getJSONArray(1).getJSONArray(j).getString(5)));
				}
			}
		} catch (Exception e) {
			Log.e("ScheduleListFragment", "Error when trying to create news objects from json : " + e.toString());
		}
		return schedLists;
	}
	
	public void startAsyncTask(final View rootView, final ScheduleListFragment frag){
		final AUDLHttpRequest httpRequester = new AUDLHttpRequest(new FragmentCallback() {			
			@Override
			public void onTaskDone(String response) {
		        try{
		            jsonResult = new JSONArray(response);
		        } catch (Exception e) {
		        	Log.e("ScheduleListFragment", "Response: " + response + ". Error creating json " + e.toString());
		        }
		        
		        schedLists = parseJSON(jsonResult);
		        
		        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.pager);
		        final ScheduleListTabsPagerAdapter adapter = new ScheduleListTabsPagerAdapter(frag.getActivity().getSupportFragmentManager(), schedLists);        
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
		});
        //httpRequester.execute("http://68.190.167.114:4000/News");
		String serverURL = getResources().getString(R.string.ServerURL);
		httpRequester.execute(serverURL + "/Schedule");
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		//new HttpRequestTask().execute("http://192.168.72.235:4000/teams");
        
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        startAsyncTask(rootView, this);
        
         
        return rootView;
    }
}
