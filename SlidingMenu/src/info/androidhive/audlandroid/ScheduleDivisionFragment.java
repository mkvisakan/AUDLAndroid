package info.androidhive.audlandroid;

import info.androidhive.audlandroid.adapter.ScheduleListBaseAdapter;
import info.androidhive.audlandroid.adapter.VideosListBaseAdapter;
import info.androidhive.audlandroid.interfaces.FragmentCallback;
import info.androidhive.audlandroid.model.ScheduleListItem;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
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

public class ScheduleDivisionFragment extends Fragment {
	
	ArrayList<ScheduleListItem> scheduleList;
	JSONArray jsonResult = null;
    String response = null;
	
	public ScheduleDivisionFragment(){}
	
	public ArrayList<ScheduleListItem> parseJSON(JSONArray jsonResult, String divisionName){
		ArrayList<ScheduleListItem> schedList = new ArrayList<ScheduleListItem>();
		try {
			for (int i=0; i<jsonResult.length(); i++){
				if(jsonResult.getJSONArray(i).getString(0).equals(divisionName)){
					for(int j = 0; j < jsonResult.getJSONArray(i).getJSONArray(1).length(); j++){
						schedList.add(new ScheduleListItem(jsonResult.getJSONArray(i).getString(0), jsonResult.getJSONArray(i).getJSONArray(1).getJSONArray(j).getString(0), jsonResult.getJSONArray(i).getJSONArray(1).getJSONArray(j).getString(1), jsonResult.getJSONArray(i).getJSONArray(1).getJSONArray(j).getString(2), jsonResult.getJSONArray(i).getJSONArray(1).getJSONArray(j).getString(3), jsonResult.getJSONArray(i).getJSONArray(1).getJSONArray(j).getString(4), jsonResult.getJSONArray(i).getJSONArray(1).getJSONArray(j).getString(5)));
					}
				}
			}
		} catch (Exception e) {
			Log.e("ScheduleListFragment " + divisionName, "Error when trying to create news objects from json : " + e.toString());
		}
		return schedList;
	}
	
	public void startAsyncTask(final ListView listview, final Activity activity, final String division){
		final AUDLHttpRequest httpRequester = new AUDLHttpRequest(new FragmentCallback() {			
			@Override
			public void onTaskDone(String response) {
		        try{
		            jsonResult = new JSONArray(response);
		        } catch (Exception e) {
		        	Log.e("ScheduleListFragment " + division, "Response: " + response + ". Error creating json " + e.toString());
		        }
		        
		        scheduleList = parseJSON(jsonResult, division);
		        
		        final ScheduleListBaseAdapter adapter = new ScheduleListBaseAdapter(activity, scheduleList);        
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
        httpRequester.execute("http://ec2-54-186-184-48.us-west-2.compute.amazonaws.com:4000/Schedule");
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		String division_name = getArguments().getString("DIVISION_NAME");
		View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		TextView txtView = (TextView) rootView.findViewById(R.id.list_header);
        txtView.setText(division_name);
        
        final ListView listview = (ListView) rootView.findViewById(R.id.listview);
        startAsyncTask(listview, getActivity(), division_name);
        return rootView;
    }
}
