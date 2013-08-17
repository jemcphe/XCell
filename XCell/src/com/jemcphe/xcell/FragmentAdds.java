package com.jemcphe.xcell;

//import com.jemcphe.xcell.MainActivity.DummySectionFragment;
//import com.jemcphe.xcell.MainActivity.SectionFragment2;
//import com.jemcphe.xcell.MainActivity.SectionFragment3;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;

public class FragmentAdds extends FragmentActivity implements ActionBar.TabListener{

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
			
			String phones = "phones";
			
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

			View rootView = inflater.inflate(R.layout.phones, container, false);

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

			View rootView = inflater.inflate(R.layout.connected, container, false);
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

			View rootView = inflater.inflate(R.layout.smb, container, false);
			return rootView;
		}
	}
















	//    /**
	//     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
	//     * three primary sections of the app. We use a {@link android.support.v4.app.FragmentPagerAdapter}
	//     * derivative, which will keep every loaded fragment in memory. If this becomes too memory
	//     * intensive, it may be best to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
	//     */
	//    AppSectionsPagerAdapter mAppSectionsPagerAdapter;
	//
	//    /**
	//     * The {@link ViewPager} that will display the three primary sections of the app, one at a
	//     * time.
	//     */
	//    ViewPager mViewPager;
	//
	//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	//            Bundle savedInstanceState) {
	//    	
	//        View rootView = inflater.inflate(R.layout.frag_adds, container, false);
	//        
	//     // Create the adapter that will return a fragment for each of the three primary sections
	//        // of the app.
	//        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getFragmentManager());
	//
	//        // Set up the action bar.
	//        final ActionBar actionBar = getActionBar();
	//
	//        // Specify that the Home/Up button should not be enabled, since there is no hierarchical
	//        // parent.
	//        actionBar.setHomeButtonEnabled(false);
	//
	//        // Specify that we will be displaying tabs in the action bar.
	//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	//
	//        // Set up the ViewPager, attaching the adapter and setting up a listener for when the
	//        // user swipes between sections.
	//        mViewPager = (ViewPager) inflater.inflate(R.id.pager, (ViewGroup) rootView, true);
	//        mViewPager.setAdapter(mAppSectionsPagerAdapter);
	//        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
	//            @Override
	//            public void onPageSelected(int position) {
	//                // When swiping between different app sections, select the corresponding tab.
	//                // We can also use ActionBar.Tab#select() to do this if we have a reference to the
	//                // Tab.
	//                actionBar.setSelectedNavigationItem(position);
	//            }
	//        });
	//
	//        // For each of the sections in the app, add a tab to the action bar.
	//        for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
	//            // Create a tab with text corresponding to the page title defined by the adapter.
	//            // Also specify this Activity object, which implements the TabListener interface, as the
	//            // listener for when this tab is selected.
	//            actionBar.addTab(
	//                    actionBar.newTab()
	//                            .setText(mAppSectionsPagerAdapter.getPageTitle(i))
	//                            .setTabListener(this));
	//        }
	//        
	//        
	////        TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
	////        dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
	//        return rootView;
	//    }
	//
	//
	//    @Override
	//    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	//    }
	//
	//    @Override
	//    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	//        // When the given tab is selected, switch to the corresponding page in the ViewPager.
	//        mViewPager.setCurrentItem(tab.getPosition());
	//    }
	//
	//    @Override
	//    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	//    }
	//
	//    /**
	//     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
	//     * sections of the app.
	//     */
	//    public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {
	//
	//        public AppSectionsPagerAdapter(android.app.FragmentManager fragmentManager) {
	//            super(fragmentManager);
	//        }
	//
	//        @Override
	//        public Fragment getItem(int position) {
	//        	switch (position) {
	//        	case 0:
	//        		Fragment sectionOneFrag = new DummySectionFragment();
	//                Bundle args1 = new Bundle();
	//                args1.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
	//                sectionOneFrag.setArguments(args1);
	//        		return sectionOneFrag;
	//        	case 1:
	//        		Fragment sectionTwoFrag = new SectionFragment2();
	//                Bundle args2 = new Bundle();
	//                args2.putInt(SectionFragment2.ARG_SECTION_NUMBER, position + 1);
	//                sectionTwoFrag.setArguments(args2);
	//        		return sectionTwoFrag;
	//        	case 2:
	//        		Fragment sectionThreeFrag = new SectionFragment3();
	//                Bundle args3 = new Bundle();
	//                args3.putInt(SectionFragment3.ARG_SECTION_NUMBER, position + 1);
	//                sectionThreeFrag.setArguments(args3);
	//        		return sectionThreeFrag;
	//        	}
	//        	
	////            Fragment fragment = new DummySectionFragment();
	////            Bundle args = new Bundle();
	////            args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
	////            args.putInt(SectionFragment2.ARG_SECTION_NUMBER, position + 2);
	////            fragment.setArguments(args);
	////            return fragment;
	//        	return null;
	//        }
	//
	//        @Override
	//        public int getCount() {
	//            return 3;
	//        }
	//
	//        @Override
	//        public CharSequence getPageTitle(int position) {
	//            return "Section " + (position + 1);
	//        }
	//    }
	//
	//    /**
	//     * A fragment that launches other parts of the demo application.
	//     */
	//    public static class DummySectionFragment extends Fragment {
	//        /**
	//         * The fragment argument representing the section number for this
	//         * fragment.
	//         */
	//        public static final String ARG_SECTION_NUMBER = "section_number";
	//
	//        public DummySectionFragment() {
	//        }
	//
	//        @Override
	//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
	//                Bundle savedInstanceState) {
	//        	
	//            View rootView = inflater.inflate(R.layout.fragment_main_dummy, container, false);
	//            TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
	//            dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
	//            return rootView;
	//        }
	//    }
	//    
	//    public static class SectionFragment2 extends Fragment {
	//        /**
	//         * The fragment argument representing the section number for this
	//         * fragment.
	//         */
	//        public static final String ARG_SECTION_NUMBER = "section_number";
	//
	//        public SectionFragment2() {
	//        }
	//
	//        @Override
	//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
	//                Bundle savedInstanceState) {
	//        	
	//            View rootView = inflater.inflate(R.layout.fragment_section2, container, false);
	//            TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
	//            dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
	//            return rootView;
	//        }
	//    }
	//    
	//    public static class SectionFragment3 extends Fragment {
	//        /**
	//         * The fragment argument representing the section number for this
	//         * fragment.
	//         */
	//        public static final String ARG_SECTION_NUMBER = "section_number";
	//
	//        public SectionFragment3() {
	//        }
	//
	//        @Override
	//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
	//                Bundle savedInstanceState) {
	//        	
	//            View rootView = inflater.inflate(R.layout.fragment_section3, container, false);
	//            TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
	//            dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
	//            return rootView;
	//        }
	//    }


}