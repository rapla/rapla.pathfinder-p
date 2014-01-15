/**
 * 
 */
package com.pathfinder.view.components;

import java.util.List;

/**
 * @author tim
 * 
 */
public interface DetailEventsSpec extends ComponentSpec {
	void setEvents(List<com.pathfinder.model.Event> events);

	void removeEvents();
}
