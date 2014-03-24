package info.androidhive.audlandroid;



import info.androidhive.audlandroid.R;

import info.androidhive.audlandroid.adapter.TeamInfoTabsPagerAdapter;
import info.androidhive.audlandroid.model.TeamsListItem;
import org.json.JSONArray;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class TeamsInfoFragment extends Fragment {
	
	public TeamsInfoFragment(){}
	
	private ViewPager viewPager;
    private TeamInfoTabsPagerAdapter mAdapter;
    private TeamsListItem team;
    JSONArray jsonResult = null;
    String response = null;
    
    private String[] tabs = { "Players", "Schedule" };
    
    public void parseJSON(JSONArray jsonResult){
		try {
			JSONArray playersList = jsonResult.getJSONArray(0);
			JSONArray scheduleList = jsonResult.getJSONArray(1);
			JSONArray statsList = jsonResult.getJSONArray(2);
			//add players
			Log.i("TeamsInfoFragment", "adding players...");
			for (int i=1; i<playersList.length(); i++){
				team.addPlayer(playersList.getJSONArray(i).getString(0), playersList.getJSONArray(i).getString(1));
			}
			Log.i("TeamsInfoFragment", "adding schedule...");
			//add schedule
			for (int i=2; i<scheduleList.length(); i++){
				team.addSchedule(scheduleList.getJSONArray(i).getString(2), scheduleList.getJSONArray(i).getString(0), scheduleList.getJSONArray(i).getString(1));
			}
			//add stats
		} catch (Exception e) {
			Log.e("TeamsInfoFragment", "Error when trying to create info objects from json : " + e.toString());
			e.printStackTrace();
		}
	}
    
    public void startAsyncTask(final View rootView, final TeamsInfoFragment frag){
		String team_id = getArguments().getString("TEAM_ID");
		String team_name = getArguments().getString("TEAM_NAME");
		team = new TeamsListItem(team_name, team_id);
		Log.i("TeamsInfoFragment", "Fetching Details about team : " + team.getTeamName());
		
		final AUDLHttpRequest httpRequester = new AUDLHttpRequest(new FragmentCallback() {			
			@Override
			public void onTaskDone(String response) {
		        Log.i("TeamsInfoFragment", "response : " + response);
		        try{
		            jsonResult = new JSONArray(response);
		            parseJSON(jsonResult);
		        } catch (Exception e) {
		        	Log.e("TeamsInfoFragment", "Response: " + response + ". Error creating json " + e.toString());
		        }
		        
		        // Initilization
		        viewPager = (ViewPager) rootView.findViewById(R.id.pager);
		        mAdapter = new TeamInfoTabsPagerAdapter(frag.getActivity().getSupportFragmentManager(), team);
		 
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
		httpRequester.execute("http://ec2-54-186-184-48.us-west-2.compute.amazonaws.com:4000/Teams/" + team.getTeamId());
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
        View rootView = inflater.inflate(R.layout.fragment_team_info, container, false);
        
        startAsyncTask(rootView, this);
         
        return rootView;
    }
}