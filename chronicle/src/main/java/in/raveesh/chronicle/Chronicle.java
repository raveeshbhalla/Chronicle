package in.raveesh.chronicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Chronicle {

	private HashMap<String, List<EventOccurredListener>> allListeners;

	/**
	 * Method to mark if an event has been performed by a user
	 *
	 * @param event String name for the event
	 */
	public abstract void did(String event);

	/**
	 * Method to mark if and when an event has been performed by a user
	 *
	 * @param event String name for the event
	 * @param timestamp Long representing epoch time
	 */
	public abstract void did(String event, long timestamp);

	/**
	 * Returns true if an event has ever been performed by a user
	 *
	 * @param event Name of event
	 * @return True if has been performed
	 */
	public abstract boolean hasDone(String event);

	/**
	 * Returns number of times an event has been performed by the user
	 *
	 * @param event Name of event
	 * @return Number of times done
	 */
	public abstract int timesDone(String event);

	/**
	 * Returns if an event has been performed by the user after a certain time
	 *
	 * @param event Name of event
	 * @param timestamp Epoch time after which we need to check
	 * @return True if event performed after timestamp
	 */
	public abstract boolean hasDoneSince(String event, long timestamp);

	/**
	 * Returns number of times an event has been performed by the user after a certain time
	 *
	 * @param event Name of event
	 * @param timestamp Epoch time after which we need to check
	 * @return Number of times performed after timestamp
	 */
	public abstract int timesDoneSince(String event, long timestamp);

	/**
	 * Returns the time that has passed since last occurence of an event
	 *
	 * @param event Name of event
	 * @return Milliseconds since last occurrence
	 */
	public abstract long timeSinceLastOccurence(String event);

	/**
	 * Returns a list of Records for all occurrences of a type of event
	 *
	 * @param event Name of an event
	 * @return List of Records for all occurences of the event
	 */
	public abstract List<Record> allTimesDid(String event);

	/**
	 * Adds an EventOccurredListener for a particular event
	 * @param event The event for which the listener will be fired
	 * @param listener Callback to be called
	 */
	public void addEventOccurredListener(String event, EventOccurredListener listener) {
		if (allListeners == null) {
			allListeners = new HashMap<>();
		}
		if (!allListeners.containsKey(event)) {
			allListeners.put(event, new ArrayList<EventOccurredListener>());
		}
		List<EventOccurredListener> eventListeners = allListeners.get(event);
		eventListeners.add(listener);
	}

	/**
	 * Removes an EventOccurredListener for a particular event
	 * @param event The event for which the listener would be removed
	 * @param listener Listener to be removed
	 */
	public void removeEventOccurredListener(String event, EventOccurredListener listener) {
		if (allListeners != null && allListeners.containsKey(event)) {
			allListeners.get(event).remove(listener);
		}
	}

	/**
	 * Removes all listeners for a particular event
	 * @param event The event for which all the listeners would be removed
	 */
	public void removeAllEventOccurredListeners(String event) {
		if (allListeners != null) {
			allListeners.put(event, new ArrayList<EventOccurredListener>());
		}
	}

	/**
	 * Removes all listeners for all the events
	 */
	public void removeAllEventOccurredListeners() {
		allListeners.clear();
	}

	protected void notifyEventOccurred(String event, long timestamp) {
		if (allListeners != null && allListeners.containsKey(event)) {
			for (EventOccurredListener eventOccurredListener : allListeners.get(event)) {
				eventOccurredListener.eventOccurred(timestamp);
			}
		}
	}
}

