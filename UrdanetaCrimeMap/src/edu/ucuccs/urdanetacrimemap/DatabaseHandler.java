package edu.ucuccs.urdanetacrimemap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

	// for our logs
	public static final String TAG = "DatabaseHandler.java";

	// database version
	private static final int DATABASE_VERSION = 5;

	// database name
	protected static final String DATABASE_NAME = "NinjaDatabase2";

	// table details
	public String tableName = "locations";
	public String fieldObjectId = "id";
	public String fieldObjectName = "name";
	
	// table details
		public String tableNameD = "direction";
		public String fieldObjectIdD = "idD";
		public String fieldObjectNameD = "nameD";

	
	
	// constructor
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// creating table
	@Override
	public void onCreate(SQLiteDatabase db) {

		String sql = "";

		sql += "CREATE TABLE " + tableName;
		sql += " ( ";
		sql += fieldObjectId + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
		sql += fieldObjectName + " TEXT ";
		sql += " ) ";

		db.execSQL(sql);
		
		//for direction
		String sqlD = "";

		sqlD += "CREATE TABLE " + tableNameD;
		sqlD += " ( ";
		sqlD += fieldObjectIdD + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
		sqlD += fieldObjectNameD + " TEXT ";
		sqlD += " ) ";

		db.execSQL(sqlD);

	}

	// When upgrading the database, it will drop the current table and recreate.
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		String sql = "DROP TABLE IF EXISTS " + tableName;
		db.execSQL(sql);
		
		String sqlD = "DROP TABLE IF EXISTS " + tableNameD;
		db.execSQL(sqlD);

		onCreate(db);
	}

	// create new record LOCATION
	// @param myObj contains details to be added as single row.
	public boolean create(MyObject myObj) {

		boolean createSuccessful = false;

		if (!checkIfExists(myObj.objectName)) {

			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues values = new ContentValues();
			values.put(fieldObjectName, myObj.objectName);
			createSuccessful = db.insert(tableName, null, values) > 0;

			db.close();

			if (createSuccessful) {
				Log.e(TAG, myObj.objectName + " created.");
			}
		}

		return createSuccessful;
	}
	
	
	// create new record DIRECTION
		// @param myObj contains details to be added as single row.
		public boolean createD(MyObjectD myObjD) {

			boolean createSuccessfulD = false;

			if (!checkIfExistsD(myObjD.objectNameD)) {

				SQLiteDatabase db = this.getWritableDatabase();

				ContentValues values = new ContentValues();
				values.put(fieldObjectNameD, myObjD.objectNameD);
				createSuccessfulD = db.insert(tableNameD, null, values) > 0;

				db.close();

				if (createSuccessfulD) {
					Log.e(TAG, myObjD.objectNameD + " created.");
				}
			}

			return createSuccessfulD;
		}

	// check if a record exists so it won't insert the next time you run this
	// code
	public boolean checkIfExists(String objectName) {

		boolean recordExists = false;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT " + fieldObjectId + " FROM "
				+ tableName + " WHERE " + fieldObjectName + " = '" + objectName
				+ "'", null);

		if (cursor != null) {

			if (cursor.getCount() > 0) {
				recordExists = true;
			}
		}

		cursor.close();
		db.close();

		return recordExists;
	}
	
//for direction	
	public boolean checkIfExistsD(String objectNameD) {

		boolean recordExistsD = false;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT " + fieldObjectIdD + " FROM "
				+ tableNameD + " WHERE " + fieldObjectNameD + " = '" + objectNameD
				+ "'", null);

		if (cursor != null) {

			if (cursor.getCount() > 0) {
				recordExistsD = true;
			}
		}

		cursor.close();
		db.close();

		return recordExistsD;
	}

	// Read records related to the search term
	public MyObject[] read(String searchTerm) {

		// select query
		String sql = "";
		sql += "SELECT * FROM " + tableName;
		sql += " WHERE " + fieldObjectName + " LIKE '%" + searchTerm + "%'";
		sql += " ORDER BY " + fieldObjectId + " DESC";
		sql += " LIMIT 0,5";

		SQLiteDatabase db = this.getWritableDatabase();

		// execute the query
		Cursor cursor = db.rawQuery(sql, null);

		int recCount = cursor.getCount();

		MyObject[] ObjectItemData = new MyObject[recCount];
		int x = 0;

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {

				String objectName = cursor.getString(cursor
						.getColumnIndex(fieldObjectName));
				Log.e(TAG, "objectName: " + objectName);

				MyObject myObject = new MyObject(objectName);

				ObjectItemData[x] = myObject;

				x++;

			} while (cursor.moveToNext());
		}

		cursor.close();
		db.close();

		return ObjectItemData;

	}
	
	// for direction
	public MyObjectD[] readD(String searchTermD) {

		// select query
		String sqlD = "";
		sqlD += "SELECT * FROM " + tableNameD;
		sqlD += " WHERE " + fieldObjectNameD + " LIKE '%" + searchTermD + "%'";
		sqlD += " ORDER BY " + fieldObjectIdD + " DESC";
		sqlD += " LIMIT 0,5";

		SQLiteDatabase db = this.getWritableDatabase();

		// execute the query
		Cursor cursor = db.rawQuery(sqlD, null);

		int recCount = cursor.getCount();

		MyObjectD[] ObjectItemDataD = new MyObjectD[recCount];
		int x = 0;

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {

				String objectNameD = cursor.getString(cursor
						.getColumnIndex(fieldObjectNameD));
				Log.e(TAG, "objectName: " + objectNameD);

				MyObjectD myObjectD = new MyObjectD(objectNameD);

				ObjectItemDataD[x] = myObjectD;

				x++;

			} while (cursor.moveToNext());
		}

		cursor.close();
		db.close();

		return ObjectItemDataD;

	}

}