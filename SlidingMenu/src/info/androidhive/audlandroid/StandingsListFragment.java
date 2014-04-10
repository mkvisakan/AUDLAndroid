package info.androidhive.audlandroid;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import info.androidhive.audlandroid.R;
import info.androidhive.audlandroid.adapter.StandingsDivisionsPagerAdapter;
import info.androidhive.audlandroid.interfaces.FragmentCallback;
import info.androidhive.audlandroid.model.TeamRecordItem;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StandingsListFragment extends Fragment {
	
	JSONArray jsonResult = null;
	public StandingsListFragment(){}
	public String TAG = "info.androidhive.audlandroid.ScheduleListFragment";
	public ViewPager viewPager;
	public StandingsDivisionsPagerAdapter mAdapter;
	private ArrayList<String> divisionNames;
	private ArrayList<ArrayList<TeamRecordItem>> leagueRecords;
	@Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_standings, container, false);
        startAsyncTask(getActivity());
        return rootView;
    }
	public ArrayList<ArrayList<TeamRecordItem>> parseJSON(JSONArray array){
		divisionNames = new ArrayList<String>();
		leagueRecords = new ArrayList<ArrayList<TeamRecordItem>>();
		for(int i=0;i<array.length();i++){
			try {
				ArrayList<TeamRecordItem> divisionRecords = new ArrayList<TeamRecordItem>();
				JSONArray array2 = array.getJSONArray(i);
				divisionNames.add(array2.getString(0));
				for(int j=1;j<array2.length();j++){
					JSONArray record = array2.getJSONArray(j);
					TeamRecordItem recordItem = new TeamRecordItem(record.getString(0),record.getString(1),record.getString(2));
					divisionRecords.add(recordItem);
				}
				leagueRecords.add(divisionRecords);
			} catch (JSONException e) {
				Log.i(TAG,"JSON Exception" + e.toString());
			}
		}
		return leagueRecords;
	}
	private void startAsyncTask(FragmentActivity activity) {
		final AUDLHttpRequest httpRequester = new AUDLHttpRequest(new FragmentCallback() {
			
			@Override
			public void onTaskDone(String response) {
				try{
					jsonResult = new JSONArray(response);
				}catch(Exception e){
					Log.e(TAG,"Response" + response + ". Error creating json " + e.toString());
				}
				parseJSON(jsonResult);
				viewPager = (ViewPager) getActivity().findViewById(R.id.standings_pager);
		        mAdapter = new StandingsDivisionsPagerAdapter(getActivity().getSupportFragmentManager(),divisionNames,leagueRecords);
		 
		        viewPager.setAdapter(mAdapter);
		        
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
		httpRequester.execute("http://ec2-54-186-184-48.us-west-2.compute.amazonaws.com:4000/Standings");
	}
}
