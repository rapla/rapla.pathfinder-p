package com.pathfinder.view;

import com.pathfinder.model.ResourceModel;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.UI;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.DateClickEvent;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.DateClickHandler;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.EventClickHandler;

/**
 * @author tim
 * 
 */
public class DetailEvents extends CustomComponent implements DetailEventsSpec {
	private Calendar calendar;
	private DetailEventProvider provider;
	private final BeanItemContainer<CalendarEventComponent> calendarEvents = new BeanItemContainer<>(
			CalendarEventComponent.class);

	public DetailEvents() {
		this.buildLayout();
		this.addStyling();
		this.initDummyHandlers();
		this.setCompositionRoot(calendar);
	}

	private void buildLayout() {
		this.setSizeFull();

		provider = new DetailEventProvider(calendarEvents);
		calendar = new Calendar(provider);
		calendar.setSizeFull();
		calendar.setWidth(100, Unit.PERCENTAGE);
		calendar.setHeight(100, Unit.PERCENTAGE);
		calendar.setFirstVisibleHourOfDay(8);
		calendar.setLastVisibleHourOfDay(20);
	}

	private void addStyling() {
		this.addStyleName("detailEvents");
	}

	private void initDummyHandlers() {
		calendar.setHandler(new DateClickHandler() {
			@Override
			public void dateClick(DateClickEvent event) {
			}
		});
	}

	@Override
	public void setEventClickHandler(EventClickHandler eventClickHandler) {
		calendar.setHandler(eventClickHandler);
	}

	@Override
	public void updateTranslations() {
		calendar.setLocale(UI.getCurrent().getLocale());
	}

	@Override
	public void doCleanup() {
	}

	@Override
	public void setResourceModel(ResourceModel resourceModel) {
		provider.setResourceModel(resourceModel);
	}
}