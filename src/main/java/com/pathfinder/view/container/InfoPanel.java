package com.pathfinder.view.container;

import com.pathfinder.util.widgetset.DateTime;
import com.pathfinder.view.components.FreeRoom;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;

public class InfoPanel extends CustomComponent implements InfoPanelSpec {
	private DateTime dateTime = null;
	private FreeRoom freeRoom = null;

	private final HorizontalLayout horizontalLayout = new HorizontalLayout();

	public InfoPanel(DateTime dateTime, FreeRoom freeRoom) {
		this.dateTime = dateTime;
		this.freeRoom = freeRoom;

		this.buildLayout();
		this.setCompositionRoot(horizontalLayout);
	}

	@Override
	public void buildLayout() {
		horizontalLayout.addComponent(dateTime);
		horizontalLayout.addComponent(freeRoom);
		horizontalLayout.setExpandRatio(dateTime, 0.5f);
		horizontalLayout.setExpandRatio(freeRoom, 0.5f);
		horizontalLayout.setSizeFull();
	}

	@Override
	public void destroyLayout() {
		horizontalLayout.removeAllComponents();
	}

	@Override
	public void updateTranslations() {
		dateTime.updateTranslations();
		freeRoom.updateTranslations();
	}
}