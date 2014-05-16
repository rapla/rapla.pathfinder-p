package com.pathfinder.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pathfinder.model.EventModel;
import com.pathfinder.model.ResourceModel;
import com.pathfinder.presenter.DataLoader;
import com.vaadin.data.Container.Indexed;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.UI;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.EventResize;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.MoveEvent;
import com.vaadin.ui.components.calendar.ContainerEventProvider;
import com.vaadin.ui.components.calendar.event.CalendarEvent;

public class DetailEventProvider extends ContainerEventProvider {

	private DataLoader dataLoader = DataLoader.getInstance();
	private ResourceModel resourceModel;

	public DetailEventProvider(Indexed container) {
		super(container);
	}

	@Override
	public List<CalendarEvent> getEvents(Date startDate, Date endDate) {

		BeanItemContainer<EventModel> container = dataLoader.getEvent(
				resourceModel.getId(), startDate, endDate, UI.getCurrent()
						.getLocale());

		List<CalendarEvent> result = new ArrayList<>();
		if (container != null) {
			CalendarEvent calendarEvent;
			for (EventModel event : container.getItemIds()) {

				calendarEvent = new CalendarEventComponent(event);

				result.add(calendarEvent);
			}
		}

		return result;

	}

	public void setResourceModel(ResourceModel resourceModel) {
		this.resourceModel = resourceModel;
	}

	@Override
	public void eventMove(MoveEvent event) {
	}

	@Override
	public void eventResize(EventResize event) {
	}

}
