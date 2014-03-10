package info.androidhive.audlandroid;

import info.androidhive.audlandroid.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultClientConnection;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {
	
	public HomeFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		HttpClient httpClient = new DefaultHttpClient();
		
		HttpGet httpget = new HttpGet("68.190.167.144:4000");
		
		HttpResponse response;
	    try {
	        response = httpClient.execute(httpget);
	        Log.i("HomeFragment", response.toString());
	    } catch (Exception e) {}
 
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
         
        return rootView;
    }
}
