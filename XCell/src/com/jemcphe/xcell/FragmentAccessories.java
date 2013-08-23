package com.jemcphe.xcell;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentAccessories extends Fragment implements OnClickListener {

	public static JSONArray data;
	
	EditText accessoryField;
    EditText priceField;
    EditText qtyField;
	
	//Create an Interface to communicate with Activity
		public  FragmentAccessories() {
			
		}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
        View rootView = inflater.inflate(R.layout.frag_accessories, container, false);
        
        accessoryField = (EditText) rootView.findViewById(R.id.accessoryField);
        priceField = (EditText) rootView.findViewById(R.id.priceField);
        qtyField = (EditText) rootView.findViewById(R.id.qtyField);
        
        accessoryField.setText("Car Charger");
        priceField.setText("19.99");
        qtyField.setText("1");
        
        Button button = (Button) rootView.findViewById(R.id.addButton);
        button.setOnClickListener(this);

        return rootView;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Log.i("Add Accessory Button", "Clicked!!");
		
		//Create JSONObject & JSONArray
//		JSONObject json = new JSONObject();
		JSONObject jsonAccessory = new JSONObject();
		
		
		try {
			jsonAccessory.put("type", accessoryField.getText().toString());
			jsonAccessory.put("price", priceField.getText().toString());
			jsonAccessory.put("qty", qtyField.getText().toString());
//			data.put(jsonAccessory);
			
			//FileInfo.storeStringFile(getActivity(), "accessory_data", data.toString(), true);
			
			Log.i("JSONObject", jsonAccessory.toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DialogFragment dialog = new AccessorySaveDialog();
		dialog.show(getFragmentManager(), "saved");
		
	}
    
	
	@SuppressLint("ValidFragment")
	public class AccessorySaveDialog extends DialogFragment {
	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        // Use the Builder class for convenient dialog construction
	        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	        builder.setMessage(R.string.accessory_dialog)
	               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                       dialog.dismiss();
	                   }
	               });
	               
	        // Create the AlertDialog object and return it
	        return builder.create();
	    }
	}
    
	
}
