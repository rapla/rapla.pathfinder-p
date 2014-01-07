package com.pathfinder.util.widgetset.client.datetime;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * Widget for displaying the current date and time; the format will be set
 * according to the current locale
 * 
 * @author tim
 * 
 */
public class DateTimeWidget extends FlowPanel {

	public static final String CLASSNAME = "datetime";
	public static final String STYLENAME_DATE_LABEL = "dateLabel";
	public static final String STYLENAME_TIME_LABEL = "timeLabel";
	private Timer timer;
	private Date time = new Date();
	private long timeInMs;
	private DateTimeFormat dateFormatter;
	private DateTimeFormat timeFormatter;
	private Label dateLabel = new Label();
	private Label timeLabel = new Label();
	private String dateFormat = "dd.MM.yy";
	private String timeFormat = "HH:mm:ss";

	public DateTimeWidget() {

		addStyleName(CLASSNAME);

		dateFormatter = DateTimeFormat.getFormat(dateFormat);
		timeFormatter = DateTimeFormat.getFormat(timeFormat);

		setTime(new Date().getTime());

		dateLabel.setText(dateFormatter.format(time));
		timeLabel.setText(timeFormatter.format(time));

		add(dateLabel);
		add(timeLabel);

		dateLabel.setStyleName(STYLENAME_DATE_LABEL);
		timeLabel.setStyleName(STYLENAME_TIME_LABEL);

		timer = new Timer() {
			@Override
			public void run() {
				updateDateTime();
			}
		};
		timer.scheduleRepeating(1000);

	}

	/**
	 * Updating time (adding 1 second) and setting label accordingly
	 */
	private void updateDateTime() {
		timeInMs += 1000;
		time.setTime(timeInMs);
		updateFormatter();
	}

	private void updateFormatter() {
		this.dateFormatter = DateTimeFormat.getFormat(dateFormat);
		dateLabel.setText(dateFormatter.format(time));
		this.timeFormatter = DateTimeFormat.getFormat(timeFormat);
		timeLabel.setText(timeFormatter.format(time));
	}

	/**
	 * Sets the current time in milliseconds
	 * 
	 * @param timeInMs
	 *            time in Milliseconds
	 */
	public void setTime(long timeInMs) {
		this.timeInMs = timeInMs;
	}

	/**
	 * @param dateFormat
	 *            the dateFormat to set
	 */
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	/**
	 * @param timeFormat
	 *            the timeFormat to set
	 */
	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}
}