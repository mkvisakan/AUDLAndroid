package info.androidhive.audlandroid;


import info.androidhive.audlandroid.interfaces.FragmentCallback;
import info.androidhive.audlandroid.model.Authenticated;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;

//Uses an AsyncTask to download a Twitter user's timeline
	public class TwitterRequest extends AsyncTask<String, Void, String> {
		private FragmentCallback mCallback;
		
		final static String CONSUMER_KEY = "3egMgWgj6UXp7N3eh9VP5Q";
		final static String CONSUMER_SECRET = "BoWTmMYa8CL2mDxNS7mAYcDHWJ5o5ah7ndqvpsaFA";
		final static String TwitterTokenURL = "https://api.twitter.com/oauth2/token";
		final static String TwitterStreamURL = "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=";
		
		public TwitterRequest(FragmentCallback fragCallback){
			this.mCallback = fragCallback;
		}
		
		@Override
		protected String doInBackground(String... screenNames) {
			String result = null;

			if (screenNames.length > 0) {
				result = getTwitterStream(screenNames[0]);
			}
			return result;
		}

		protected void onPostExecute(String result) {
			mCallback.onTaskDone(result);
			Log.i("AUDLTwitterRequest", result);
	    }

		// convert a JSON authentication object into an Authenticated object
		private Authenticated jsonToAuthenticated(String rawAuthorization) {
			Authenticated auth = null;
			if (rawAuthorization != null && rawAuthorization.length() > 0) {
				try {
					Gson gson = new Gson();
					auth = gson.fromJson(rawAuthorization, Authenticated.class);
				} catch (IllegalStateException ex) {
					// just eat the exception
				}
			}
			return auth;
		}

		private String getResponseBody(HttpRequestBase request) {
			StringBuilder sb = new StringBuilder();
			try {

				DefaultHttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());
				HttpResponse response = httpClient.execute(request);
				int statusCode = response.getStatusLine().getStatusCode();
				String reason = response.getStatusLine().getReasonPhrase();

				if (statusCode == 200) {

					HttpEntity entity = response.getEntity();
					InputStream inputStream = entity.getContent();

					BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
					String line = null;
					while ((line = bReader.readLine()) != null) {
						sb.append(line);
					}
				} else {
					sb.append(reason);
				}
			} catch (UnsupportedEncodingException ex) {
			} catch (ClientProtocolException ex1) {
			} catch (IOException ex2) {
			}
			return sb.toString();
		}

		private String getTwitterStream(String screenName) {
			String results = null;

			// Step 1: Encode consumer key and secret
			try {
				// URL encode the consumer key and secret
				String urlApiKey = URLEncoder.encode(CONSUMER_KEY, "UTF-8");
				String urlApiSecret = URLEncoder.encode(CONSUMER_SECRET, "UTF-8");
				

				// Concatenate the encoded consumer key, a colon character, and the
				// encoded consumer secret
				String combined = urlApiKey + ":" + urlApiSecret;
				// Base64 encode the string
				String base64Encoded = Base64.encodeToString(combined.getBytes(), Base64.NO_WRAP);
				// Step 2: Obtain a bearer token
				HttpPost httpPost = new HttpPost(TwitterTokenURL);
				httpPost.setHeader("Authorization", "Basic " + base64Encoded);
				httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
				httpPost.setEntity(new StringEntity("grant_type=client_credentials"));
				String rawAuthorization = getResponseBody(httpPost);
				Authenticated auth = jsonToAuthenticated(rawAuthorization);

				// Applications should verify that the value associated with the
				// token_type key of the returned object is bearer
				if (auth != null && auth.token_type.equals("bearer")) {

					// Step 3: Authenticate API requests with bearer token
					HttpGet httpGet = new HttpGet(TwitterStreamURL + screenName + "&count=20");

					// construct a normal HTTPS request and include an Authorization
					// header with the value of Bearer <>
					httpGet.setHeader("Authorization", "Bearer " + auth.access_token);
					httpGet.setHeader("Content-Type", "application/json");
					// update the results with the body of the response
					results = getResponseBody(httpGet);
				}
			} catch (UnsupportedEncodingException ex) {
			} catch (IllegalStateException ex1) {
			}
			return results;
		}
	}