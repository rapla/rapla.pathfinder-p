package com.pathfinder.presenter;

import com.pathfinder.view.components.FreeRoom;
import com.pathfinder.view.components.TimeDate;
import com.pathfinder.view.layout.InfoPanel;

public class InfoPanelPresenter {
	private final TimeDate timeDate = new TimeDate();
	private final FreeRoom freeRoomView = new FreeRoom();
	private final InfoPanel infoPanel = new InfoPanel(timeDate, freeRoomView);
	
	
	
	public InfoPanel getInfoPanel()
	{
		return this.infoPanel;
	}
}
