package com.pathfinder.view;

import com.pathfinder.model.EventModelSpec;
import com.vaadin.ui.components.calendar.event.CalendarEvent;

import de.vksi.c4j.ContractReference;
import de.vksi.c4j.Pure;

@ContractReference(CalendarEventComponentSpecContract.class)
public interface CalendarEventComponentSpec extends CalendarEvent {
	@Pure
	EventModelSpec getEventModel();
}
