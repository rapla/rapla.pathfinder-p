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