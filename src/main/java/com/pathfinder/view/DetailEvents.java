package com.pathfinder.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pathfinder.model.EventModel;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.UI;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.DateClickEvent;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.DateClickHandler;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.EventClickHandler;
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
		this.addStyling();
		this.initDummyHandlers();
		this.setCompositionRoot(calendar);
	}

	private void buildLayout() {
		this.setSizeFull();
		calendar.setSizeFull();
		calendar.setWidth(100, Unit.PERCENTAGE);
		calendar.setHeight(100, Unit.PERCENTAGE);
	}

	private void addStyling() {
		this.addStyleName("detailEvents");
	}

	private void initDummyHandlers() {
		calendar.setHandler(new DateClickHandler() {
			@Override
			public void dateClick(DateClickEvent event) {
			}
		});
	}

	@Override
	public void setEventClickHandler(EventClickHandler eventClickHandler) {
		calendar.setHandler(eventClickHandler);
	}

	@Override
	public void addCalendarListener(Listener listener) {
		calendar.addListener(listener);
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
	public void doCleanup() {
		removeEvents();
	}
}