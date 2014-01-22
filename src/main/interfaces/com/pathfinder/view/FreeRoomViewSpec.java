package com.pathfinder.view;

import com.pathfinder.model.FreeRoomModel;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent.ItemClickListener;

/**
 * FreeRoomSpec
 * 
 * @author alexh
 * 
 */
public interface FreeRoomViewSpec extends ComponentSpec {
	void addTableItemClickListener(ItemClickListener listener);

	void refreshFreeRooms(BeanItemContainer<FreeRoomModel> freeRoomContainer);

	void hideFreeRoomView();

	void showFreeRoomView();
}
