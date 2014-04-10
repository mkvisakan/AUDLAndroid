package info.androidhive.audlandroid;

import info.androidhive.audlandroid.adapter.ListAdapter;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class ToUFragment extends Fragment {
	String ToU;
	public ToUFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		TextView txtView = (TextView) rootView.findViewById(R.id.list_header);
        txtView.setText("Terms of Use");
        
        final ListView listview = (ListView) rootView.findViewById(R.id.listview);
        
        ToU = getString(R.string.terms_of_use);
        ArrayList<String> lists = new ArrayList<String>();
        lists.add(ToU);
        final ListAdapter adapter = new ListAdapter(this.getActivity(), android.R.layout.simple_list_item_1, lists);
        listview.setAdapter(adapter);
        return rootView;
	}
}
