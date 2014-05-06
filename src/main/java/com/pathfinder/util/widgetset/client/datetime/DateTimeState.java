package com.pathfinder.util.widgetset.client.datetime;

/**
 * Contains the last synchronized value of system time and the format of the
 * date and time according to the last known UI's locale; states are shared by
 * widget and server
 * 
 * @author tim
 * 
 */
public class DateTimeState extends com.vaadin.shared.AbstractComponentState {

	/**
	 * Server-Time in Milliseconds (value of last synchronisation)
	 */
	private double time;

	/**
	 * Format of the Date
	 */
	private String dateFormat;

	/**
	 * Format of the Time
	 */
	private String timeFormat;

	/**
	 * @return the time
	 */
	public double getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(double time) {
		this.time = time;
	}

	/**
	 * @return the dateFormat
	 */
	public String getDateFormat() {
		return dateFormat;
	}

	/**
	 * @param dateFormat
	 *            the dateFormat to set
	 */
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	/**
	 * @return the timeFormat
	 */
	public String getTimeFormat() {
		return timeFormat;
	}

	/**
	 * @param timeFormat
	 *            the timeFormat to set
	 */
	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}

}