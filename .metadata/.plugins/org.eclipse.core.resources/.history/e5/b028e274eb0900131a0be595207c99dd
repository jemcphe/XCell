package com.jemcphe.xcell;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;

public class FragmentAccessories extends Fragment implements OnClickListener {

	//Create an Interface to communicate with Activity
		public interface OnAccessoryAdded {
			void onAccessoryAdded();
		}

		//Create a private connection to MainActivity method startSearchActivity
		private OnAccessoryAdded parentActivity;
		
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
				super.onAttach(activity);
				
				if(activity instanceof OnAccessoryAdded){
					parentActivity = (OnAccessoryAdded) activity;
				}
				else {
					throw new ClassCastException(activity.toString() + "must implement onAccessoryAdded");
				}
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
        View rootView = inflater.inflate(R.layout.frag_accessories, container, false);
        
        Button button = (Button) rootView.findViewById(R.id.addButton);
        button.setOnClickListener(new OnClickListener() {
			
        	@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
        		// 1. Instantiate an AlertDialog.Builder with its constructor
        		final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        		
        		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        	           public void onClick(DialogInterface dialog, int id) {
        	               // User clicked OK button
        	        	   builder.setMessage("Accessory Added Successfully")
            		       .setTitle("Success");
        	           }
        	       });

        		// 2. Chain together various setter methods to set the dialog characteristics
        		

        		// 3. Get the AlertDialog from create()
        		AlertDialog dialog = builder.create();
			}
		});

        return rootView;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		parentActivity.onAccessoryAdded();
	}
    
    
    
	
}
