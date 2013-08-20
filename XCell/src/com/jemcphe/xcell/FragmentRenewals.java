package com.jemcphe.xcell;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
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
    	
        view = inflater.inflate(R.layout.frag_renewals, container, false);
        
		final TextView renewalText = (TextView) view.findViewById(R.id.renewalValue);
		renewalText.setText(String.valueOf(renewalValue));
        
        SeekBar seekBar = (SeekBar) view.findViewById(R.id.renewalSeekBar);
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
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
        
        
        return view;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
