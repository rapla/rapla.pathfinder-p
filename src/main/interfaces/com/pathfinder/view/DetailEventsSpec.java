/**
 * 
 */
package com.pathfinder.view;

import com.pathfinder.model.ResourceModel;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.EventClickHandler;

/**
 * @author tim
 * 
 */
public interface DetailEventsSpec extends ComponentSpec {
	void setEventClickHandler(EventClickHandler eventClickHandler);

	void setResourceModel(ResourceModel resourceModel);
}
