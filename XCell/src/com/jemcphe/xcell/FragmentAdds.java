package com.jemcphe.xcell;

//import com.jemcphe.xcell.MainActivity.PhoneSectionFragment;
//import com.jemcphe.xcell.MainActivity.ConnectedSectionFragment;
//import com.jemcphe.xcell.MainActivity.SmbSectionFragment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;

public class FragmentAdds extends FragmentActivity implements ActionBar.TabListener{

	//Create public final today values
	private static String RENEWAL_TODAY = "renewal_today";
	private static String POST_TODAY = "post_today";
	private static String PRE_TODAY = "pre_today";
	private static String CONNECTED_TODAY = "connected_today";
	private static String SMB_TODAY = "smb_today";
	private static String HOME_TODAY = "home_today";

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
		setContentView(R.layout.adds_layout);

		// Create the adapter that will return a fragment for each of the three primary sections
		// of the app.
		mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();

		// Specify that the Home/Up button should not be enabled, since there is no hierarchical
		// parent.
		actionBar.setHomeButtonEnabled(true);

		// Specify that we will be displaying tabs in the action bar.
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Set up the ViewPager, attaching the adapter and setting up a listener for when the
		// user swipes between sections.
		mViewPager = (ViewPager) findViewById(R.id.pager);
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
				Fragment sectionOneFrag = new PhoneSectionFragment();
				Bundle args1 = new Bundle();
				args1.putInt(PhoneSectionFragment.ARG_SECTION_NUMBER, position + 1);
				sectionOneFrag.setArguments(args1);
				return sectionOneFrag;
			case 1:
				Fragment sectionTwoFrag = new ConnectedSectionFragment();
				Bundle args2 = new Bundle();
				args2.putInt(ConnectedSectionFragment.ARG_SECTION_NUMBER, position + 1);
				sectionTwoFrag.setArguments(args2);
				return sectionTwoFrag;
			case 2:
				Fragment sectionThreeFrag = new SmbSectionFragment();
				Bundle args3 = new Bundle();
				args3.putInt(SmbSectionFragment.ARG_SECTION_NUMBER, position + 1);
				sectionThreeFrag.setArguments(args3);
				return sectionThreeFrag;
			default:
				// The other sections of the app are dummy placeholders.
				Fragment fragment = new PhoneSectionFragment();
				Bundle args = new Bundle();
				args.putInt(PhoneSectionFragment.ARG_SECTION_NUMBER, position + 1);
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

