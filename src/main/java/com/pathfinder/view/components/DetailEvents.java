package com.pathfinder.view.components;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.pathfinder.model.EventModel;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.components.calendar.event.BasicEvent;
import com.vaadin.ui.components.calendar.event.CalendarEvent;

/**
 * @author tim
 * 
 */
public class DetailEvents extends CustomComponent implements DetailEventsSpec {

	private final Calendar calendar = new Calendar();
	private final GregorianCalendar gc = new GregorianCalendar();

	public DetailEvents() {
		setCompositionRoot(calendar);
	}

	@Override
	public void setEvents(List<EventModel> events) {
		calendar.setStartDate(new Date());
		calendar.setEndDate(new Date());

		int firstHourOfDay = 24;
		int lastHourOfDay = 0;

		BasicEvent calendarEvent;
		for (EventModel event : events) {
			// TODO: Adapt do 'real' event data
			// calendarEvent = new BasicEvent();
			// calendarEvent.setCaption(event.getName());
			// calendarEvent.setDescription("Description of event");
			// GregorianCalendar gc = new GregorianCalendar();
			// calendarEvent.setStart(new Date());
			// gc.add(java.util.Calendar.HOUR_OF_DAY, 2);
			// calendarEvent.setEnd(gc.getTime());
			//
			// calendar.addEvent(calendarEvent);
			//
			// int startHour = getHourOfDay(calendarEvent.getStart());
			// if (startHour < firstHourOfDay) {
			// firstHourOfDay = startHour;
			// }
			// int endHour = getHourOfDay(calendarEvent.getEnd());
			// if (endHour > lastHourOfDay) {
			// lastHourOfDay = endHour;
			// }

		}

		calendar.setFirstVisibleHourOfDay(firstHourOfDay - 1);
		calendar.setLastVisibleHourOfDay(lastHourOfDay + 1);

	}

	private int getHourOfDay(Date date) {
		gc.setTime(date);
		return gc.get(java.util.Calendar.HOUR_OF_DAY);
	}

	@Override
	public void removeEvents() {
		for (CalendarEvent event : calendar.getEvents(new Date(), new Date())) {
			calendar.removeEvent(event);
		}
	}

	@Override
	public void updateTranslations() {

	}

}
