package com.jemcphe.xcell;

import java.io.File;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class FragmentRenewals extends Fragment implements OnClickListener {

	View view;
	int renewalValue;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
	}

	public FragmentRenewals() {
    }

	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		File renewalFile = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.jemcphe.xcell/files/renewal_today");
		
    	String todayRenewalValue = FileInfo.readStringFile(getActivity(), "renewal_today", true);
    	
        view = inflater.inflate(R.layout.frag_renewals, container, false);
        
		final TextView renewalText = (TextView) view.findViewById(R.id.renewalValue);
        
		
		
        SeekBar seekBar = (SeekBar) view.findViewById(R.id.renewalSeekBar);
        if (renewalFile.exists()) {
        	int seekBarValue = Integer.valueOf(todayRenewalValue);
        	seekBar.setProgress(seekBarValue);
    		renewalText.setText(todayRenewalValue);
    	}
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				String todayRenewals = (String) renewalText.getText();
				FileInfo.storeStringFile(getActivity(), "renewal_today", todayRenewals, true);
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
					renewalValue = progress;
//			        renewalText.invalidate();
					Log.i("SeekBar Progress", String.valueOf(renewalValue));
					renewalText.setText(String.valueOf(progress));
				}
			}
		});
        
        Button updateButton = (Button) view.findViewById(R.id.renewalUpdateBtn);
        updateButton.setOnClickListener(this);
        return view;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Log.i("updatebutton", "Clicked!!");
	}
}
