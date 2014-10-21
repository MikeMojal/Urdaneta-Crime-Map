package edu.ucuccs.urdanetacrimemap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {

	// database
	static final int DATABASE_VERSION = 1;
	static final String DATABASE_NAME = "db_user";

	// field for contact table
	static final String LOCID = "locid";
	static final String LOCATIOND = "locationD";
	
	static final String DIRID = "dirid";
	static final String DIRECTIOND = "directionD";
		static final String DIRECTIONDLoc = "directionDLoc";

	// table

	static final String TABLE_NAME2 = "tbl_location";

	static final String TABLE_NAME3 = "tbl_direction";

	// CREATE TABLE

	static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_NAME2 + " ( "
			+ LOCID + " integer primary key autoincrement, " + LOCATIOND
			+ " varchar(255) not null )";

	static final String DATABASE_CREATEDIR = "CREATE TABLE " + TABLE_NAME3 + " ( "
			+ DIRID + " integer primary key autoincrement, " + DIRECTIOND
			+ " varchar(255) not null, " + DIRECTIONDLoc
			+ " varchar(255) not null )";
	
	// DBHELPER
	final Context context;

	DatabaseHelper DBHelper;

	SQLiteDatabase db;

	public DBAdapter(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		// ON CREATE
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			try {

				db.execSQL(DATABASE_CREATE);
				db.execSQL(DATABASE_CREATEDIR);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// ON UPGRADE (DROP DUPLICATE)
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub

			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
			onCreate(db);
		}
	}

	public DBAdapter open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		DBHelper.close();
	}

	// METHOD SAVE CONTACT

	public long method_savecon(String locationD) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(LOCATIOND, locationD);
		return db.insert(TABLE_NAME2, null, initialValues);

	}
	
	public long method_savecondir(String directionD, String directionDLoc) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(DIRECTIOND, directionD);
		initialValues.put(DIRECTIONDLoc, directionDLoc);
		return db.insert(TABLE_NAME3, null, initialValues);

	}


	// SHOW ALL LOCATION RECORDS
	public Cursor method_showallrecords() {
		Cursor search_cursor = db.query(true, TABLE_NAME2, new String[] {
				LOCID, LOCATIOND }, null, null, null, null, null, null);
		return search_cursor;

	}

	public Cursor method_showallrecordsdir() {
		Cursor search_cursor = db.query(true, TABLE_NAME3, new String[] {
				DIRID, DIRECTIOND,DIRECTIONDLoc }, null, null, null, null, null, null);
		return search_cursor;

	}
	
	// METHOD FOR SEARCH ID

	public Cursor method_searchid(long cid) {
		Cursor search_cursor = db.query(true, TABLE_NAME2, new String[] {
				LOCID, LOCATIOND }, LOCID + "=" + cid + "", null, null, null,
				null, null);
		return search_cursor;
	}
	
	
	public Cursor method_searchiddir(long dircid) {
		Cursor search_cursor = db.query(true, TABLE_NAME3, new String[] {
				DIRID, DIRECTIOND,DIRECTIONDLoc  }, DIRID + "=" + dircid + "", null, null, null,
				null, null);
		return search_cursor;
	}
	
	

	// DELETE LOCATION HISTORY

	public boolean method_delete(long locid) {
		return db.delete(TABLE_NAME2, LOCID + "=" + locid + "", null) > 0;

	}

	public void method_deleteAll() {

		Cursor c = method_showallrecords();
		long rowId = c.getColumnIndexOrThrow(LOCID);
		if (c.moveToFirst()) {
			do {
				method_delete(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}
	
	
	//delete
	public boolean method_deletedir(long dirid) {
		return db.delete(TABLE_NAME3, DIRID + "=" + dirid + "", null) > 0;

	}

	public void method_deleteAlldir() {

		Cursor c = method_showallrecordsdir();
		long rowId = c.getColumnIndexOrThrow(DIRID);
		if (c.moveToFirst()) {
			do {
				method_deletedir(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}
}