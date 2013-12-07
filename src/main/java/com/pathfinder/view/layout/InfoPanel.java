package com.pathfinder.view.layout;

import com.pathfinder.view.components.FreeRoom;
import com.pathfinder.view.components.TimeDate;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;

public class InfoPanel extends CustomComponent implements InfoPanelSpec {
	private final TimeDate timeDate = new TimeDate();
	private final FreeRoom freeRoom = new FreeRoom();

	private final HorizontalLayout horizontalLayout = new HorizontalLayout();

	public InfoPanel() {
		this.buildLayout();
		this.setCompositionRoot(horizontalLayout);
	}

	@Override
	public void buildLayout() {
		horizontalLayout.addComponent(timeDate);
		horizontalLayout.addComponent(freeRoom);
		horizontalLayout.setExpandRatio(timeDate, 0.5f);
		horizontalLayout.setExpandRatio(freeRoom, 0.5f);
		horizontalLayout.setSizeFull();
	}

	@Override
	public void destroyLayout() {
		horizontalLayout.removeAllComponents();
	}
}
