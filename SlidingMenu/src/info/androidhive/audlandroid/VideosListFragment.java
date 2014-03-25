package info.androidhive.audlandroid;

import java.util.ArrayList;

import org.json.JSONArray;

import info.androidhive.audlandroid.R;
import info.androidhive.audlandroid.adapter.TeamsListBaseAdapter;
import info.androidhive.audlandroid.adapter.VideosListBaseAdapter;
import info.androidhive.audlandroid.interfaces.FragmentCallback;
import info.androidhive.audlandroid.model.NewsListItem;
import info.androidhive.audlandroid.model.VideosListItem;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class VideosListFragment extends Fragment {
	
	ArrayList<VideosListItem> videosList;
	JSONArray jsonResult = null;
    String response = null;
	
	public VideosListFragment(){}
	
	public ArrayList<VideosListItem> parseJSON(JSONArray jsonResult){
		ArrayList<VideosListItem> videosList = new ArrayList<VideosListItem>();
		try {
			for (int i=1; i<jsonResult.length(); i++){
				videosList.add(new VideosListItem(jsonResult.getJSONArray(i).getString(0), jsonResult.getJSONArray(i).getString(1), jsonResult.getJSONArray(i).getString(2)));
			}
		} catch (Exception e) {
			Log.e("VideosListFragment", "Error when trying to create news objects from json : " + e.toString());
		}
		return videosList;
	}
	
	public void startAsyncTask(final ListView listview, final Activity activity){
		final AUDLHttpRequest httpRequester = new AUDLHttpRequest(new FragmentCallback() {			
			@Override
			public void onTaskDone(String response) {
		        try{
		            jsonResult = new JSONArray(response);
		        } catch (Exception e) {
		        	Log.e("VideosListFragment", "Response: " + response + ". Error creating json " + e.toString());
		        }
		        
		        videosList = parseJSON(jsonResult);

		        final ArrayList<String> list = new ArrayList<String>();
		        
		        final VideosListBaseAdapter adapter = new VideosListBaseAdapter(activity, videosList);        
		        listview.setAdapter(adapter);
		        
		        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		            @Override
		            public void onItemClick(AdapterView<?> parent, final View view,
		                int position, long id) {
		            	Intent intent = new Intent(Intent.ACTION_VIEW);
		                intent.setData(Uri.parse(videosList.get(position).getVideoURL()));
		                startActivity(intent);
		            }
		          });
			}
		});
        //httpRequester.execute("http://68.190.167.114:4000/News");
        httpRequester.execute("http://ec2-54-186-184-48.us-west-2.compute.amazonaws.com:4000/Videos");
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
		View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		TextView txtView = (TextView) rootView.findViewById(R.id.list_header);
        txtView.setText("League Videos");
        
        final ListView listview = (ListView) rootView.findViewById(R.id.listview);
        startAsyncTask(listview, getActivity());
        return rootView;
    }
}
