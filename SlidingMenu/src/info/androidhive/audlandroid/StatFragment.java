package info.androidhive.audlandroid;

import info.androidhive.audlandroid.adapter.StatsListBaseAdapter;
import info.androidhive.audlandroid.model.StatsListItem;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class StatFragment extends Fragment {
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		String stat_name = getArguments().getString("STAT_NAME");
		ArrayList<StatsListItem> stat_data = getArguments().getParcelableArrayList("STAT_DATA");
		
		View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		TextView txtView = (TextView) rootView.findViewById(R.id.list_header);
        txtView.setText(stat_name);
        
        final ListView listview = (ListView) rootView.findViewById(R.id.listview);
        
        final StatsListBaseAdapter adapter = new StatsListBaseAdapter(getActivity(), stat_data);
        listview.setAdapter(adapter);
        return rootView;
    }
}
