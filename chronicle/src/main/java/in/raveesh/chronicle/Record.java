package in.raveesh.chronicle;

/**
 * Created by Raveesh on 18/09/17.
 */

public interface Record {

	/**
	 * Returns a unique ID for the particular Record
	 * @return ID for record
	 */
	int getId();

	/**
	 * Returns the name to of the event associated with the Record
	 * @return Name
	 */
	String getEventName();

	/**
	 * Returns the timestamp of the Record
	 * @return Timestamp from epoch
	 */
	long timestamp();
}