				return "Phones";
			case 1:
				return "Connected";
			case 2:
				return "SMB";
			}

			return null;
		}
	}

	@SuppressLint("SimpleDateFormat")
	public static String getLongDate(){
		Date currentDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM d, yyyy");
		String formattedDate = formatter.format(currentDate);
		return formattedDate;
	}
	
	public static class PhoneSectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";
		String startValue = "0";
		public PhoneSectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			File postFile = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.jemcphe.xcell/files/post_today");
			File preFile = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.jemcphe.xcell/files/pre_today");
			File homeFile = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.jemcphe.xcell/files/home_today");

			//Create Strings with values from storage
			String postDateText = getLongDate();
			String todayPostValue = FileInfo.readStringFile(getActivity(), "post_today", true);
			String todayPreValue = FileInfo.readStringFile(getActivity(), "pre_today", true);
			String todayHomeValue = FileInfo.readStringFile(getActivity(), "home_today", true);

			View rootView = inflater.inflate(R.layout.phones, container, false);

			TextView postDate = (TextView) rootView.findViewById(R.id.phonesDate);
			postDate.setText(postDateText);
			final TextView postValue = (TextView) rootView.findViewById(R.id.postValue);
			final TextView preValue = (TextView) rootView.findViewById(R.id.preValue);
			final TextView homeValue = (TextView) rootView.findViewById(R.id.homeValue);

			//SeekBars
			SeekBar postSeek = (SeekBar) rootView.findViewById(R.id.postSeekBar);
			SeekBar preSeek = (SeekBar) rootView.findViewById(R.id.preSeekBar);
			SeekBar homeSeek = (SeekBar) rootView.findViewById(R.id.homeSeekBar);

			if (postFile.exists()){
				int postSeekBarValue = Integer.valueOf(todayPostValue);
				postValue.setText(todayPostValue);
				postSeek.setProgress(postSeekBarValue);
			} else {
				postValue.setText(startValue);
				FileInfo.storeStringFile(getActivity(), POST_TODAY, startValue, true);
			}

			if (preFile.exists()){
				int preSeekBarValue = Integer.valueOf(todayPreValue);
				preValue.setText(todayPreValue);
				preSeek.setProgress(preSeekBarValue);
			} else {
				preValue.setText(startValue);
				FileInfo.storeStringFile(getActivity(), PRE_TODAY, startValue, true);
			}

			if (homeFile.exists()){
				int homeSeekBarValue = Integer.valueOf(todayHomeValue);
				homeValue.setText(todayHomeValue);
				homeSeek.setProgress(homeSeekBarValue);
			} else {
				homeValue.setText(startValue);
				FileInfo.storeStringFile(getActivity(), HOME_TODAY, startValue, true);
			}

			//setup postSeek
			postSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					String todayPost = (String) postValue.getText();
					FileInfo.storeStringFile(getActivity(), "post_today", todayPost, true);
				}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					// TODO Auto-generated method stub
					if (fromUser) {
						seekBar.setProgress(progress);
						Log.i("SeekBar Progress", String.valueOf(progress));
						postValue.setText(String.valueOf(progress));
					}
				}
			});

			//setup preSeek
			preSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					String todayPre = (String) preValue.getText();
					FileInfo.storeStringFile(getActivity(), "pre_today", todayPre, true);
				}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					// TODO Auto-generated method stub
					if (fromUser) {
						seekBar.setProgress(progress);
						Log.i("SeekBar Progress", String.valueOf(progress));
						preValue.setText(String.valueOf(progress));
					}
				}
			});

			//Setup homeSeek
			homeSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					String todayHome = (String) homeValue.getText();
					FileInfo.storeStringFile(getActivity(), "home_today", todayHome, true);
				}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					// TODO Auto-generated method stub
					if (fromUser) {
						seekBar.setProgress(progress);
						Log.i("SeekBar Progress", String.valueOf(progress));
						homeValue.setText(String.valueOf(progress));
					}
				}
			});
			return rootView;
		}
	}

	public static class ConnectedSectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";
		String startValue = "0";
		public ConnectedSectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			File connectedFile = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.jemcphe.xcell/files/connected_today");

			String connectedTodayValue = FileInfo.readStringFile(getActivity(), "connected_today", true);
			String connectedDateText = getLongDate();

			View rootView = inflater.inflate(R.layout.connected, container, false);

			TextView connectedDate = (TextView) rootView.findViewById(R.id.connectedDate);
			connectedDate.setText(connectedDateText);
			
			final TextView connectedValue = (TextView) rootView.findViewById(R.id.connectedValue);
			SeekBar connectedSeek = (SeekBar) rootView.findViewById(R.id.connectedSeekBar);

			if (connectedFile.exists()) {
				int connectedSeekBarValue = Integer.valueOf(connectedTodayValue);
				connectedValue.setText(connectedTodayValue);
				connectedSeek.setProgress(connectedSeekBarValue);
			} else {
				connectedValue.setText(startValue);
				FileInfo.storeStringFile(getActivity(), CONNECTED_TODAY, startValue, true);
			}

			connectedSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					String todayConnected = (String) connectedValue.getText();
					FileInfo.storeStringFile(getActivity(), "connected_today", todayConnected, true);
				}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					// TODO Auto-generated method stub
					if (fromUser) {
						seekBar.setProgress(progress);
						Log.i("SeekBar Progress", String.valueOf(progress));
						connectedValue.setText(String.valueOf(progress));
					}
				}
			});

			return rootView;
		}
	}

	public static class SmbSectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";
		String startValue = "0";
		public SmbSectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			File smbFile = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.jemcphe.xcell/files/smb_today");

			String smbTodayValue = FileInfo.readStringFile(getActivity(), "smb_today", true);
			String smbDateText = getLongDate();

			View rootView = inflater.inflate(R.layout.smb, container, false);

			TextView smbDate = (TextView) rootView.findViewById(R.id.smbDate);
			smbDate.setText(smbDateText);
			
			final TextView smbValue = (TextView) rootView.findViewById(R.id.smbValue);
			SeekBar smbSeek = (SeekBar) rootView.findViewById(R.id.smbSeekBar);

			if (smbFile.exists()) {
				int smbSeekBarValue = Integer.valueOf(smbTodayValue);
				smbValue.setText(smbTodayValue);
				smbSeek.setProgress(smbSeekBarValue);
			} else {
				smbValue.setText(startValue);
				FileInfo.storeStringFile(getActivity(), SMB_TODAY, startValue, true);
			}

			smbSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					String todaySmb = (String) smbValue.getText();
					FileInfo.storeStringFile(getActivity(), "smb_today", todaySmb, true);
				}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					if (fromUser) {
						seekBar.setProgress(progress);
						Log.i("SeekBar Progress", String.valueOf(progress));
						smbValue.setText(String.valueOf(progress));
					}

				}
			});
			return rootView;
		}
	}
}
