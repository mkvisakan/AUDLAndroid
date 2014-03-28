package info.androidhive.audlandroid;

import info.androidhive.audlandroid.adapter.ScoreListBaseAdapter;
import info.androidhive.audlandroid.model.ScoreListItem;

import java.util.ArrayList;

import com.google.gson.Gson;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ScoresDivisionListFragment extends Fragment{
	private String TAG = "info.androidhive.audlandroid.ScoresDivisionListFragment";
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		Gson gson = new Gson();
		Bundle bundle = this.getArguments();
		ArrayList<ScoreListItem> list = new ArrayList<ScoreListItem>();
		ArrayList<String> scoreList = bundle.getStringArrayList("ScoreItemList");
		for(int i=0;i<scoreList.size();i++){
			list.add((ScoreListItem) gson.fromJson(scoreList.get(i),ScoreListItem.class));
		}
		View rootView = inflater.inflate(R.layout.fragment_scores, container,false);
		ListView listView = (ListView) rootView.findViewById(R.id.scoreList);
		final ScoreListBaseAdapter adapter = new ScoreListBaseAdapter(getActivity(),list);
		listView.setAdapter(adapter);
		return rootView;
	}
}
