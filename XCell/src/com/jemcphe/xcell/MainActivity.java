package com.jemcphe.xcell;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jemcphe.xcell.db.AccessoriesDataSource;
import com.jemcphe.xcell.db.AccessoryDBOpenHelper;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView; 
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends FragmentActivity{

	//Create public today values
	private static String RENEWAL_TODAY = "renewal_today";
	private static String POST_TODAY = "post_today";
	private static String PRE_TODAY = "pre_today";
	private static String CONNECTED_TODAY = "connected_today";
	private static String SMB_TODAY = "smb_today";
	private static String HOME_TODAY = "home_today";
	//Create public start values
	private static String RENEWAL_START = "renewal_start";
	private static String POST_START = "post_start";
	private static String PRE_START = "pre_start";
	private static String CONNECTED_START = "connected_start";
	private static String SMB_START = "smb_start";
	private static String HOME_START = "home_start";
	
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mPlanetTitles;
	
	public static AccessoriesDataSource dataSource;
	public static AccessoryDBOpenHelper dbHelper;
	public static FragmentManager myDataManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		dataSource = new AccessoriesDataSource(this);

		mTitle = mDrawerTitle = getTitle();
		mPlanetTitles = getResources().getStringArray(R.array.drawer_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// set a custom shadow that overlays the main content when the drawer opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		// set up the drawer's list view with items and click listener
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, mPlanetTitles));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(
				this,                  /* host Activity */
				mDrawerLayout,         /* DrawerLayout object */
				R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
				R.string.drawer_open,  /* "open drawer" description for accessibility */
				R.string.drawer_close  /* "close drawer" description for accessibility */
				) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(1);
		}

		/**************************** SWIPE TABS ***************************/

		//        // Set up the action bar.
		//        final ActionBar actionBar = getActionBar();
		//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		//
		//        // Create the adapter that will return a fragment for each of the three
		//        // primary sections of the app.
		//        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		//
		//        // Set up the ViewPager with the sections adapter.
		//        mViewPager = (ViewPager) findViewById(R.id.pager);
		//        mViewPager.setAdapter(mSectionsPagerAdapter);
		//
		//        // When swiping between different sections, select the corresponding
		//        // tab. We can also use ActionBar.Tab#select() to do this if we have
		//        // a reference to the Tab.
		//        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
		//            @Override
		//            public void onPageSelected(int position) {
		//                actionBar.setSelectedNavigationItem(position);
		//            }
		//        });
		//
		//        // For each of the sections in the app, add a tab to the action bar.
		//        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
		//            // Create a tab with text corresponding to the page title defined by
		//            // the adapter. Also specify this Activity object, which implements
		//            // the TabListener interface, as the callback (listener) for when
		//            // this tab is selected.
		//            actionBar.addTab(
		//                    actionBar.newTab()
		//                            .setText(mSectionsPagerAdapter.getPageTitle(i))
		//                            .setTabListener(this));
		//        }
		//    }


	}

	/* The click listner for ListView in the navigation drawer */
	public class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			selectItem(position);
		}
	}
	
	private void selectItem(int position) {
		// update the main content by replacing fragments
		switch (position){

		case 0:
			Intent intent = new Intent(this, FragmentAdds.class);
			startActivity(intent);
			
			break;
		case 1:
			FragmentRenewals renewalFrag = new FragmentRenewals();
			Bundle args = new Bundle();
			args.putInt("Renewals", 1);
			renewalFrag.setArguments(args);

			android.app.FragmentManager renewManager = getFragmentManager();
			renewManager.beginTransaction().replace(R.id.content_frame, renewalFrag).commit();

			mDrawerList.setItemChecked(position, true);
			setTitle("Renewals");
			mDrawerLayout.closeDrawer(mDrawerList);

			break;
		case 2:
			FragmentAccessories accessoriesFrag = new FragmentAccessories();
			Bundle accessories = new Bundle();
			accessories.putInt("Renewals", 1);
			accessoriesFrag.setArguments(accessories);

			android.app.FragmentManager accessoriesManager = getFragmentManager();
			accessoriesManager.beginTransaction().replace(R.id.content_frame, accessoriesFrag).commit();

			mDrawerList.setItemChecked(position, true);
			setTitle("Accessories");
			mDrawerLayout.closeDrawer(mDrawerList);
			break;
		case 3:
			Intent myDataIntent = new Intent(this, MyDataActivity.class);
			startActivity(myDataIntent);
			break;
		case 4:
			FragmentSettings settingsFrag = new FragmentSettings();
			Bundle settings = new Bundle();
			settings.putInt("Renewals", 1);
			settingsFrag.setArguments(settings);

			android.app.FragmentManager settingsManager = getFragmentManager();
			settingsManager.beginTransaction().replace(R.id.content_frame, settingsFrag).commit();

			mDrawerList.setItemChecked(position, true);
			setTitle("Accessories");
			mDrawerLayout.closeDrawer(mDrawerList);
			break;
		default:
			break;

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()){
		case R.id.send_data:
			onDataSend();
			return true;
		case R.id.newMonth:
			Log.i("XCELL MENU ITEM", "New Month Started");
			DialogFragment dialog = new NewMonthConfirmation();
			dialog.show(getFragmentManager(), "sent");
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		if (requestCode == 100){
				Log.i("XCELL", "Reset All Data Files To 0");
				String resetValue = "0";
				
				//Reset Daily Values for fresh sales tracking
				FileInfo.storeStringFile(this, RENEWAL_TODAY, resetValue, true);
				FileInfo.storeStringFile(this, POST_TODAY, resetValue, true);
				FileInfo.storeStringFile(this, PRE_TODAY, resetValue, true);
				FileInfo.storeStringFile(this, CONNECTED_TODAY, resetValue, true);
				FileInfo.storeStringFile(this, SMB_TODAY, resetValue, true);
				FileInfo.storeStringFile(this, HOME_TODAY, resetValue, true);
		}
		
	}
	
	@SuppressLint("ValidFragment")
	public class SendAndSaveDialog extends DialogFragment {
	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        // Use the Builder class for convenient dialog construction
	        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	        builder.setMessage(R.string.savesend_dialog)
	               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                       dialog.dismiss();
	                   }
	               });
	        // Create the AlertDialog object and return it
	        return builder.create();
	    }
	}
	
	@SuppressLint("ValidFragment")
	public class GoToSettingsDialog extends DialogFragment {
	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        // Use the Builder class for convenient dialog construction
	        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	        builder.setMessage(R.string.goToSettings_dialog)
	               .setPositiveButton("Go To Settings", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                	FragmentSettings settingsFrag = new FragmentSettings();
	           			Bundle settings = new Bundle();
	           			settings.putInt("Renewals", 1);
	           			settingsFrag.setArguments(settings);

	           			android.app.FragmentManager settingsManager = getFragmentManager();
	           			settingsManager.beginTransaction().replace(R.id.content_frame, settingsFrag).commit();
	                   }
	               });
	        // Create the AlertDialog object and return it
	        return builder.create();
	    }
	}
	
	@SuppressLint("ValidFragment")
	public class NewMonthConfirmation extends DialogFragment {
	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        // Use the Builder class for convenient dialog construction
	        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	        builder.setMessage(R.string.newMonthCheck_dialog)
	               .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                	   onNewMonth();
	                	   DialogFragment dialogFrag = new NewMonthStartedDialog();
	                	   dialogFrag.show(getFragmentManager(), "sent");
	                   }
	               })
	               .setNegativeButton("No", new DialogInterface.OnClickListener() {
	            	   public void onClick(DialogInterface dialog, int which) {
	            		   dialog.dismiss();
					}
				});
	        // Create the AlertDialog object and return it
	        return builder.create();
	    }
	}
	
	@SuppressLint("ValidFragment")
	public class NewMonthStartedDialog extends DialogFragment {
	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        // Use the Builder class for convenient dialog construction
	        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	        builder.setMessage(R.string.newMonth_dialog)
	               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                	   dialog.dismiss();
	                   }
	               });
	        // Create the AlertDialog object and return it
	        return builder.create();
	    }
	}
	
	@SuppressLint("SimpleDateFormat")
	public String getShortDate(){
		Date currentDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM'/'dd");
		String formattedDate = formatter.format(currentDate);
		return formattedDate;
	}
	
	@SuppressLint("SimpleDateFormat")
	public String getLongDate(){
		Date currentDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM'/'dd'/'yyyy");
		String formattedDate = formatter.format(currentDate);
		return formattedDate;
	}
	
    @Override
    public void onResume() {
    	super.onResume();
    	dataSource.open();
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    	dataSource.close();
    }

    public void onDataSend(){
    	Log.i("XCELL", "Send Button Selected");
		//Check if file exists
		File postFile = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.jemcphe.xcell/files/settings_name");
		
		//Create String Values via Current Monthly Totals
		String postTotal = String.valueOf(MyDataActivity.AddsSectionFragment.postTotal);
		String preTotal = String.valueOf(MyDataActivity.AddsSectionFragment.preTotal);
		String connectedTotal = String.valueOf(MyDataActivity.AddsSectionFragment.connectedTotal);
		String smbTotal = String.valueOf(MyDataActivity.AddsSectionFragment.smbTotal);
		String homeTotal = String.valueOf(MyDataActivity.AddsSectionFragment.homeTotal);
		String renewalTotal = String.valueOf(MyDataActivity.RenewalSectionFragment.renewalTotal);
		
		if (postFile.exists()){
			//Create Strings to hold add & renewal values
			String name = FileInfo.readStringFile(this, "settings_name", true);
			String post = FileInfo.readStringFile(this, "post_today", true);
			String pre = FileInfo.readStringFile(this, "pre_today", true);
			String connected = FileInfo.readStringFile(this, "connected_today", true);
			String smb = FileInfo.readStringFile(this, "smb_today", true);
			String home = FileInfo.readStringFile(this, "home_today", true);
			String renewal = FileInfo.readStringFile(this, "renewal_today", true);
			String date = getLongDate();
			String leaderEmail = FileInfo.readStringFile(this, "settings_email", true);
			String [] emailList = {leaderEmail};
			
			Intent intent = new Intent(android.content.Intent.ACTION_SEND);
			intent.setType("plain/text");
			intent.putExtra(android.content.Intent.EXTRA_EMAIL, emailList);
			intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Nightly Numbers - " + date);
			intent.putExtra(android.content.Intent.EXTRA_TEXT, 
					"Sales Activity For " + name + " for " + date + "\n\n" +
					"Post:              " + post + "\n" +
					"Pre:                " + pre + "\n" +
					"Connected:   " + connected + "\n" +
					"SMB:              " + smb + "\n" +
					"Home:            " + home + "\n" +
					"Renewals:     " + renewal + "\n");
			startActivityForResult(intent, 100);
			
			//Save TODAY values as START values
			FileInfo.storeStringFile(this, RENEWAL_START, renewalTotal, true);
			FileInfo.storeStringFile(this, POST_START, postTotal, true);
			FileInfo.storeStringFile(this, PRE_START, preTotal, true);
			FileInfo.storeStringFile(this, CONNECTED_START, connectedTotal, true);
			FileInfo.storeStringFile(this, SMB_START, smbTotal, true);
			FileInfo.storeStringFile(this, HOME_START, homeTotal, true);
			
			DialogFragment dialog = new SendAndSaveDialog();
			dialog.show(getFragmentManager(), "sent");
		} else {
			DialogFragment dialog = new GoToSettingsDialog();
			dialog.show(getFragmentManager(), "goSettings");
		}
    }
    
    public void onNewMonth(){
    	String resetValueString = "0";
    	//Reset all Stored Values to 0, except for User Settings
    	FileInfo.storeStringFile(this, POST_START, resetValueString, true);
    	FileInfo.storeStringFile(this, POST_TODAY, resetValueString, true);
    	FileInfo.storeStringFile(this, PRE_START, resetValueString, true);
    	FileInfo.storeStringFile(this, PRE_TODAY, resetValueString, true);
    	FileInfo.storeStringFile(this, CONNECTED_START, resetValueString, true);
    	FileInfo.storeStringFile(this, CONNECTED_TODAY, resetValueString, true);
    	FileInfo.storeStringFile(this, SMB_START, resetValueString, true);
    	FileInfo.storeStringFile(this, SMB_TODAY, resetValueString, true);
    	FileInfo.storeStringFile(this, HOME_START, resetValueString, true);
    	FileInfo.storeStringFile(this, HOME_TODAY, resetValueString, true);
    	FileInfo.storeStringFile(this, RENEWAL_START, resetValueString, true);
    	FileInfo.storeStringFile(this, RENEWAL_TODAY, resetValueString, true);
    	
    	//Empty out the database
    	dataSource.delete();

    }
    
}
