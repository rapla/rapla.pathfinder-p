package com.pathfinder.view.components;

import java.util.List;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author tim
 * 
 */
public class DetailEvents extends CustomComponent implements DetailEventsSpec {

	private final VerticalLayout layout = new VerticalLayout();

	public DetailEvents() {
		setCompositionRoot(layout);
	}

	@Override
	public void setEvents(List<com.pathfinder.model.Event> events) {
		for (com.pathfinder.model.Event event : events) {
			layout.addComponent(new Label(event.getName()));
		}
	}

	@Override
	public void removeEvents() {
		layout.removeAllComponents();
	}

	@Override
	public void updateTranslations() {

	}

}
