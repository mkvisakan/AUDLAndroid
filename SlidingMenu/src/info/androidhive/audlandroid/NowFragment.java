package info.androidhive.audlandroid;

import info.androidhive.audlandroid.adapter.NowListBaseAdapter;
import info.androidhive.audlandroid.interfaces.FragmentCallback;
import info.androidhive.audlandroid.model.Twitter;
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

import com.google.gson.Gson;

public class NowFragment extends Fragment {

	public NowFragment() {
	}

	final static String ScreenName = "theaudl";
	final static String LOG_TAG = "audl";

	// download twitter timeline after first checking to see if there is a
	// network connection

	// converts a string of JSON data into a Twitter object
	private Twitter jsonToTwitter(String result) {

		Twitter twits = null;

		if (result != null && result.length() > 0) {

			try {
				Gson gson = new Gson();
				twits = gson.fromJson(result, Twitter.class);
			} catch (IllegalStateException ex) {
				Log.i("In this exception", "EXCEPTION");
			}
		}
		return twits;
	}

	public void startAsyncTask(final ListView listview, final Activity activity) {
		final TwitterRequest twitterDownloader = new TwitterRequest(
				new FragmentCallback() {
					@Override
					public void onTaskDone(String response) {

						final Twitter twits = jsonToTwitter(response);

						// send the tweets to the adapter for rendering

						final NowListBaseAdapter adapter = new NowListBaseAdapter(activity, twits);
				        listview.setAdapter(adapter);
				        
				        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				            @Override
				            public void onItemClick(AdapterView<?> parent, final View view,
				                int position, long id) {
				            	String text = twits.get(position).getText();
				            	int index = text.indexOf(".co");
				            	if(index != -1){
				            		Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(text.substring(text.lastIndexOf(" ", index) + 1, (text + " ").indexOf(" ", index))));
				            		startActivity(myIntent);
				            	}
				            }

				          });
					}
				});
		// httpRequester.execute("http://68.190.167.114:4000/News");
		twitterDownloader.execute(ScreenName);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_list, container,
				false);

		TextView txtView = (TextView) rootView.findViewById(R.id.list_header);
		txtView.setText("AUDL Twitter Feed");

		final ListView listview = (ListView) rootView
				.findViewById(R.id.listview);

		startAsyncTask(listview, getActivity());
		return rootView;
	}
}
