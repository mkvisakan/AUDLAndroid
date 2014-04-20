package info.androidhive.audlandroid;


import info.androidhive.audlandroid.interfaces.FragmentCallback;
import android.os.AsyncTask;

public class EmptyRequest extends AsyncTask<String, Void, String>{
	private FragmentCallback mCallback;
	private String stringResult = null;
	
	public EmptyRequest(FragmentCallback fragCallback){
		this.mCallback = fragCallback;
	}
	
	@Override
	protected String doInBackground(String... url){
		try{
		Thread.sleep(250); // this is the whole point of this class, to create an async delay. Lol.
		} catch(InterruptedException e){
		}
	    return stringResult;
	}
	
	protected void onPostExecute(String result) {
		mCallback.onTaskDone(result);
    }
	
}