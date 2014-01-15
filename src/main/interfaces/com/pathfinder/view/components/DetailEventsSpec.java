/**
 * 
 */
package com.pathfinder.view.components;

import java.util.List;

import com.pathfinder.model.EventModel;

/**
 * @author tim
 * 
 */
public interface DetailEventsSpec extends ComponentSpec {
	void setEvents(List<EventModel> events);

	void removeEvents();
}
