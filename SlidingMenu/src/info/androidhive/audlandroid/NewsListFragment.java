package info.androidhive.audlandroid;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;

import info.androidhive.audlandroid.R;
import info.androidhive.audlandroid.AUDLHttpRequest;
import info.androidhive.audlandroid.adapter.NewsListBaseAdapter;
import info.androidhive.audlandroid.interfaces.FragmentCallback;
import info.androidhive.audlandroid.model.NewsListItem;
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
import android.widget.ListView;
import android.widget.TextView;

public class NewsListFragment extends Fragment {
	
	ArrayList<NewsListItem> newsList;
	JSONArray jsonResult = null;
    String response = null;
	
	public NewsListFragment(){}
	
	public ArrayList<NewsListItem> parseJSON(JSONArray jsonResult){
		ArrayList<NewsListItem> newsList = new ArrayList<NewsListItem>();
		try {
			for (int i=1; i<jsonResult.length(); i++){
				newsList.add(new NewsListItem(jsonResult.getJSONArray(i).getString(0), jsonResult.getJSONArray(i).getString(1), jsonResult.getJSONArray(i).getString(2)));
			}
		} catch (Exception e) {
			Log.e("NewsListFragment", "Error when trying to create news objects from json : " + e.toString());
		}
		return newsList;
	}
	
	
	public void startAsyncTask(final ListView listview, final Activity activity){
		final AUDLHttpRequest httpRequester = new AUDLHttpRequest(new FragmentCallback() {			
			@Override
			public void onTaskDone(String response) {
		        try{
		            jsonResult = new JSONArray(response);
		        } catch (Exception e) {
		        	Log.e("NewsListFragment", "Response: " + response + ". Error creating json " + e.toString());
		        }
		        
		        newsList = parseJSON(jsonResult);

		        final ArrayList<String> list = new ArrayList<String>();
		        for (int i = 0; i < newsList.size(); ++i) {
		          list.add(newsList.get(i).getNewsHeadline());
		        }
		        
		        final NewsListBaseAdapter adapter = new NewsListBaseAdapter(activity, newsList);
		        listview.setAdapter(adapter);
		        
		        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		            @Override
		            public void onItemClick(AdapterView<?> parent, final View view,
		                int position, long id) {
		            	
		              Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsList.get(position).getNewsURL()));
		              startActivity(myIntent);
		            }

		          });
			}
		});
        //httpRequester.execute("http://68.190.167.114:4000/News");
        httpRequester.execute("http://ec2-54-186-184-48.us-west-2.compute.amazonaws.com:4000/News");
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        
        TextView txtView = (TextView) rootView.findViewById(R.id.list_header);
        txtView.setText("League News");
        
        final ListView listview = (ListView) rootView.findViewById(R.id.listview);
        
        startAsyncTask(listview, getActivity());         	
        return rootView;
    }
}

