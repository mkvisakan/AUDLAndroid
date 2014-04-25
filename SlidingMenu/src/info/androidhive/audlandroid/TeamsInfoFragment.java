package info.androidhive.audlandroid;



import info.androidhive.audlandroid.R;

import info.androidhive.audlandroid.adapter.TeamInfoTabsPagerAdapter;
import info.androidhive.audlandroid.interfaces.FragmentCallback;
import info.androidhive.audlandroid.model.ScoreListItem;
import info.androidhive.audlandroid.model.TeamsListItem;
import info.androidhive.audlandroid.utils.Utils;

import org.json.JSONArray;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TeamsInfoFragment extends Fragment {
	
	public TeamsInfoFragment(){}
	
	private ViewPager viewPager;
    private TeamInfoTabsPagerAdapter mAdapter;
    private TeamsListItem team;
    JSONArray jsonResult = null;
    String response = null;
    
    public TeamsListItem getTeam(){
    	return team;
    }
    
    public TeamsListItem parseJSON(JSONArray jsonResult, String team_name, String team_id){
    	TeamsListItem team = null;
		try {
			team = new TeamsListItem(team_name, team_id);
			JSONArray playersList = jsonResult.getJSONArray(0);
			JSONArray scheduleList = jsonResult.getJSONArray(1);
			JSONArray statsList = jsonResult.getJSONArray(2);
			JSONArray gamesList = jsonResult.getJSONArray(3);
			
			//add players
			Log.i("TeamsInfoFragment", "adding players...");
			for (int i=1; i<playersList.length(); i++){
				team.addPlayer(playersList.getJSONArray(i).getString(0), playersList.getJSONArray(i).getString(1));
			}
			Log.i("TeamsInfoFragment", "adding schedule...");
			//add schedule
			for (int i=2; i<scheduleList.length(); i++){
				team.addSchedule(scheduleList.getJSONArray(i).getString(2), scheduleList.getJSONArray(i).getString(3), scheduleList.getJSONArray(i).getString(0), scheduleList.getJSONArray(i).getString(1));
			}
			
			//add stats
			for (int i=1; i<statsList.length(); i++){
				String statsType = statsList.getJSONArray(i).getString(0);
				JSONArray statsData = statsList.getJSONArray(i).getJSONArray(1);
				for (int j=0; j<statsData.length(); j++){
					team.addStats(statsType, statsData.getJSONArray(j).getString(0), statsData.getJSONArray(j).getString(1));
				}
			}
			
			//add games
			for (int j=0; j<gamesList.length(); j++) {
				ScoreListItem scoreItem = new ScoreListItem(gamesList.getJSONArray(j).getString(0),gamesList.getJSONArray(j).getString(1),gamesList.getJSONArray(j).getString(2),
						gamesList.getJSONArray(j).getString(3),gamesList.getJSONArray(j).getString(4),gamesList.getJSONArray(j).getString(5),gamesList.getJSONArray(j).getString(6),
						gamesList.getJSONArray(j).getString(7),gamesList.getJSONArray(j).getString(8));
				team.addScores(scoreItem);
			}
			
		} catch (Exception e) {
			Log.e("TeamsInfoFragment", "Error when trying to create info objects from json : " + e.toString());
			e.printStackTrace();
		}
		return team;
	}
    
    public void startAsyncTask(final View rootView, final TeamsInfoFragment frag){
		final String team_id = getArguments().getString("TEAM_ID");
		final String team_name = getArguments().getString("TEAM_NAME");
		
		final AUDLHttpRequest httpRequester = new AUDLHttpRequest(new FragmentCallback() {
			@Override
			public void onTaskFailure(){
				Utils.ServerError(getActivity());
			}
			@Override
			public void onTaskDone(String response) {
		        Log.i("TeamsInfoFragment", "response : " + response);
		        try{
		            jsonResult = new JSONArray(response);
		            team = parseJSON(jsonResult, team_name, team_id);
		        } catch (Exception e) {
		        	Log.e("TeamsInfoFragment", "Response: " + response + ". Error creating json " + e.toString());
		        }
		        
		        //header
		        ActionBar act = getActivity().getActionBar();
		        act.setTitle(team.getTeamName());
		        
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
		String serverURL = getResources().getString(R.string.ServerURL);
		httpRequester.execute(serverURL + "/Teams/" + team_id);
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
        View rootView = inflater.inflate(R.layout.fragment_team_info, container, false);
        
        startAsyncTask(rootView, this);
         
        return rootView;
    }
}