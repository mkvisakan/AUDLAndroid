package info.androidhive.audlandroid;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LineGraphView;

import info.androidhive.audlandroid.interfaces.FragmentCallback;
import info.androidhive.audlandroid.utils.Utils;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScoreGraphFragment extends Fragment{
	
	private JSONArray jsonResult;
	public ScoreGraphFragment(){
		
	}
	private class Point{
		private double x;
		private int y;
		
		public Point(double x,int y){
			this.x = x;
			this.y = y;
		}
		public double getX(){
			return x;
		}
		public int getY(){
			return y;
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_score_graph, container,false);
		Bundle args = this.getArguments();
		String teamID = args.getString("HOMEID");
		String date = args.getString("DATE");
		String homeTeam = args.getString("HOMETEAM");
		String awayTeam = args.getString("AWAYTEAM");
		startAsyncTask(teamID,date,homeTeam,awayTeam,rootView);
		return rootView;
	}
	
	private ArrayList<ArrayList<Point>> parseJSON(JSONArray responseArray){
		ArrayList<ArrayList<Point>> pointListList = new ArrayList<ArrayList<Point>>();
		try{
			ArrayList<Point> pointList = new ArrayList<Point>(); 
			JSONArray homeArray = responseArray.getJSONArray(0);
			JSONArray awayArray = responseArray.getJSONArray(1);
			JSONArray homePointsArray = homeArray.getJSONArray(1);
			JSONArray awayPointsArray = awayArray.getJSONArray(1);
			for(int i=0;i<homePointsArray.length();i++){
				JSONArray points = homePointsArray.getJSONArray(i);
				Point point = new Point(points.getDouble(0),points.getInt(1));
				pointList.add(point);
			}
			pointListList.add(pointList);
			ArrayList<Point> awayPointList = new ArrayList<Point>();
			for(int i=0;i<awayPointsArray.length();i++){
				JSONArray points = awayPointsArray.getJSONArray(i);
				Point point = new Point(points.getDouble(0),points.getInt(1));
				awayPointList.add(point);
			}
			pointListList.add(awayPointList);
		}catch(JSONException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return pointListList;
	}
	private void startAsyncTask(String teamID,String date,final String homeTeam,
			final String awayTeam,final View rootView){
		final AUDLHttpRequest httpRequester = new AUDLHttpRequest(new FragmentCallback(){

			@Override
			public void onTaskFailure() {
				Utils.ServerError(getActivity());
			}

			@Override
			public void onTaskDone(String response) {
				try{
					jsonResult = new JSONArray(response);
				}catch(JSONException e){
					e.printStackTrace();
				}
				try{
					ArrayList<ArrayList<Point>> list = parseJSON(jsonResult);
					populateGraphView(rootView,list,homeTeam,awayTeam);
				}catch(Exception e){
					TextView status = (TextView) rootView.findViewById(R.id.graphStatus);
					status.setVisibility(View.VISIBLE);
				}
			}
			
		});
		String serverURL = getResources().getString(R.string.ServerURL);
		httpRequester.execute(serverURL + "/Game/" + teamID + "/" + date + "/graph");
	}
	
	private void populateGraphView(View view,ArrayList<ArrayList<Point>> pointsList,
			String homeTeam,String awayTeam){
		int maxValue,intervals,remainder;
		int homePointsSize = pointsList.get(0).size();
		int awayPointsSize = pointsList.get(1).size();
		if(pointsList.get(0).get(homePointsSize-1).getY() >= pointsList.get(1).get(awayPointsSize-1).getY()){
			maxValue = pointsList.get(0).get(homePointsSize-1).getY();
		}
		else{
			maxValue = pointsList.get(1).get(awayPointsSize-1).getY();
		}
		remainder = maxValue % 5;
		intervals = (maxValue + (5 - remainder)) / 5;
		GraphViewData[] pointsArray = new GraphViewData[homePointsSize];
		for(int i=0;i<pointsList.get(0).size();i++){
			pointsArray[i] = new GraphViewData(pointsList.get(0).get(i).getX(),pointsList.get(0).get(i).getY());
		}
		GraphViewSeriesStyle homeSeriesStyle = new GraphViewSeriesStyle();
		GraphViewSeries homeSeries = new GraphViewSeries(homeTeam,homeSeriesStyle,pointsArray);
		GraphViewData[] awayPointsArray = new GraphViewData[awayPointsSize];
		for(int i=0;i<pointsList.get(1).size();i++){
			awayPointsArray[i] = new GraphViewData(pointsList.get(1).get(i).getX(),pointsList.get(1).get(i).getY());
		}
		GraphViewSeriesStyle awaySeriesStyle = new GraphViewSeriesStyle();
		awaySeriesStyle.color=0xffff0000;
		GraphViewSeries awaySeries = new GraphViewSeries(awayTeam,awaySeriesStyle,awayPointsArray);
		LineGraphView graphView = new LineGraphView(getActivity(),homeTeam  + " vs "  + awayTeam);
		graphView.setManualYAxisBounds(maxValue + (5-remainder), 0);
		graphView.getGraphViewStyle().setNumHorizontalLabels(1);
		graphView.getGraphViewStyle().setNumVerticalLabels(intervals + 1);
		graphView.addSeries(homeSeries);
		graphView.addSeries(awaySeries);
		graphView.setLegendAlign(GraphView.LegendAlign.BOTTOM);
		graphView.setShowLegend(true);
		graphView.setLegendWidth(425);
		try{
			LinearLayout layout = (LinearLayout) view.findViewById(R.id.graph1);
			layout.addView(graphView);
		}catch(NullPointerException e){
			
		}
	}
	
}
