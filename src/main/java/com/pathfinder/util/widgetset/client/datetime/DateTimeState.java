package com.pathfinder.util.widgetset.client.datetime;

/**
 * Contains the last synchronized value of system time and the last known UI's
 * locale; states are shared by widget and server
 * 
 * @author tim
 * 
 */
public class DateTimeState extends com.vaadin.shared.AbstractComponentState {

	/**
	 * Server-Time in Milliseconds (value of last synchronisation)
	 */
	private long time;

	/**
	 * @return the time
	 */
	public long getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(long time) {
		this.time = time;
	}

}