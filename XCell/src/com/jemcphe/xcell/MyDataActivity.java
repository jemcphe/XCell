package com.jemcphe.xcell;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import android.app.ActionBar.TabListener;
import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyDataActivity extends FragmentActivity implements TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
	 * three primary sections of the app. We use a {@link android.support.v4.app.FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	AppSectionsPagerAdapter mAppSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will display the three primary sections of the app, one at a
	 * time.
	 */
	ViewPager mViewPager;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frag_mydata);

		// Create the adapter that will return a fragment for each of the three primary sections
		// of the app.
		mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();

		// Up enabled as a workaround for now
		actionBar.setHomeButtonEnabled(true);

		// Specify that we will be displaying tabs in the action bar.
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Set up the ViewPager, attaching the adapter and setting up a listener for when the
		// user swipes between sections.
		mViewPager = (ViewPager) findViewById(R.id.myDataPager);
		mViewPager.setAdapter(mAppSectionsPagerAdapter);
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				// When swiping between different app sections, select the corresponding tab.
				// We can also use ActionBar.Tab#select() to do this if we have a reference to the
				// Tab.
				actionBar.setSelectedNavigationItem(position); 
			}
		});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by the adapter.
			// Also specify this Activity object, which implements the TabListener interface, as the
			// listener for when this tab is selected.
			actionBar.addTab(
					actionBar.newTab()
					.setText(mAppSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
		
		pleaseRefresh();
		
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
	 * sections of the app.
	 */
	public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

		public AppSectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				Fragment sectionOneFrag = new DummySectionFragment();
				Bundle args1 = new Bundle();
				args1.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
				sectionOneFrag.setArguments(args1);
				return sectionOneFrag;
			case 1:
				Fragment sectionTwoFrag = new SectionFragment2();
				Bundle args2 = new Bundle();
				args2.putInt(SectionFragment2.ARG_SECTION_NUMBER, position + 1);
				sectionTwoFrag.setArguments(args2);
				return sectionTwoFrag;
			case 2:
				Fragment sectionThreeFrag = new SectionFragment3();
				Bundle args3 = new Bundle();
				args3.putInt(SectionFragment3.ARG_SECTION_NUMBER, position + 1);
				sectionThreeFrag.setArguments(args3);
				return sectionThreeFrag;
			default:
				// The other sections of the app are dummy placeholders.
				Fragment fragment = new DummySectionFragment();
				Bundle args = new Bundle();
				args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
				fragment.setArguments(args);
				return fragment;
			}
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {

			switch(position){
			case 0:
				return "Adds";
			case 1:
				return "Renewals";
			case 2:
				return "Accessories";
			}

			return null;
		}
	}

	public void pleaseRefresh(){
		int hour = Calendar.HOUR_OF_DAY;
		if (hour == 21){
			Log.i("Refreshing Hour", "It is Time!!");
		} else {
			Log.i("Not Refreshing Hour", "Sorry, it is not time");
		}
		
	}
	
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			View rootView = inflater.inflate(R.layout.data_adds, container, false);
			
			TextView postValue = (TextView) rootView.findViewById(R.id.data_postValue);
			TextView preValue = (TextView) rootView.findViewById(R.id.data_preValue);
			TextView connectedValue = (TextView) rootView.findViewById(R.id.data_connectedValue);
			TextView smbValue = (TextView) rootView.findViewById(R.id.data_smbValue);
			TextView homeValue = (TextView) rootView.findViewById(R.id.data_homeValue);
			
			File postDailyFile = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.jemcphe.xcell/files/post_today");
			File postStartFile = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.jemcphe.xcell/files/post_start");
			
			File preDailyFile = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.jemcphe.xcell/files/pre_today");
			File preStartFile = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.jemcphe.xcell/files/pre_start");
			
			File connectedDailyFile = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.jemcphe.xcell/files/connected_today");
			File connectedStartFile = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.jemcphe.xcell/files/connected_start");
			
			File smbDailyFile = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.jemcphe.xcell/files/smb_today");
			File smbStartFile = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.jemcphe.xcell/files/smb_start");
			
			File homeDailyFile = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.jemcphe.xcell/files/home_today");
			File homeStartFile = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.jemcphe.xcell/files/home_start");
			
			//Post setup
			if (postDailyFile.exists() && postStartFile.exists()){
				String dailyPost = FileInfo.readStringFile(getActivity(), "post_today", true);
				String savedPost = FileInfo.readStringFile(getActivity(), "post_start", true);
				int postTotal = Integer.valueOf(dailyPost) + Integer.valueOf(savedPost);
				postValue.setText(String.valueOf(postTotal));
			} else {
				String postStart = "0";
				FileInfo.storeStringFile(getActivity(), "post_start", postStart, true);
				postValue.setText(postStart);
			}
			
			//Pre setup
			if (preDailyFile.exists() && preStartFile.exists()){
				String dailyPre = FileInfo.readStringFile(getActivity(), "pre_today", true);
				String savedPre = FileInfo.readStringFile(getActivity(), "pre_start", true);
				int preTotal = Integer.valueOf(dailyPre) + Integer.valueOf(savedPre);
				preValue.setText(String.valueOf(preTotal));
			} else {
				String preStart = "0";
				FileInfo.storeStringFile(getActivity(), "pre_start", preStart, true);
				preValue.setText(preStart);
			}
			
			//Connected setup
			if (connectedDailyFile.exists() && connectedStartFile.exists()){
				String dailyConnected = FileInfo.readStringFile(getActivity(), "connected_today", true);
				String savedConnected = FileInfo.readStringFile(getActivity(), "connected_start", true);
				int connectedTotal = Integer.valueOf(dailyConnected) + Integer.valueOf(savedConnected);
				connectedValue.setText(String.valueOf(connectedTotal));
			} else {
				String connectedStart = "0";
				FileInfo.storeStringFile(getActivity(), "connected_start", connectedStart, true);
				postValue.setText(connectedStart);
			}
			
			//SMB setup
			if (smbDailyFile.exists() && smbStartFile.exists()){
				String dailySmb = FileInfo.readStringFile(getActivity(), "smb_today", true);
				String savedSmb = FileInfo.readStringFile(getActivity(), "smb_start", true);
				int smbTotal = Integer.valueOf(dailySmb) + Integer.valueOf(savedSmb);
				smbValue.setText(String.valueOf(smbTotal));
			} else {
				String smbStart = "0";
				FileInfo.storeStringFile(getActivity(), "smb_start", smbStart, true);
				smbValue.setText(smbStart);
			}
			
			//Home setup
			if (homeDailyFile.exists() && homeStartFile.exists()){
				String dailyHome = FileInfo.readStringFile(getActivity(), "home_today", true);
				String savedHome = FileInfo.readStringFile(getActivity(), "home_start", true);
				int homeTotal = Integer.valueOf(dailyHome) + Integer.valueOf(savedHome);
				homeValue.setText(String.valueOf(homeTotal));
			} else {
				String homeStart = "0";
				FileInfo.storeStringFile(getActivity(), "home_start", homeStart, true);
				postValue.setText(homeStart);
			}
			
			return rootView;
		}
	}

	public static class SectionFragment2 extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public SectionFragment2() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			View rootView = inflater.inflate(R.layout.data_renewals, container, false);
			
			TextView renewalValue = (TextView) rootView.findViewById(R.id.data_renewalsValue);
			TextView commissionValue = (TextView) rootView.findViewById(R.id.data_renewalCommissionValue);
			
			File renewDailyFile = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.jemcphe.xcell/files/renewal_today");
			File renewStartFile = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.jemcphe.xcell/files/renewal_start");
			
			//Renewal Setup
			if (renewDailyFile.exists() && renewStartFile.exists()){
				String dailyRenewal = FileInfo.readStringFile(getActivity(), "renewal_today", true);
				String savedRenewal = FileInfo.readStringFile(getActivity(), "renewal_start", true);
				int renewalTotal = Integer.valueOf(dailyRenewal) + Integer.valueOf(savedRenewal);
				
				renewalValue.setText(String.valueOf(renewalTotal));
				
				String renewals = renewalValue.getText().toString();
				int projCommission = 8 * Integer.valueOf(renewals);
				commissionValue.setText("$" + String.valueOf(projCommission) + ".00");
				
			} else {
				String renewalStart = "0";
				FileInfo.storeStringFile(getActivity(), "renewal_start", renewalStart, true);
				renewalValue.setText(renewalStart);
				commissionValue.setText("$" + "0.00");
			}
			
			
			
			return rootView;
		}
	}

	public static class SectionFragment3 extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		
		
		public static final String ARG_SECTION_NUMBER = "section_number";

		public SectionFragment3() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			View rootView = inflater.inflate(R.layout.data_accessory, container, false);

			String JSONString = FileInfo.readStringFile(getActivity(), "accessory_data", true);
			
			try {
				JSONObject jsonObject = new JSONObject(JSONString);
				@SuppressWarnings("rawtypes")
				JSONArray data = new JSONArray((Collection) jsonObject);
				
				Log.i("Accessory Array", data.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//			ArrayList<HashMap<String, String>> accessoryList = new ArrayList<HashMap<String, String>>();
			//
			//			try {
			//				for(int i=0; i<data.length(); i++){
			//					JSONObject teamObject = data.getJSONObject(i);
			//					Log.i("JSONObject", teamObject.toString());
			//					Log.i("JSONObject", teamObject.getString("first_name"));
			//					String teamName = teamObject.getString("first_name") + " " + teamObject.getString("last_name");
			//					String wins = teamObject.getString("won");
			//					String losses = teamObject.getString("lost");
			//
			//					//Create HashMap for data
			//					HashMap<String, String> displayMap = new HashMap<String, String>();
			//					displayMap.put("team", teamName);
			//					displayMap.put("wins", wins);
			//					displayMap.put("losses", losses);
			//
			//					teamList.add(displayMap);
			//				}
			//
			//				//Set up the Adapter
			//				SimpleAdapter adapter = new SimpleAdapter(this, teamList, R.layout.list_row,
			//						new String[] {"team", "wins", "losses"}, 
			//						new int[] {R.id.team, R.id.wins, R.id.losses});
			//
			//				//Instantiate the Adapter
			//				listview.setAdapter(adapter);
			//
			//			} catch (JSONException e) {
			//				// TODO Auto-generated catch block
			//				Log.e("JSON ERROR", e.toString());
			//			}
			
			return rootView;
		}
	}

}
