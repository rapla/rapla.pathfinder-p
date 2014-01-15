package com.pathfinder.view.components;

import java.util.Date;
import java.util.GregorianCalendar;

import com.pathfinder.model.EventModel;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.CustomComponent;
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
	public void setEvents(BeanItemContainer<EventModel> events) {
		calendar.setStartDate(new Date());
		calendar.setEndDate(new Date());

		int firstHourOfDay = 24;
		int lastHourOfDay = 0;

		for (EventModel event : events.getItemIds()) {

			int startHour = getHourOfDay(event.getStart());
			int endHour = getHourOfDay(event.getEnd());
			if (startHour == -1 || endHour == -1)
				break;
			if (startHour < firstHourOfDay) {
				firstHourOfDay = startHour;
			}
			if (endHour > lastHourOfDay) {
				lastHourOfDay = endHour;
			}

			calendar.addEvent(event);
		}

		calendar.setFirstVisibleHourOfDay(firstHourOfDay - 1);
		calendar.setLastVisibleHourOfDay(lastHourOfDay + 1);

	}

	private int getHourOfDay(Date date) {
		int result = -1;
		if (date != null) {
			gc.setTime(date);
			result = gc.get(java.util.Calendar.HOUR_OF_DAY);
		}
		return result;
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
