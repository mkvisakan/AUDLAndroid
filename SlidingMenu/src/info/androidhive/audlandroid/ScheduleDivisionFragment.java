package info.androidhive.audlandroid;

import info.androidhive.audlandroid.adapter.ScheduleListBaseAdapter;
import info.androidhive.audlandroid.model.ScheduleListItem;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class ScheduleDivisionFragment extends Fragment {
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		String division_name = getArguments().getString("DIVISION_NAME");
		ArrayList<ScheduleListItem> division_data = getArguments().getParcelableArrayList("DIVISION_DATA");
		
		View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		TextView txtView = (TextView) rootView.findViewById(R.id.list_header);
        txtView.setText(division_name);
        
        final ListView listview = (ListView) rootView.findViewById(R.id.listview);
        
        final ScheduleListBaseAdapter adapter = new ScheduleListBaseAdapter(getActivity(), division_data);
        listview.setAdapter(adapter);
        return rootView;
    }
}
