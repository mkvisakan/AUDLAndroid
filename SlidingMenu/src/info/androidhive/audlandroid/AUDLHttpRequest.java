package info.androidhive.audlandroid;


import info.androidhive.audlandroid.interfaces.FragmentCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import android.os.AsyncTask;
import android.util.Log;

public class AUDLHttpRequest extends AsyncTask<String, Void, String>{
	private FragmentCallback mCallback;
	private String stringResult = null;
	
	public AUDLHttpRequest(FragmentCallback fragCallback){
		this.mCallback = fragCallback;
	}
	
	
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
        HttpClient httpClient = new DefaultHttpClient();
		
        HttpGet httpget = new HttpGet(url[0]);
		
		HttpResponse response;
	    try {
	    	response = httpClient.execute(httpget);
	        HttpEntity entity = response.getEntity();
	        InputStream instream = entity.getContent();
            stringResult= convertStreamToString(instream);
            // now you have the string representation of the HTML request
            instream.close();
	    } catch (Exception e) {
	    	Log.e("AUDLHttpRequest", "Error fetching data " + e.toString());
	    }
	    return stringResult;
	}
	
	protected void onPostExecute(String result) {
		mCallback.onTaskDone(result);
		Log.i("AUDLHttpRequest", result);
    }
	
}