package com.pathfinder.view.components;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.pathfinder.model.EventModel;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.components.calendar.event.CalendarEvent;

/**
 * @author tim
 * 
 */
public class DetailEvents extends CustomComponent implements DetailEventsSpec {
	private final HorizontalLayout layout = new HorizontalLayout();
	private final Calendar calendar = new Calendar();
	private final GregorianCalendar gc = new GregorianCalendar();
	private final List<CalendarEventComponent> calendarEvents = new ArrayList<>();

	public DetailEvents() {
		this.buildLayout();
		this.setCompositionRoot(layout);
	}

	private void buildLayout() {
		layout.addComponent(calendar);
	}

	@Override
	public void setEvents(BeanItemContainer<EventModel> events,
			Date calendarStartDate) {
		removeEvents();

		int firstHourOfDay = 24;
		int lastHourOfDay = 0;

		calendar.setStartDate(calendarStartDate);
		calendar.setEndDate(calendarStartDate);

		if (events.getItemIds().size() == 0) {
			firstHourOfDay = 10;
			lastHourOfDay = 18;
		}

		CalendarEventComponent calendarEvent;

		for (EventModel event : events.getItemIds()) {

			calendarEvent = new CalendarEventComponent(event);

			int startHour = getHourOfDay(calendarEvent.getStart(),
					calendarStartDate);
			int endHour = getHourOfDay(calendarEvent.getEnd(),
					calendarStartDate);
			if (startHour == -1 || endHour == -1)
				break;
			if (startHour < firstHourOfDay) {
				firstHourOfDay = startHour;
			}
			if (endHour > lastHourOfDay) {
				lastHourOfDay = endHour;
			}

			calendar.addEvent(calendarEvent);
			calendarEvents.add(calendarEvent);
		}

		calendar.setFirstVisibleHourOfDay(firstHourOfDay - 1);
		calendar.setLastVisibleHourOfDay(lastHourOfDay);

	}

	private int getHourOfDay(Date dateToGetHourFrom, Date currentDateOfCalendar) {
		int result = -1;
		if (dateToGetHourFrom != null && currentDateOfCalendar != null) {
			gc.setTime(currentDateOfCalendar);
			gc.set(java.util.Calendar.HOUR, 23);
			gc.set(java.util.Calendar.MINUTE, 59);
			if (gc.getTime().before(dateToGetHourFrom)) {
				result = 23;
			} else {
				gc.set(java.util.Calendar.HOUR, 0);
				gc.set(java.util.Calendar.MINUTE, 0);
				if (gc.getTime().after(dateToGetHourFrom)) {
					result = 0;
				} else {
					gc.setTime(dateToGetHourFrom);
					result = gc.get(java.util.Calendar.HOUR_OF_DAY);
				}
			}
		}
		return result;
	}

	@Override
	public void removeEvents() {
		for (CalendarEvent event : calendarEvents) {
			calendar.removeEvent(event);
		}
		calendarEvents.clear();
	}

	@Override
	public void updateTranslations() {
		calendar.setLocale(UI.getCurrent().getLocale());
	}

	@Override
	public void addCalendarListener(Listener listener) {
		calendar.addListener(listener);
	}

}