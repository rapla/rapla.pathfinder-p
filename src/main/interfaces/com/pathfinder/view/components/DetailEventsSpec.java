/**
 * 
 */
package com.pathfinder.view.components;

import com.pathfinder.model.EventModel;
import com.vaadin.data.util.BeanItemContainer;

/**
 * @author tim
 * 
 */
public interface DetailEventsSpec extends ComponentSpec {
	void setEvents(BeanItemContainer<EventModel> events);

	void removeEvents();
}
