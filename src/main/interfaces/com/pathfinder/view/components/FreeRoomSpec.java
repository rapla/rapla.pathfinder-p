package com.pathfinder.view.components;

import com.pathfinder.view.listener.FreeRoomViewListenerSpec;

/**
 * FreeRoomSpec
 * 
 * @author alexh
 * 
 */
public interface FreeRoomSpec extends ComponentSpec {
	void buildLayout();

	void refreshFreeRooms();

	void addFreeRoomListener(FreeRoomViewListenerSpec listener);
}
