package info.androidhive.audlandroid;

import info.androidhive.audlandroid.adapter.ListAdapter;

import java.util.ArrayList;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class SettingsFragment extends Fragment {
	
	public SettingsFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		TextView txtView = (TextView) rootView.findViewById(R.id.list_header);
        txtView.setText("Settings");
        
        final ListView listview = (ListView) rootView.findViewById(R.id.listview);
        ArrayList<String> sendFeedback = new ArrayList<String>();
        sendFeedback.add("Send Feedback");
        final ListAdapter adapter = new ListAdapter(this.getActivity(), android.R.layout.simple_list_item_1, sendFeedback);
        listview.setAdapter(adapter);
        
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                int position, long id) {
            	Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("mailto:arjunsesh@gmail.com"));
                startActivity(intent);
            }
    });
        return rootView;
	}
}
