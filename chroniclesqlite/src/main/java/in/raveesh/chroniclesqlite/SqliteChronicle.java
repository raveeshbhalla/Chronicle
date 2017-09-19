package in.raveesh.chroniclesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import in.raveesh.chronicle.Chronicle;
import in.raveesh.chronicle.EventOccurredListener;
import in.raveesh.chronicle.Record;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Raveesh on 19/09/17.
 */

public class SqliteChronicle extends Chronicle {

	private DbHelper dbHelper;

	public SqliteChronicle(Context context) {
		dbHelper = new DbHelper(context);
	}

	@Override
	public void did(String event) {
		did(event, System.currentTimeMillis());
	}

	@Override
	public void did(String event, long timestamp) {
		ContentValues values = new ContentValues();
		values.put(DbHelper.KEY_EVENT_NAME, event);
		values.put(DbHelper.KEY_TIMESTAMP, timestamp);
		dbHelper.insert(values);
		notifyEventOccurred(event, timestamp);
	}

	@Override
	public boolean hasDone(String event) {
		Cursor cursor = dbHelper.query(DbHelper.KEY_EVENT_NAME + "=?",
				new String[]{event});
		boolean hasDone = !(cursor == null || cursor.getCount() == 0);
		if (cursor != null) {
			cursor.close();
		}
		return hasDone;
	}

	@Override
	public int timesDone(String event) {
		Cursor cursor = dbHelper.query(DbHelper.KEY_EVENT_NAME + "=?",
				new String[]{event});
		int timesDone = 0;
		if (cursor != null) {
			timesDone = cursor.getCount();
			cursor.close();
		}
		return timesDone;
	}

	@Override
	public boolean hasDoneSince(String event, long timestamp) {
		Cursor cursor = dbHelper.query(DbHelper.KEY_EVENT_NAME + "=? AND "
						+ DbHelper.KEY_TIMESTAMP + " >?",
				new String[]{event, String.valueOf(timestamp)});
		boolean hasDone = !(cursor == null || cursor.getCount() == 0);
		if (cursor != null) {
			cursor.close();
		}
		return hasDone;
	}

	@Override
	public int timesDoneSince(String event, long timestamp) {
		Cursor cursor = dbHelper.query(DbHelper.KEY_EVENT_NAME + "=? AND "
						+ DbHelper.KEY_TIMESTAMP + " >?",
				new String[]{event, String.valueOf(timestamp)});
		int timesDone = 0;
		if (cursor != null) {
			timesDone = cursor.getCount();
			cursor.close();
		}
		return timesDone;
	}

	@Override
	public long timeSinceLastOccurence(String event) {
		Cursor cursor = dbHelper.query(DbHelper.KEY_EVENT_NAME + "=?",
				new String[]{event});
		if (cursor != null && cursor.moveToLast()) {
			long lastOccurrence = cursor.getLong(cursor.getColumnIndex(DbHelper.KEY_TIMESTAMP));
			cursor.close();
			return lastOccurrence;
		} else {
			return -1;
		}
	}

	@Override
	public List<Record> allTimesDid(String event) {
		Cursor cursor = dbHelper.query(DbHelper.KEY_EVENT_NAME + "=?",
				new String[]{event});
		List<Record> records = new ArrayList<>();
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			do {
				records.add(new EventModel(cursor));
			} while (cursor.moveToNext());
			cursor.close();
		}
		return records;
	}
}
