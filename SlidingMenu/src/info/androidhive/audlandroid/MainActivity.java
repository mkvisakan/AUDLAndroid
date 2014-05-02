package info.androidhive.audlandroid;

import info.androidhive.audlandroid.adapter.NavDrawerListAdapter;
import info.androidhive.audlandroid.interfaces.OnScoreSelectedListener;
import info.androidhive.audlandroid.model.NavDrawerItem;
import info.androidhive.audlandroid.model.ScoreListItem;
import info.androidhive.audlandroid.model.TeamsListItem;
import info.androidhive.audlandroid.utils.ConnectionDetector;
import info.androidhive.audlandroid.R;
import info.androidhive.audlandroid.TeamsListFragment.OnTeamSelectedListener;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
/*import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.*;*/

public class MainActivity extends FragmentActivity implements OnTeamSelectedListener,OnScoreSelectedListener{
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;
	//private GoogleCloudMessaging gcm;
	private String TAG="info.androidhive.audlandroid.MainActivity";
	private String SENDER_ID = "447710219727";
	private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	private final static String REG_ID = "registrationID";
	private String regId;
	private Context context;
	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;
	
	
	public void internetError(){
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Internet Connection Error");
		alertDialog.setMessage("Not Connected to Internet. Please reconnect and try again !!!");
		alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	System.exit(0);
            }
        });
		alertDialog.show();
	}
	
	public void onTeamSelected(TeamsListItem team){
		ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
		boolean isInternetConnected = cd.isConnectingToInternet();
		
		if (!isInternetConnected) {
			internetError();
			return;
		}
		
		TeamsInfoFragment teamFrag = new TeamsInfoFragment();
		Bundle args = new Bundle();
		args.putString("TEAM_ID", team.getTeamId());
		args.putString("TEAM_NAME", team.getTeamName());
		teamFrag.setArguments(args);
        
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.frame_container, teamFrag);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
	}
	public void onScoreSelected(ScoreListItem item){
		ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
		boolean isInternetConnected = cd.isConnectingToInternet();
		
		if (!isInternetConnected) {
			internetError();
			return;
		}
		ScoresInfoFragment scoreFrag = new ScoresInfoFragment();
		Bundle args = new Bundle();
		args.putString("HOMETEAM", item.getHomeTeam());
		args.putString("HOMETEAMID", item.getHomeTeamID());
		args.putString("AWAYTEAM", item.getAwayTeam());
		args.putString("AWAYTEAMID", item.getAwayTeamID());
		args.putString("DATE",item.getDate());
		args.putString("TIME", item.getTime());
		args.putString("HOMETEAMSCORE", item.getHomeTeamScore());
		args.putString("AWAYTEAMSCORE",item.getAwayTeamScore());
		args.putString("STATUS", item.getGameStatus());
		args.putString("ISOTIME", item.getISOTime());
		if(item.getGameStatus().compareTo("0")!=0){
			scoreFrag.setArguments(args);
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			transaction.addToBackStack(null);
			transaction.hide(getSupportFragmentManager().findFragmentById(R.id.frame_container));
			transaction.add(R.id.frame_container, scoreFrag);
			transaction.commit();
		}else{
			Toast.makeText(getApplicationContext(), "Not yet started", Toast.LENGTH_SHORT).show();
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTitle = mDrawerTitle = getTitle();

		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		navDrawerItems = new ArrayList<NavDrawerItem>();
		/*if(checkPlayServices()){
		context = getApplicationContext();
		gcm = GoogleCloudMessaging.getInstance(this);
		regId = getRegistrationId(context);
		if(regId.compareTo("") == 0){
			registerInBackground();
		}
		}
		else{
			finish();
		}*/
		// adding nav drawer items to array
		// Home
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		// News
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
		// Teams
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
		// Now
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
		// Standings
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
		// Scores
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
		// Schedule
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));
		// Videos
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons.getResourceId(7, -1)));
		// Stats
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[8], navMenuIcons.getResourceId(8, -1)));
		// Settings
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[9], navMenuIcons.getResourceId(9, -1)));
		

		// Recycle the typed array
		navMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, //nav menu toggle icon
				R.string.app_name, // nav drawer open - description for accessibility
				R.string.app_name // nav drawer close - description for accessibility
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}
	}
	/*private void registerInBackground() {
		new RegisterTask().execute(null,null,null);
	}

	private String getRegistrationId(Context context){
		final SharedPreferences prefs = getGCMPreferences(context);
		String registrationId = prefs.getString(REG_ID, null);
		if(registrationId == null){
			return "";
		}
		return registrationId;
	}
	private SharedPreferences getGCMPreferences(Context context) {
	    return getSharedPreferences(MainActivity.class.getSimpleName(),
	            Context.MODE_PRIVATE);
	}*/
	/*private boolean checkPlayServices() {
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		if(resultCode != ConnectionResult.SUCCESS){
			if(GooglePlayServicesUtil.isUserRecoverableError(resultCode)){
				GooglePlayServicesUtil.getErrorDialog(resultCode,this,PLAY_SERVICES_RESOLUTION_REQUEST);
			}
			else{
				Log.i(TAG,"This device is not supported");
			}
			return false;
		}
		return true;
	}
	private class RegisterTask extends AsyncTask<Void,Void,Void>{
		protected Void doInBackground(Void... voids){
			String msg="";
			try{
				if(gcm==null){
					gcm = GoogleCloudMessaging.getInstance(context);
				}
				String regid = gcm.register(SENDER_ID);
				msg = "Device registered! Registration ID=" + regid;
				Log.i(TAG,msg);
				sendRegistrationIdToBackend(regid);
				storeRegistrationId(context,regid);
			} catch(IOException e){
				msg = "Error :" + e.getMessage();
			}
			return null;
		}
	}
	
	private void storeRegistrationId(Context context, String regId){
		SharedPreferences pref = getGCMPreferences(context);
		Editor editor = pref.edit();
		editor.putString(REG_ID, regId);
		editor.commit();
	}
	private void sendRegistrationIdToBackend(String regId) {
		HttpClient httpclient = new DefaultHttpClient();
		//To be provided
		String serverURL = getResources().getString(R.string.ServerURL);
		HttpPost httppost = new HttpPost(serverURL);
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		Pattern emailPattern = Patterns.EMAIL_ADDRESS;
		Account[] accounts = AccountManager.get(context).getAccounts();
		for(Account account : accounts){
			if(emailPattern.matcher(account.name).matches()){
				Log.i(TAG,"regID is" + regId);
				nameValuePairs.add(new BasicNameValuePair("regID",regId));
				nameValuePairs.add(new BasicNameValuePair("email",account.name));
				break;
			}
		}
		try{
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			Log.i(TAG,"Response is" + response.getStatusLine().getReasonPhrase());
		}
		catch(UnsupportedEncodingException e){
			Log.i(TAG,"Unsupported encoding Exception");
		}
		catch(ClientProtocolException e){
			Log.i(TAG,"Client Protocol Exception");
		}
		catch(IOException e){
			Log.i(TAG,"IO Exception");
		}
	}**/
	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			getActionBar().removeAllTabs();
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		//case R.id.action_settings:
		//	return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		//menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
		boolean isInternetConnected = cd.isConnectingToInternet();
		
		if (!isInternetConnected) {
			internetError();
			return;
		}
		
		this.invalidateOptionsMenu();
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new HomeFragment();
			break;
		case 1:
			fragment = new NewsListFragment();
			break;
		case 2:
			fragment = new TeamsListFragment();
			break;
		case 3:
			fragment = new NowFragment();
			break;
		case 4:
			fragment = new StandingsListFragment();
			break;
		case 5:
			fragment = new ScoresListFragment();
			break;
		case 6:
			fragment = new ScheduleListFragment();
			break;
		case 7:
			fragment = new VideosListFragment();
			break;
		case 8:
			fragment = new StatsListFragment();
			break;
		case 9:
			fragment = new SettingsFragment();
			break;

		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			
			//clear the backstack
			for (int i=0; i< fragmentManager.getBackStackEntryCount(); i++){
				fragmentManager.popBackStack();
			}
			
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

}
