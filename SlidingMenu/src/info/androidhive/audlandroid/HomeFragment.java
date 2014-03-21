package info.androidhive.audlandroid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import info.androidhive.audlandroid.R;
import info.androidhive.audlandroid.adapter.TabsPagerAdapter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultClientConnection;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {
	
	public HomeFragment(){}
	
	private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    
    private String[] tabs = { "News", "Teams"};
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		//new HttpRequestTask().execute("http://192.168.72.235:4000/teams");
        
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        
        // Initilization
        viewPager = (ViewPager) rootView.findViewById(R.id.pager);
        actionBar = this.getActivity().getActionBar();
        mAdapter = new TabsPagerAdapter(this.getActivity().getSupportFragmentManager());
 
        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);        
 
        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(new TabListener() {
						
						@Override
						public void onTabUnselected(Tab tab, FragmentTransaction ft) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onTabSelected(Tab tab, FragmentTransaction ft) {
							// TODO Auto-generated method stub
							viewPager.setCurrentItem(tab.getPosition());
						}
						
						@Override
						public void onTabReselected(Tab tab, FragmentTransaction ft) {
							// TODO Auto-generated method stub
							
						}
					}));
        }
        
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        	 
            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }
         
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
         
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
         
        return rootView;
    }
}
