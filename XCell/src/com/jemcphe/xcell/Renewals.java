package com.jemcphe.xcell;

import android.app.Activity;
import android.os.Bundle;

public class Renewals extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frag_renewals);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

}
