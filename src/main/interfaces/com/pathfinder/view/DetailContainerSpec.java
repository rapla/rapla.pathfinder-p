package com.pathfinder.view;

import java.util.Date;

import com.pathfinder.model.Attribut;
import com.pathfinder.model.EventModel;
import com.pathfinder.model.ResourceModel;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.EventClickHandler;

import de.vksi.c4j.ContractReference;

@ContractReference(DetailContainerSpecContract.class)
public interface DetailContainerSpec extends ComponentSpec {

	void removeDetails();

	void hideDetailContainer();

	void showDetailContainer();

	void addDetails(ResourceModel resource,
			BeanItemContainer<Attribut> resourceDetails);

	void addCalendarListener(Listener listener);

	void setEventClickHandler(EventClickHandler eventClickHandler);

	void updateCalendarEvents(BeanItemContainer<EventModel> resourceEvents,
			Date calendarStartDate, Date calendarEndDate);
}
