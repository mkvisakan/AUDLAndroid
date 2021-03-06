package info.androidhive.audlandroid;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import info.androidhive.audlandroid.R;
import info.androidhive.audlandroid.adapter.ScoreDivisionsPagerAdapter;
import info.androidhive.audlandroid.interfaces.FragmentCallback;
import info.androidhive.audlandroid.model.ScoreListItem;
import info.androidhive.audlandroid.utils.Utils;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ScoresListFragment extends Fragment {
	private String TAG = "info.androidhive.audlandroid.model.ScoresListFragment";
	public ScoresListFragment(){}
	public ViewPager viewPager;
	public ScoreDivisionsPagerAdapter mAdapter;
	private ArrayList<String> divisionNames;
	private ArrayList<ArrayList<ScoreListItem>> leagueScores;
	private JSONArray jsonResult;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_scores, container, false);
        startAsyncTask(getActivity(),rootView);
        return rootView;
    }
	
	public ArrayList<ArrayList<ScoreListItem>> parseJSON(JSONArray array){
		divisionNames = new ArrayList<String>();
		leagueScores = new ArrayList<ArrayList<ScoreListItem>>(); 
		for(int i=0;i<array.length();i++){
			ArrayList<ScoreListItem> divisionScores = new ArrayList<ScoreListItem>();
			try {
				JSONArray array2 = array.getJSONArray(i);
				divisionNames.add(array2.getString(0));
				JSONArray array3 = array2.getJSONArray(1);
				for(int j=0;j<array3.length();j++){
					ScoreListItem scoreItem = new ScoreListItem(array3.getJSONArray(j).getString(0),array3.getJSONArray(j).getString(1),array3.getJSONArray(j).getString(2),
							array3.getJSONArray(j).getString(3),array3.getJSONArray(j).getString(4),array3.getJSONArray(j).getString(5),array3.getJSONArray(j).getString(6),
							array3.getJSONArray(j).getString(7),array3.getJSONArray(j).getString(8),array3.getJSONArray(j).getString(9));
					divisionScores.add(scoreItem);
				}
				leagueScores.add(divisionScores);
			} catch (JSONException e) {
				Log.i(TAG,"JSON Exception");
			}
		}
		return leagueScores;
	}
		
	private void startAsyncTask(final FragmentActivity activity,final View rootView){
		final AUDLHttpRequest httpRequester = new AUDLHttpRequest(new FragmentCallback(){
			@Override
			public void onTaskDone(String response) {
					try{
						jsonResult = new JSONArray(response);
					}catch(JSONException e){
						e.printStackTrace();
					}

					parseJSON(jsonResult);
					viewPager = (ViewPager) rootView.findViewById(R.id.scores_pager);
					mAdapter = new ScoreDivisionsPagerAdapter(activity.getSupportFragmentManager(),divisionNames,leagueScores);
					viewPager.setAdapter(mAdapter);
			        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			        	 
			            @Override
			            public void onPageSelected(int position) {
			              
			            }
			         
			            @Override
			            public void onPageScrolled(int arg0, float arg1, int arg2) {
			            	
			            }
			         
			            @Override
			            public void onPageScrollStateChanged(int arg0) {
			            
			            }
			        });
			}
			@Override
			public void onTaskFailure() {
				Utils.ServerError(getActivity());
			}
		});
		String serverURL = getResources().getString(R.string.ServerURL);
		httpRequester.execute(serverURL + "/Scores");
	}
	
}
