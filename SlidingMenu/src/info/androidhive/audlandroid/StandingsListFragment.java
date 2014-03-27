package info.androidhive.audlandroid;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import info.androidhive.audlandroid.R;
import info.androidhive.audlandroid.adapter.DivisionsPagerAdapter;
import info.androidhive.audlandroid.adapter.TabsPagerAdapter;
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
	public DivisionsPagerAdapter mAdapter;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_standings, container, false);
        startAsyncTask(getActivity());
        return rootView;
    }
	private void parseJSON(JSONArray array){
		ArrayList<String> divisionNames = new ArrayList<String>();
		for(int i=0;i<array.length();i++){
			try {
				JSONArray array2 = array.getJSONArray(i);
				divisionNames.add(array2.getString(0));
			} catch (JSONException e) {
				Log.i(TAG,"JSON Exception" + e.toString());
			}
		}
		viewPager = (ViewPager) getActivity().findViewById(R.id.standings_pager);
        mAdapter = new DivisionsPagerAdapter(this.getActivity().getSupportFragmentManager(),divisionNames,array);
 
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
			}
		});
		httpRequester.execute("http://ec2-54-186-184-48.us-west-2.compute.amazonaws.com:4000/Standings");
	}
}
