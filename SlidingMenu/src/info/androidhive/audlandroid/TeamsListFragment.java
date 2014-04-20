package info.androidhive.audlandroid;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import info.androidhive.audlandroid.R;
import info.androidhive.audlandroid.adapter.ListAdapter;
import info.androidhive.audlandroid.adapter.TeamsListBaseAdapter;
import info.androidhive.audlandroid.interfaces.FragmentCallback;
import info.androidhive.audlandroid.model.TeamsListItem;
import info.androidhive.audlandroid.utils.ImageLoader;
import info.androidhive.audlandroid.utils.Utils;


import org.json.JSONArray;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TeamsListFragment extends Fragment {
	public OnTeamSelectedListener mCallback;
	JSONArray jsonResult = null;
    String response = null;
	
	public TeamsListFragment(){}
	
	public interface OnTeamSelectedListener {
        public void onTeamSelected(TeamsListItem team);
    }
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnTeamSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnTeamSelectedListener");
        }
    }
	
	public ArrayList<TeamsListItem> parseJSON(JSONArray jsonResult){
		ArrayList<TeamsListItem> teamsList = new ArrayList<TeamsListItem>();
		try {
			for (int i=0; i<jsonResult.length(); i++){
				teamsList.add(new TeamsListItem(jsonResult.getJSONArray(i).getString(0), jsonResult.getJSONArray(i).getString(1)));
			}
		} catch (Exception e) {
			Log.e("NewsListFragment", "Error when trying to create news objects from json : " + e.toString());
		}
		return teamsList;
	}
	public void startAsyncTask(final ListView listview, final Activity activity){
		final AUDLHttpRequest httpRequester = new AUDLHttpRequest(new FragmentCallback() {
			@Override
			public void onTaskFailure(){
				Utils.ServerError(activity);
			}
			@Override
			public void onTaskDone(String response) {
		        try{
		            jsonResult = new JSONArray(response);
		        } catch (Exception e) {
		        	Log.e("TeamsListFragment", "Response: " + response + ". Error creating json " + e.toString());
		        }
				
		        final ArrayList<TeamsListItem> teamsList = parseJSON(jsonResult);

		        final ArrayList<String> list = new ArrayList<String>();
		        for (int i = 0; i < teamsList.size(); ++i) {
		          list.add(teamsList.get(i).getTeamName());
		        }
		        
		        //final ListAdapter adapter = new ListAdapter(activity, android.R.layout.simple_list_item_1, list);
		        final TeamsListBaseAdapter adapter = new TeamsListBaseAdapter(activity, teamsList);        
		        listview.setAdapter(adapter);
		        
		        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		            @Override
		            public void onItemClick(AdapterView<?> parent, final View view,
		                int position, long id) {
		            	mCallback.onTeamSelected(teamsList.get(position));
		            }

		          });
			}
		});
		String serverURL = getResources().getString(R.string.ServerURL);
		httpRequester.execute(serverURL + "/Teams");
	}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		
		getActivity().getActionBar().setTitle("Teams");
		
		TextView txtView = (TextView) rootView.findViewById(R.id.list_header);
        txtView.setText("AUDL Teams");
		
		final ListView listview = (ListView) rootView.findViewById(R.id.listview);
		startAsyncTask(listview, getActivity());
		
        return rootView;
    }		
}
