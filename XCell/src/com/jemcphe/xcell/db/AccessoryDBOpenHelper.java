package com.jemcphe.xcell.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AccessoryDBOpenHelper extends SQLiteOpenHelper {

	private static final String LOGTAG = "XCELL";

	private static final String DATABASE_NAME = "accessories.db";
	private static final int DATABASE_VERSION = 1;
	
	public static final String TABLE_ACCESSORIES = "accessories";
	public static final String COLUMN_ID = "accessoryId";
	public static final String COLUMN_DATE = "date";
	public static final String COLUMN_ACCESSORY = "accessory";
	public static final String COLUMN_QTY = "qty";
	public static final String COLUMN_TOTAL = "total";
	
	private static final String TABLE_CREATE = 
			"CREATE TABLE " + TABLE_ACCESSORIES + " (" +
			COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			COLUMN_DATE + " TEXT, " +
			COLUMN_ACCESSORY + " TEXT, " +
			COLUMN_QTY + " TEXT, " +
			COLUMN_TOTAL + " TEXT " +
			")";
	
	public AccessoryDBOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase dataBase) {
		dataBase.execSQL(TABLE_CREATE);
		Log.i(LOGTAG, "Table Has been created");
	}

	@Override
	public void onUpgrade(SQLiteDatabase dataBase, int oldVersion, int newVersion) {
		dataBase.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCESSORIES);
	}

}
