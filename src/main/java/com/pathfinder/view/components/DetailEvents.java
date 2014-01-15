package com.pathfinder.view.components;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.pathfinder.model.EventModel;
import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.components.calendar.event.CalendarEvent;

/**
 * @author tim
 * 
 */
public class DetailEvents extends CustomComponent implements DetailEventsSpec {
	private final HorizontalLayout layout = new HorizontalLayout();
	private final Calendar calendar = new Calendar();
	private final GregorianCalendar gc = new GregorianCalendar();
	private final Label noEventsLabel = new Label();
	private final TranslatorSpec translator = Translator.getInstance();
	private final List<EventModel> calendarEvents = new ArrayList<>();

	public DetailEvents() {
		this.buildLayout();
		this.setCompositionRoot(layout);
	}

	private void buildLayout() {
		noEventsLabel.setCaption(translator
				.translate(TranslationKeys.NO_EVENTS_AVAILABLE));
		layout.addComponent(noEventsLabel);
		layout.addComponent(calendar);
	}

	@Override
	public void setEvents(BeanItemContainer<EventModel> events) {
		removeEvents();

		calendar.setStartDate(new Date());
		calendar.setEndDate(new Date());

		int firstHourOfDay = 24;
		int lastHourOfDay = 0;

		if (events.getItemIds().size() == 0) {
			noEventsLabel.setVisible(true);
			calendar.setVisible(false);
		} else {
			noEventsLabel.setVisible(false);
			calendar.setVisible(true);
			calendarEvents.clear();
			calendarEvents.addAll(events.getItemIds());
		}

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
		for (CalendarEvent event : calendarEvents) {
			calendar.removeEvent(event);
		}
	}

	@Override
	public void updateTranslations() {
		noEventsLabel.setCaption(translator
				.translate(TranslationKeys.NO_EVENTS_AVAILABLE));
	}
}