/**
 * 
 */
package com.pathfinder.view;

import java.util.Date;

import com.pathfinder.model.EventModel;
import com.vaadin.data.util.BeanItemContainer;

/**
 * @author tim
 * 
 */
public interface DetailEventsSpec extends ComponentSpec {
	void setEvents(BeanItemContainer<EventModel> events, Date calendarStartDate, Date calendarEndDate);

	void removeEvents();

	void addCalendarListener(Listener listener);
}
