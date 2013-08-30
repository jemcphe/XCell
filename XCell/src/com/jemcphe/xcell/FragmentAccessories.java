package com.jemcphe.xcell;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jemcphe.xcell.db.AccessoriesDataSource;
import com.jemcphe.xcell.db.AccessoryDBOpenHelper;
import com.jemcphe.xcell.items.Accessory;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentAccessories extends Fragment implements OnClickListener {
	
	private static final String LOGTAG = "XCELL";
	
	EditText accessoryField;
    EditText priceField;
    EditText qtyField;
    
    AccessoriesDataSource dataSource;
	
	//Create an Interface to communicate with Activity
		public  FragmentAccessories() {
			
		}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
        View rootView = inflater.inflate(R.layout.frag_accessories, container, false);
        
        String testDate = getDate();
        Log.i("Test Date", testDate);
        
        //SQLite setup
        dataSource = new AccessoriesDataSource(getActivity());
        dataSource.open();
        
        
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
		
		//String Values
		String date = getDate();
		String accessoryName = accessoryField.getText().toString();
		String qtyString = qtyField.getText().toString();
		String priceString = priceField.getText().toString();
				
		//Create Accessory Value
		Accessory accessory = new Accessory();
//		accessory.setDate(date);
//		accessory.setAccessoryName(accessoryName);
//		accessory.setQty(qtyString);
//		accessory.setTotal(priceString);
		
		accessory = dataSource.createAccessory(date, accessoryName, qtyString, priceString);
		
		Log.i(LOGTAG, accessory.toString());
		
		Log.i(LOGTAG, "Accessory created with ID " + accessory.getId());
		
		DialogFragment dialog = new AccessorySaveDialog();
		dialog.show(getFragmentManager(), "saved");
		
	}
    
	@SuppressLint("SimpleDateFormat")
	public String getDate(){
		Date currentDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM'/'dd");
		String formattedDate = formatter.format(currentDate);
		return formattedDate;
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
