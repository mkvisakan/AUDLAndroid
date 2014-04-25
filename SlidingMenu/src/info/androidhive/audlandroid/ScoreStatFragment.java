package info.androidhive.audlandroid;

import info.androidhive.audlandroid.utils.ImageLoader;

import java.util.ArrayList;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ScoreStatFragment extends Fragment{
	
	private ImageLoader imageLoader;
	public ScoreStatFragment(Activity a){
		imageLoader=new ImageLoader(a.getApplicationContext());
	}
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
		 
		 View rootView = inflater.inflate(R.layout.fragment_score_stat,container,false);
		 
		 Bundle args = this.getArguments();
		 TextView homeGoal = (TextView) rootView.findViewById(R.id.homeGoal);
		 TextView awayGoal = (TextView) rootView.findViewById(R.id.awayGoal);
		 TextView homeAssist = (TextView) rootView.findViewById(R.id.homeAssist);
		 TextView awayAssist = (TextView) rootView.findViewById(R.id.awayAssist);
		 TextView homeDrop = (TextView) rootView.findViewById(R.id.homeDrop);
		 TextView awayDrop = (TextView) rootView.findViewById(R.id.awayDrop);
		 TextView homeTA = (TextView) rootView.findViewById(R.id.homeTA);
		 TextView awayTA = (TextView) rootView.findViewById(R.id.awayTA);
		 TextView homeD = (TextView) rootView.findViewById(R.id.homeD);
		 TextView awayD = (TextView) rootView.findViewById(R.id.awayD);
		 ImageView homeIcon = (ImageView) rootView.findViewById(R.id.homeIcon);
		 ImageView awayIcon = (ImageView) rootView.findViewById(R.id.awayIcon);
		 TextView homeScore = (TextView) rootView.findViewById(R.id.homeScore);
		 TextView awayScore = (TextView) rootView.findViewById(R.id.awayScore);
		 ArrayList<String> homePlayers = args.getStringArrayList("HOMEPLAYERS");
		 ArrayList<String> awayPlayers = args.getStringArrayList("AWAYPLAYERS");
		 ArrayList<String> homeNumbers = args.getStringArrayList("HOMENUMBERS");
		 ArrayList<String> awayNumbers = args.getStringArrayList("AWAYNUMBERS");
		 String homeID = args.getString("HOMEID");
		 String awayID = args.getString("AWAYID");
		 String homeScoreString = args.getString("HOMESCORE");
		 String awayScoreString = args.getString("AWAYSCORE");
		 homeGoal.setText(homePlayers.get(0) + "(" + (homeNumbers.get(0)) + ")");
		 awayGoal.setText(awayPlayers.get(0) + "(" + (awayNumbers.get(0)) + ")");
		 homeAssist.setText(homePlayers.get(1) + "(" + (homeNumbers.get(1)) + ")");
		 awayAssist.setText(awayPlayers.get(1) + "(" + (awayNumbers.get(1)) + ")");
		 homeDrop.setText(homePlayers.get(2) + "(" + (homeNumbers.get(2)) + ")");
		 awayDrop.setText(awayPlayers.get(2) + "(" + (awayNumbers.get(2)) + ")");
		 homeTA.setText(homePlayers.get(3) + "(" + (homeNumbers.get(3)) + ")");
		 awayTA.setText(awayPlayers.get(3) + "(" + (awayNumbers.get(3)) + ")");
		 homeD.setText(homePlayers.get(4) + "(" + (homeNumbers.get(4)) + ")");
		 awayD.setText(awayPlayers.get(4) + "(" + (awayNumbers.get(4)) + ")");
		 homeScore.setText(homeScoreString);
		 awayScore.setText(awayScoreString);
		 String serverURL = getResources().getString(R.string.ServerURL);
		 imageLoader.DisplayImage(serverURL + "/Icons/" + homeID, homeIcon);
		 imageLoader.DisplayImage(serverURL + "/Icons/" + awayID, awayIcon);
		 return rootView;
	 }
}
