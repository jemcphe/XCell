package com.jemcphe.xcell;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView; 
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends FragmentActivity{

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mPlanetTitles;

	public static FragmentManager myDataManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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

		//AddsActivity addsFragment = (AddsActivity) getSupportFragmentManager().findFragmentById(R.id.frag_adds);

		switch (position){

		case 0:
			Intent intent = new Intent(this, FragmentAdds.class);
			startActivity(intent);
			
//			FragmentAdds addsActivity = new FragmentAdds();
//			Bundle adds = new Bundle();
//			adds.putInt("Adds", 1);
//			addsActivity.setArguments(adds);
//			FragmentTransaction addsTransaction = getSupportFragmentManager().beginTransaction();
//
//			android.app.FragmentManager fragManager = getFragmentManager();
//			fragManager.beginTransaction().replace(R.id.content_frame, addsActivity).commit();
//
//			mDrawerList.setItemChecked(position, true);
//			setTitle("Adds");
//			mDrawerLayout.closeDrawer(mDrawerList);
			
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

		//        Fragment fragment = new PlanetFragment();
		//        Bundle args = new Bundle();
		//        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
		//        fragment.setArguments(args);
		//
		//        FragmentManager fragmentManager = getFragmentManager();
		//        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
		//
		//        // update selected item and title, then close the drawer
		//        mDrawerList.setItemChecked(position, true);
		//        setTitle(mPlanetTitles[position]);
		//        mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
