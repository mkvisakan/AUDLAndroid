package info.androidhive.audlandroid;

import info.androidhive.audlandroid.adapter.StatsListBaseAdapter;
import info.androidhive.audlandroid.interfaces.FragmentCallback;
import info.androidhive.audlandroid.model.StatsListItem;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class StatFragment extends Fragment {
	
	ArrayList<StatsListItem> statsList;
	JSONObject jsonResult = null;
    String response = null;
	
	public StatFragment(){}
	
	public ArrayList<StatsListItem> parseJSON(JSONObject jsonResult, String statName){
		ArrayList<StatsListItem> statsList = new ArrayList<StatsListItem>();
		try {
			JSONArray statValues = jsonResult.getJSONArray(statName);
			for(int i=0; i<statValues.length(); i++){
				statsList.add(new StatsListItem(statName, statValues.getJSONArray(i).getString(0), statValues.getJSONArray(i).getString(1), statValues.getJSONArray(i).getString(2)));
			}
		} catch (Exception e) {
			Log.e("StatsListFragment " + statName, "Error when trying to create news objects from json : " + e.toString());
		}
		return statsList;
	}
	
	public void startAsyncTask(final ListView listview, final Activity activity, final String stat){
		final AUDLHttpRequest httpRequester = new AUDLHttpRequest(new FragmentCallback() {			
			@Override
			public void onTaskDone(String response) {
		        try{
		            jsonResult = new JSONObject(response);
		        } catch (Exception e) {
		        	Log.e("ScheduleListFragment " + stat, "Response: " + response + ". Error creating json " + e.toString());
		        }
		        
		        statsList = parseJSON(jsonResult, stat);
		        
		        final StatsListBaseAdapter adapter = new StatsListBaseAdapter(activity, statsList);        
		        listview.setAdapter(adapter);
		        
		        /*listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		            @Override
		            public void onItemClick(AdapterView<?> parent, final View view,
		                int position, long id) {
		            	Intent intent = new Intent(Intent.ACTION_VIEW);
		                intent.setData(Uri.parse(videosList.get(position).getVideoURL()));
		                startActivity(intent);
		            }
		          });*/
			}
		});
        //httpRequester.execute("http://68.190.167.114:4000/News");
        httpRequester.execute("http://ec2-54-186-184-48.us-west-2.compute.amazonaws.com:4000/Stats");
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		String stat_name = getArguments().getString("STAT_NAME");
		View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		TextView txtView = (TextView) rootView.findViewById(R.id.list_header);
        txtView.setText(stat_name);
        
        final ListView listview = (ListView) rootView.findViewById(R.id.listview);
        startAsyncTask(listview, getActivity(), stat_name);
        return rootView;
    }
}
