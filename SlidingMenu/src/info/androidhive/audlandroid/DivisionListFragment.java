package info.androidhive.audlandroid;

import info.androidhive.audlandroid.model.TeamRecordItem;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
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

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class DivisionListFragment extends Fragment{
	private ArrayList<String> divisionTeamNames;
	private ArrayList<String> divisionTeamWins;
	private ArrayList<String> divisionTeamLosses;
	public DivisionListFragment(){}
	
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		Bundle bundle = this.getArguments();
		divisionTeamNames = bundle.getStringArrayList("divisionTeamNames");
		divisionTeamWins = bundle.getStringArrayList("divisionTeamWins");
		divisionTeamLosses = bundle.getStringArrayList("divisionTeamLosses");
		View rootView = inflater.inflate(R.layout.fragment_division, container,false);
		TableLayout divisionTable = (TableLayout) rootView.findViewById(R.id.standingsLayout);
		for(int i=0;i<divisionTeamNames.size();i++){
			TableRow row = new TableRow(this.getActivity());
			TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
			row.setLayoutParams(lp);
			TextView numTV = new TextView(getActivity());
			TextView teamTV= new TextView(getActivity());
			TextView wTV = new TextView(getActivity());
			TextView lTV = new TextView(getActivity());
			numTV.setTextSize(getResources().getDimension(R.dimen.rowheight));
			teamTV.setTextSize(getResources().getDimension(R.dimen.rowheight));
			wTV.setTextSize(getResources().getDimension(R.dimen.rowheight));
			lTV.setTextSize(getResources().getDimension(R.dimen.rowheight));
			numTV.setBackground(getResources().getDrawable(R.drawable.border_cell));
			teamTV.setBackground(getResources().getDrawable(R.drawable.border_cell));
			wTV.setBackground(getResources().getDrawable(R.drawable.border_cell));
			lTV.setBackground(getResources().getDrawable(R.drawable.border_cell));
			numTV.setBackgroundColor(Color.WHITE);
			teamTV.setBackgroundColor(Color.WHITE);
			wTV.setBackgroundColor(Color.WHITE);
			lTV.setBackgroundColor(Color.WHITE);
			numTV.setText("" + (i+1));
			teamTV.setText(divisionTeamNames.get(i));
			wTV.setText("" + divisionTeamWins.get(i));
			lTV.setText("" + divisionTeamLosses.get(i));
			row.addView(numTV);
			row.addView(teamTV);
			row.addView(wTV);
			row.addView(lTV);
			row.setBackgroundColor(Color.parseColor("#CCC000"));
			divisionTable.addView(row, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		}
		return rootView;
	}
}
