package com.pathfinder.presenter;

import com.pathfinder.view.components.FreeRoom;
import com.pathfinder.view.container.InfoPanel;
import com.pathfinder.widgetset.DateTime;

public class InfoPanelPresenter implements InfoPanelPresenterSpec {
	private final DateTime dateTime = new DateTime();
	private final FreeRoom freeRoomView = new FreeRoom();
	private final InfoPanel infoPanel = new InfoPanel(dateTime, freeRoomView);

	public InfoPanel getInfoPanel() {
		return this.infoPanel;
	}
}
