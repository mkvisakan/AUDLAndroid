package info.androidhive.audlandroid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import info.androidhive.audlandroid.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultClientConnection;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TeamsListFragment extends Fragment {
	
	public TeamsListFragment(){}
	
	TextView textMsg, textPrompt;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_teams, container, false);
		
		//textMsg = (TextView)getView().findViewById(R.id.textView1);
		textMsg = (TextView)rootView.findViewById(R.id.textView1);
		textMsg.setText("Loading Teams...");

		new HttpRequestTask().execute("http://192.168.72.235:4000/teams");
        
        
         
        return rootView;
    }
	
	private class HttpRequestTask extends AsyncTask<String, Void, String>{
		
		private String convertStreamToString(InputStream is) {
		    /*
		     * To convert the InputStream to String we use the BufferedReader.readLine()
		     * method. We iterate until the BufferedReader return null which means
		     * there's no more data to read. Each line will appended to a StringBuilder
		     * and returned as String.
		     */
		    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		    StringBuilder sb = new StringBuilder();

		    String line = null;
		    try {
		        while ((line = reader.readLine()) != null) {
		            sb.append(line + "\n");
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            is.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		    return sb.toString();
		}
		
		@Override
		protected String doInBackground(String... url){
			String result =  null;
	        HttpClient httpClient = new DefaultHttpClient();
			
			//HttpGet httpget = new HttpGet("http://192.168.72.232:4000/teams");
	        HttpGet httpget = new HttpGet(url[0]);
			
			HttpResponse response;
		    try {
		    	response = httpClient.execute(httpget);
		        HttpEntity entity = response.getEntity();
		        InputStream instream = entity.getContent();
	            result= convertStreamToString(instream);
	            // now you have the string representation of the HTML request
	            instream.close();
		        Log.i("HomeFragment", result);
		    } catch (Exception e) {
		    	Log.i("HomeFragment", "Error fetching data " + e.toString());
		    }
		    return result;
		}
		
		
		protected void onPostExecute(String result) {
			textMsg.setText(result);
			Log.i("HomeFragment", result);
	    }
		
	}

}
