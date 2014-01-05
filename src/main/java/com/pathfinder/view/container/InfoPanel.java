package com.pathfinder.view.container;

import com.pathfinder.util.widgetset.DateTime;
import com.pathfinder.view.components.FreeRoom;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

public class InfoPanel extends CustomComponent implements InfoPanelSpec {
	private DateTime dateTime = null;
	private FreeRoom freeRoom = null;

	private final VerticalLayout verticalLayout = new VerticalLayout();

	public InfoPanel(DateTime dateTime, FreeRoom freeRoom) {
		this.dateTime = dateTime;
		this.freeRoom = freeRoom;

		this.buildLayout();
		this.setCompositionRoot(verticalLayout);
	}

	@Override
	public void buildLayout() {
		verticalLayout.addComponent(dateTime);
		verticalLayout.addComponent(freeRoom);
		verticalLayout.setSizeFull();
	}

	@Override
	public void destroyLayout() {
		verticalLayout.removeAllComponents();
	}

	@Override
	public void hideDateTime() {
		dateTime.setVisible(false);
	}

	@Override
	public void showDateTime() {
		dateTime.setVisible(true);
	}

	@Override
	public void hideFreeRoom() {
		freeRoom.setVisible(false);
	}

	@Override
	public void showFreeRoom() {
		freeRoom.setVisible(true);
	}

	@Override
	public void hideInfoPanel() {
		this.setVisible(false);
	}

	@Override
	public void showInfoPanel() {
		this.setVisible(true);
	}

	@Override
	public void updateTranslations() {
		dateTime.updateTranslations();
		freeRoom.updateTranslations();
	}
}