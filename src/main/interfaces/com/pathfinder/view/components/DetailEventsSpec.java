/**
 * 
 */
package com.pathfinder.view.components;

import java.util.Date;

import com.pathfinder.model.EventModel;
import com.vaadin.data.util.BeanItemContainer;

/**
 * @author tim
 * 
 */
public interface DetailEventsSpec extends ComponentSpec {
	void setEvents(BeanItemContainer<EventModel> events, Date calendarStartDate);

	void removeEvents();

	void addCalendarListener(Listener listener);
}
