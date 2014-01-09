package com.pathfinder.view.components;

import com.pathfinder.model.FreeRoomModel;
import com.vaadin.data.util.BeanItemContainer;

/**
 * FreeRoomSpec
 * 
 * @author alexh
 * 
 */
public interface FreeRoomViewSpec extends ComponentSpec {
	void refreshFreeRooms(BeanItemContainer<FreeRoomModel> freeRoomContainer);

	void hideFreeRoomView();

	void showFreeRoomView();
}
