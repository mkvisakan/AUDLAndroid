package info.androidhive.audlandroid;

import info.androidhive.audlandroid.adapter.FaqAdapter;

import java.util.Arrays;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class FaqFragment extends Fragment {
	String [] mQuestions;
	String [] mAnswers;
	public FaqFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		TextView txtView = (TextView) rootView.findViewById(R.id.list_header);
        txtView.setText("Frequently Asked Questions");
        
        mQuestions = getResources().getStringArray(R.array.faqQuestions);
        mAnswers = getResources().getStringArray(R.array.faqAnswers);
        
        final ListView listview = (ListView) rootView.findViewById(R.id.listview);
        final FaqAdapter adapter = new FaqAdapter(this.getActivity(), Arrays.asList(mQuestions), Arrays.asList(mAnswers));
        listview.setAdapter(adapter);
        return rootView;
	}
}
