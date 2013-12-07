package com.pathfinder.view.components;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class FreeRoom extends CustomComponent implements FreeRoomSpec {
	private final Label description = new Label("Aktuelle freie Räume:");

	private final VerticalLayout verticalLayoutOuter = new VerticalLayout();
	private final VerticalLayout verticalLayoutInner = new VerticalLayout();

	public FreeRoom() {
		buildLayout();
		setCompositionRoot(verticalLayoutOuter);
	}

	@Override
	public void buildLayout() {
		verticalLayoutOuter.addComponent(description);
		verticalLayoutOuter.addComponent(verticalLayoutInner);
	}

	@Override
	public void refreshFreeRooms() {
		// TODO Auto-generated method stub - verticalLayoutInner
	}

}
