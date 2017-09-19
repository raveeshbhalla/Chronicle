package in.raveesh.chroniclesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Raveesh on 19/09/17.
 */

class DbHelper extends SQLiteOpenHelper {
	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "chronicle";
	private static final String TABLE_NAME = "history";
	static final String KEY_ID = "ROWID";
	static final String KEY_EVENT_NAME = "event_name";
	static final String KEY_TIMESTAMP = "timestamp";

	DbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}


	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		String createTable = "CREATE TABLE " + TABLE_NAME + "("
				+  KEY_EVENT_NAME + " TEXT NOT NULL,"
				+ KEY_TIMESTAMP + " INTEGER" + ")";
		sqLiteDatabase.execSQL(createTable);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
		sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(sqLiteDatabase
		);
	}

	/**
	 * Insert contentValues into the database table
	 * @param contentValues Event's details
	 */
	void insert(ContentValues contentValues) {
		SQLiteDatabase db = getWritableDatabase();
		db.insert(TABLE_NAME, null, contentValues);
		db.close();
	}

	@Nullable
	Cursor query(@NonNull String selection, String[] arguments) {
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME,
				new String[] {KEY_ID, KEY_EVENT_NAME, KEY_TIMESTAMP},
				selection, arguments,
				null, null, null
				);
		cursor.moveToFirst();
		return cursor;
	}
}
