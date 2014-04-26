package info.androidhive.audlandroid;

import info.androidhive.audlandroid.adapter.ScoreListBaseAdapter;
import info.androidhive.audlandroid.interfaces.OnScoreSelectedListener;
import info.androidhive.audlandroid.model.ScoreListItem;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class ScoresDivisionListFragment extends Fragment{
	private ArrayList<ArrayList<String>> list;
	public OnScoreSelectedListener mCallback;
	
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnScoreSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnScoreSelectedListener");
        }
    }
	
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		Bundle bundle = this.getArguments();
		list = new ArrayList<ArrayList<String>>();
		ArrayList<String> homeTeams = bundle.getStringArrayList("homeTeamList");
		list.add(homeTeams);
		ArrayList<String> homeTeamIDs = bundle.getStringArrayList("homeTeamIDList");
		list.add(homeTeamIDs);
		ArrayList<String> awayTeams = bundle.getStringArrayList("awayTeamList");
		list.add(awayTeams);
		ArrayList<String> awayTeamIDs = bundle.getStringArrayList("awayTeamIDList");
		list.add(awayTeamIDs);
		ArrayList<String> dates = bundle.getStringArrayList("dateList");
		list.add(dates);
		ArrayList<String> times = bundle.getStringArrayList("timeList");
		list.add(times);
		ArrayList<String> homeTeamScores = bundle.getStringArrayList("homeTeamScoreList");
		list.add(homeTeamScores);
		ArrayList<String> awayTeamScores = bundle.getStringArrayList("awayTeamScoreList");
		list.add(awayTeamScores);
		ArrayList<String> gameStatus = bundle.getStringArrayList("gameScoreList");
		list.add(gameStatus);
		View rootView = inflater.inflate(R.layout.fragment_list, container,false);
		ListView listView = (ListView) rootView.findViewById(R.id.listview);
		TextView textView = (TextView) rootView.findViewById(R.id.list_header);
		textView.setText(bundle.getString("divisionName"));
		final ScoreListBaseAdapter adapter = new ScoreListBaseAdapter(getActivity(),list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mCallback.onScoreSelected(new ScoreListItem(list.get(0).get(position),list.get(1).get(position),list.get(2).get(position),
						list.get(3).get(position),list.get(4).get(position),list.get(5).get(position),list.get(6).get(position),
						list.get(7).get(position),list.get(8).get(position)));
			}
			
		});
		return rootView;
	}
}
