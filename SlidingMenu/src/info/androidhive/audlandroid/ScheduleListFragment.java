package info.androidhive.audlandroid;

import info.androidhive.audlandroid.adapter.ScheduleListTabsPagerAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ScheduleListFragment extends Fragment {
	
	public ScheduleListFragment(){}
	
	private ViewPager viewPager;
    private ScheduleListTabsPagerAdapter mAdapter;
    
    
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		//new HttpRequestTask().execute("http://192.168.72.235:4000/teams");
        
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        
        // Initilization
        viewPager = (ViewPager) rootView.findViewById(R.id.pager);
        mAdapter = new ScheduleListTabsPagerAdapter(this.getActivity().getSupportFragmentManager());
 
        viewPager.setAdapter(mAdapter);
        
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        	 
            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
               // actionBar.setSelectedNavigationItem(position);
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
