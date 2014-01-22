package com.pathfinder.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pathfinder.model.EventModel;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.UI;
import com.vaadin.ui.components.calendar.event.CalendarEvent;

/**
 * @author tim
 * 
 */
public class DetailEvents extends CustomComponent implements DetailEventsSpec {
	private final Calendar calendar = new Calendar();
	private final List<CalendarEventComponent> calendarEvents = new ArrayList<>();

	public DetailEvents() {
		this.buildLayout();
		this.setCompositionRoot(calendar);
	}

	private void buildLayout() {
		setSizeFull();
		calendar.setSizeFull();
		calendar.setHeight(600, Unit.PIXELS);
		calendar.setReadOnly(true);
	}

	@Override
	public void setEvents(BeanItemContainer<EventModel> events,
			Date calendarStartDate, Date calendarEndDate) {
		removeEvents();

		calendar.setStartDate(calendarStartDate);
		calendar.setEndDate(calendarEndDate);

		CalendarEventComponent calendarEvent;

		for (EventModel event : events.getItemIds()) {

			calendarEvent = new CalendarEventComponent(event);

			calendar.addEvent(calendarEvent);
			calendarEvents.add(calendarEvent);
		}

		calendar.setFirstVisibleHourOfDay(8);
		calendar.setLastVisibleHourOfDay(20);

	}

	@Override
	public void removeEvents() {
		if (calendarEvents.size() != 0) {
			for (CalendarEvent event : calendarEvents) {
				calendar.removeEvent(event);
			}
			calendarEvents.clear();
		}
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