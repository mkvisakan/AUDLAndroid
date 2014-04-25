package info.androidhive.audlandroid;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import info.androidhive.audlandroid.adapter.ScoreInfoPagerAdapter;
import info.androidhive.audlandroid.adapter.TeamInfoTabsPagerAdapter;
import info.androidhive.audlandroid.interfaces.FragmentCallback;
import info.androidhive.audlandroid.model.ScoreListItem;
import info.androidhive.audlandroid.model.TopStatsItem;
import info.androidhive.audlandroid.utils.Utils;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ScoresInfoFragment extends Fragment{
	
	public ScoresInfoFragment(){}
	
	private ViewPager viewPager;
    private ScoreInfoPagerAdapter mAdapter;
    
    private ScoreListItem score;
    private JSONArray jsonResult = null;
    private String response = null;
    private ArrayList<TopStatsItem> topStats;
    public ScoreListItem getScore(){
    	return score;
    }
    
    private ArrayList<TopStatsItem> parseJSON(JSONArray response){
    	ArrayList<TopStatsItem> list = new ArrayList<TopStatsItem>();
		   try{
			   JSONArray statsArray = response.getJSONArray(4);
			   JSONArray homeStatsArray = statsArray.getJSONArray(0);
			   JSONArray goalsArray = homeStatsArray.getJSONArray(0);
			   JSONArray assistsArray = homeStatsArray.getJSONArray(1);
			   JSONArray dropsArray = homeStatsArray.getJSONArray(2);
			   JSONArray tAsArray  = homeStatsArray.getJSONArray(3);
			   JSONArray DsArray = homeStatsArray.getJSONArray(4);
			   TopStatsItem home = new TopStatsItem(goalsArray.getString(1),goalsArray.getString(2),
					   								assistsArray.getString(1),assistsArray.getString(2),
					   								dropsArray.getString(1),dropsArray.getString(2),
					   								tAsArray.getString(1),tAsArray.getString(2),
					   								DsArray.getString(1),DsArray.getString(2));
			   list.add(home);
			   JSONArray awayStatsArray = statsArray.getJSONArray(1);
			   goalsArray = awayStatsArray.getJSONArray(0);
			   assistsArray = awayStatsArray.getJSONArray(1);
			   dropsArray = awayStatsArray.getJSONArray(2);
			   tAsArray  = awayStatsArray.getJSONArray(3);
			   DsArray = awayStatsArray.getJSONArray(4);
			   TopStatsItem away = new TopStatsItem(goalsArray.getString(1),goalsArray.getString(2),
							assistsArray.getString(1),assistsArray.getString(2),
							dropsArray.getString(1),dropsArray.getString(2),
							tAsArray.getString(1),tAsArray.getString(2),
							DsArray.getString(1),DsArray.getString(2));
			   list.add(away);
		   }catch(JSONException e){
			   
		   }
		   return list;
    }
    public void startAsyncTask(final View rootView,final ScoresInfoFragment frag){
    	final String homeTeamName = getArguments().getString("HOMETEAM");
    	final String awayTeamName = getArguments().getString("AWAYTEAM");
    	final String homeTeamID = getArguments().getString("HOMETEAMID");
    	final String date = getArguments().getString("DATE");
    	final String time = getArguments().getString("TIME");
    	final String awayTeamID = getArguments().getString("AWAYTEAMID");
    	final String homeTeamScore = getArguments().getString("HOMETEAMSCORE");
    	final String awayTeamScore = getArguments().getString("AWAYTEAMSCORE");
    	final String gameStatus = getArguments().getString("GAMESTATUS");
    	final AUDLHttpRequest httpRequester = new AUDLHttpRequest(new FragmentCallback(){
			@Override
			public void onTaskFailure() {
				Utils.ServerError(getActivity());
			}

			@Override
			public void onTaskDone(String response) {
				try{
					jsonResult = new JSONArray(response);
					topStats = parseJSON(jsonResult);
				}catch(Exception e){
					e.printStackTrace();
				}
				viewPager = (ViewPager) rootView.findViewById(R.id.detailed_scores_pager);
				ScoreListItem item = new ScoreListItem(homeTeamName,homeTeamID,awayTeamName,awayTeamID,date,time,
						homeTeamScore,awayTeamScore,gameStatus);
				mAdapter = new ScoreInfoPagerAdapter(frag.getActivity().getSupportFragmentManager(),topStats,item,frag.getActivity());
				viewPager.setAdapter(mAdapter);
				viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
					
					@Override
					public void onPageSelected(int arg0) {
						
					}
					
					@Override
					public void onPageScrolled(int arg0, float arg1, int arg2) {
						
					}
					
					@Override
					public void onPageScrollStateChanged(int arg0) {
						// TODO Auto-generated method stub
						
					}
				});
			}
    		
    	});
    	String serverURL = getResources().getString(R.string.ServerURL);
		httpRequester.execute(serverURL + "/Game/" + homeTeamID + "/" + date);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
        View rootView = inflater.inflate(R.layout.fragment_score_info, container, false);
        
        startAsyncTask(rootView, this);
         
        return rootView;
    }
    
}
