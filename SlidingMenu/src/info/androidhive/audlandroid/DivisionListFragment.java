package info.androidhive.audlandroid;

import info.androidhive.audlandroid.model.TeamRecordItem;
import info.androidhive.audlandroid.utils.ImageLoader;

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
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class DivisionListFragment extends Fragment{
	private ArrayList<String> divisionTeamNames;
	private ArrayList<String> divisionTeamIDs;
	private ArrayList<String> divisionTeamWins;
	private ArrayList<String> divisionTeamLosses;
	private ArrayList<String> divisionTeamPointsDiff;
	public ImageLoader imageLoader;
	public DivisionListFragment(){}
	
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		Bundle bundle = this.getArguments();
		imageLoader = new ImageLoader(getActivity());
		divisionTeamNames = bundle.getStringArrayList("divisionTeamNames");
		divisionTeamIDs = bundle.getStringArrayList("divisionTeamIDs");
		divisionTeamWins = bundle.getStringArrayList("divisionTeamWins");
		divisionTeamLosses = bundle.getStringArrayList("divisionTeamLosses");
		divisionTeamPointsDiff = bundle.getStringArrayList("divisionTeamPointsDiff");
		View rootView = inflater.inflate(R.layout.fragment_division, container,false);
		TableLayout divisionTable = (TableLayout) rootView.findViewById(R.id.standingsLayout);
		for(int i=0;i<divisionTeamNames.size();i++){
			DisplayMetrics metrics = getActivity().getResources().getDisplayMetrics();
			float dp = 20f;
			float fpixels = metrics.density * dp;
			int pixels = (int) (fpixels + 0.5f);
			TableRow row = new TableRow(this.getActivity());
			TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
			row.setLayoutParams(lp);
			TextView numTV = new TextView(getActivity());
			ImageView teamIV = new ImageView(getActivity());
			TextView teamTV= new TextView(getActivity());
			TextView wTV = new TextView(getActivity());
			TextView lTV = new TextView(getActivity());
			TextView pdTV = new TextView(getActivity());
			numTV.setHeight(pixels);
			teamTV.setHeight(pixels);
			wTV.setHeight(pixels);
			lTV.setHeight(pixels);
			pdTV.setHeight(pixels);
			numTV.setText("" + (i+1));
			imageLoader.DisplayImage("http://ec2-54-186-184-48.us-west-2.compute.amazonaws.com:4000/Icons/" + divisionTeamIDs.get(i), teamIV);
			teamTV.setText(divisionTeamNames.get(i));
			wTV.setText("" + divisionTeamWins.get(i));
			lTV.setText("" + divisionTeamLosses.get(i));
			pdTV.setText("" + divisionTeamPointsDiff.get(i));
			row.addView(numTV);
			row.addView(teamIV);
			row.addView(teamTV);
			row.addView(wTV);
			row.addView(lTV);
			row.addView(pdTV);
			divisionTable.addView(row, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		}
		return rootView;
	}
}
