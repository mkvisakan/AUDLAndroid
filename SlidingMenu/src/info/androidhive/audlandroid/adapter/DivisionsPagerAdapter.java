package info.androidhive.audlandroid.adapter;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import info.androidhive.audlandroid.DivisionListFragment;
import info.androidhive.audlandroid.model.TeamRecordItem;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class DivisionsPagerAdapter extends FragmentStatePagerAdapter{
	private ArrayList<String> tabNames;
	private JSONArray standingsArray;
	public DivisionsPagerAdapter(FragmentManager fm,ArrayList<String> tabValues,JSONArray array) {
		super(fm);
		tabNames = tabValues;	
		this.standingsArray = array;
	}
	
	    @Override
	    public CharSequence getPageTitle(int position) {
	    	return tabNames.get(position);
	    }
	    @Override
	    public Fragment getItem(int index) {
	        DivisionListFragment fragment =  new DivisionListFragment(); 
	        JSONArray divisionArray = new JSONArray();
	        try {
	        	Bundle bundle = new Bundle();
				divisionArray = standingsArray.getJSONArray(index);
				bundle.putString("DivisionArray", divisionArray.toString());
		        fragment.setArguments(bundle);
	        } catch (JSONException e) {
				e.printStackTrace();
			}
	        return fragment;
	    }
	 
	    @Override
	    public int getCount() {
	        // get item count - equal to number of tabs
	        return tabNames.size();
	    }
	 
}
