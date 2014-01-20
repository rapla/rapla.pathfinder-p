/**
 * 
 */
package com.pathfinder.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Contains property for current date of calendar in detail view
 * 
 * @author tim
 * 
 */
public class CalendarModel {
	private Date beginningOfCurrentDay;

	/**
	 * @return the beginningOfCurrentDay
	 */
	public Date getBeginningOfCurrentDay() {
		return beginningOfCurrentDay;
	}

	/**
	 * @param beginningOfCurrentDay
	 *            the beginningOfCurrentDay to set
	 */
	public void setBeginningOfCurrentDay(Date beginningOfCurrentDay) {
		this.beginningOfCurrentDay = getBeginningOfDate(beginningOfCurrentDay);
	}

	/**
	 * @return the endOfCurrentDay
	 */
	public Date getEndOfCurrentDay() {
		return getEndOfDate(beginningOfCurrentDay);
	}

	/**
	 * @param endOfCurrentDay
	 *            the endOfCurrentDay to set
	 */
	public void setEndOfCurrentDay(Date endOfCurrentDay) {
		this.beginningOfCurrentDay = getBeginningOfDate(endOfCurrentDay);
	}

	private Date getBeginningOfDate(Date date) {
		GregorianCalendar now = new GregorianCalendar();
		now.setTime(date);
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		return now.getTime();
	}

	private Date getEndOfDate(Date date) {
		GregorianCalendar now = new GregorianCalendar();
		now.setTime(date);
		now.set(Calendar.HOUR_OF_DAY, 23);
		now.set(Calendar.MINUTE, 59);
		return now.getTime();
	}

}
