package in.raveesh.chroniclesqlite;

import android.database.Cursor;
import in.raveesh.chronicle.Record;

/**
 * Created by Raveesh on 19/09/17.
 */

public class EventModel implements Record {
	private int id;
	private String eventName;
	private long timestamp;

	public EventModel(Cursor cursor) {
		id = cursor.getInt(cursor.getColumnIndex(DbHelper.KEY_ID));
		eventName = cursor.getString(cursor.getColumnIndex(DbHelper.KEY_EVENT_NAME));
		timestamp = cursor.getLong(cursor.getColumnIndex(DbHelper.KEY_TIMESTAMP));
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getEventName() {
		return eventName;
	}

	@Override
	public long timestamp() {
		return timestamp;
	}
}
