package info.androidhive.audlandroid;

import info.androidhive.audlandroid.utils.ImageLoader;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ScoreGraphFragment extends Fragment{
	
	private ImageLoader imageLoader;
	public ScoreGraphFragment(Activity a){
		imageLoader = new ImageLoader(a.getApplicationContext());
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_score_graph, container,false);
		Bundle args = this.getArguments();
		String teamID = args.getString("HOMEID");
		String date = args.getString("DATE");
		ImageView graph = (ImageView) rootView.findViewById(R.id.graph);
		String serverURL = getResources().getString(R.string.ServerURL);
		imageLoader.DisplayImage(serverURL + "/Game/" + teamID + "/" + date + "/graph", graph);
		return rootView;
	}
	
	
}
