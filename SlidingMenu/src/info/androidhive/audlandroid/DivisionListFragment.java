package info.androidhive.audlandroid;

import info.androidhive.audlandroid.model.TeamRecordItem;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DivisionListFragment extends Fragment{
	private JSONArray divisionArray;
	private ArrayList<TeamRecordItem> divisionRecords;
	public DivisionListFragment(){}
	
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		Bundle bundle = this.getArguments();
		try {
			divisionArray = new JSONArray(bundle.getString("DivisionArray"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		View rootView = inflater.inflate(R.layout.fragment_division, container,false);
		TableLayout divisionTable = (TableLayout) rootView.findViewById(R.id.standingsLayout);
		divisionRecords = new ArrayList<TeamRecordItem>();
		for(int i=1;i<divisionArray.length();i++){
			try {
				TeamRecordItem record = new TeamRecordItem(divisionArray.getJSONArray(i).getString(0),
						divisionArray.getJSONArray(i).getInt(1),divisionArray.getJSONArray(i).getInt(2));
				divisionRecords.add(record);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		for(int i=0;i<divisionRecords.size();i++){
			TeamRecordItem record = divisionRecords.get(i);
			TableRow row = new TableRow(this.getActivity());
			TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
			row.setLayoutParams(lp);
			TextView tv1= new TextView(getActivity());
			TextView tv2 = new TextView(getActivity());
			TextView tv3 = new TextView(getActivity());
			tv1.setText(record.getTeamName());
			tv2.setText("" + record.getWins());
			tv3.setText("" + record.getLosses());
			row.addView(tv1);
			row.addView(tv2);
			row.addView(tv3);
			divisionTable.addView(row, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		}
		return rootView;
	}
}
