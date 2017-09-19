package in.raveesh.chronicle;

import java.util.List;

public interface Chronicle {

	/**
	 * Method to mark if an event has been performed by a user
	 * @param event String name for the event
	 */
	void did(String event);

	/**
	 * Method to mark if and when an event has been performed by a user
	 * @param event String name for the event
	 * @param timestamp Long representing epoch time
	 */
	void did(String event, long timestamp);

	/**
	 * Returns true if an event has ever been performed by a user
	 * @param event Name of event
	 * @return True if has been performed
	 */
	boolean hasDone(String event);

	/**
	 * Returns number of times an event has been performed by the user
	 * @param event Name of event
	 * @return Number of times done
	 */
	int timesDone(String event);

	/**
	 * Returns if an event has been performed by the user after a certain time
	 * @param event Name of event
	 * @param timestamp Epoch time after which we need to check
	 * @return True if event performed after timestamp
	 */
	boolean hasDoneSince(String event, long timestamp);

	/**
	 * Returns number of times an event has been performed by the user after a certain time
	 * @param event Name of event
	 * @param timestamp Epoch time after which we need to check
	 * @return Number of times performed after timestamp
	 */
	int timesDoneSince(String event, long timestamp);

	/**
	 * Returns the time that has passed since last occurence of an event
	 * @param event Name of event
	 * @return Milliseconds since last occurrence
	 */
	long timeSinceLastOccurence(String event);

	/**
	 * Returns a list of Records for all occurrences of a type of event
	 * @param event Name of an event
	 * @return List of Records for all occurences of the event
	 */
	List<Record> allTimesDid(String event);

	void addEventOccurredListener(String event, EventOccurredListener listener);

	void removeEventOccurredListener(String event, EventOccurredListener listener);

	void removeAllEventOccurredListeners(String event);
}

