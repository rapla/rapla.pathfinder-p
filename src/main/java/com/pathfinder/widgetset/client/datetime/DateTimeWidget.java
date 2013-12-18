package com.pathfinder.widgetset.client.datetime;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;

/**
 * Widget for displaying the current date and time; the format will be set
 * according to the current locale
 * 
 * @author tim
 * 
 */
public class DateTimeWidget extends Label {

	public static final String CLASSNAME = "datetime";
	private Timer timer;
	private Date time = new Date();
	private long timeInMs;
	private DateTimeFormat formatter = DateTimeFormat
			.getFormat("dd.MM.yy HH:mm:ss");

	public DateTimeWidget() {

		setStyleName(CLASSNAME);

		setTime(new Date().getTime());

		setText(formatter.format(time));

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
		setText(formatter.format(time));
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

	// /**
	// * Sets the current locale
	// *
	// * @param locale
	// * current locale
	// */
	// public void setLocale(String locale) {
	// this.updateLocale();
	// this.locale = locale;
	// this.formatter = DateTimeFormat
	// .getFormat(PredefinedFormat.DATE_TIME_MEDIUM);
	// }
	//
	// /**
	// * Updating the locale in Window; necessary to get the right formatter for
	// * date and time
	// */
	// private void updateLocale() {
	// Window.Location.assign(Window.Location.createUrlBuilder()
	// .setParameter(LocaleInfo.getLocaleQueryParam(), locale)
	// .buildString());
	// }
}