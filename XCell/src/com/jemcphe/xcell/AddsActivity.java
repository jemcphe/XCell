package com.jemcphe.xcell;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AddsActivity extends android.support.v4.app.Fragment {

	public AddsActivity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
        View rootView = inflater.inflate(R.layout.adds_layout, container, false);
        
        return rootView;
    }
	
}
