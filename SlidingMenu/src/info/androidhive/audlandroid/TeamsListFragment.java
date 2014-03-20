package info.androidhive.audlandroid;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import info.androidhive.audlandroid.R;
import info.androidhive.audlandroid.model.TeamsListItem;


import org.json.JSONArray;

import android.support.v4.app.Fragment;
import android.content.Context;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TeamsListFragment extends Fragment {
	
	public TeamsListFragment(){}
	
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
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		
		TextView txtView = (TextView) rootView.findViewById(R.id.list_header);
        txtView.setText("AUDL Teams");
		
		final ListView listview = (ListView) rootView.findViewById(R.id.listview);
		
		AUDLHttpRequest httpRequester = new AUDLHttpRequest();
        httpRequester.execute("http://68.190.167.114:4000/Teams");
        JSONArray jsonResult = null;
        String response = null;

        try{
        	response = httpRequester.get();
            jsonResult = new JSONArray(response);
        } catch (Exception e) {
        	Log.e("TeamsListFragment", "Response: " + response + ". Error creating json " + e.toString());
        }
		
        ArrayList<TeamsListItem> teamsList = parseJSON(jsonResult);

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < teamsList.size(); ++i) {
          list.add(teamsList.get(i).getTeamName());
        }
        
        final TeamsListAdapter adapter = new TeamsListAdapter(this.getActivity(), android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        
         
        return rootView;
    }
	
	private class TeamsListAdapter extends ArrayAdapter<String> {

	    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

	    public TeamsListAdapter(Context context, int textViewResourceId, List<String> objects) {
	      super(context, textViewResourceId, objects);
	      for (int i = 0; i < objects.size(); ++i) {
	        mIdMap.put(objects.get(i), i);
	      }
	    }

	    @Override
	    public long getItemId(int position) {
	      String item = getItem(position);
	      return mIdMap.get(item);
	    }

	    @Override
	    public boolean hasStableIds() {
	      return true;
	    }
	  }
}
