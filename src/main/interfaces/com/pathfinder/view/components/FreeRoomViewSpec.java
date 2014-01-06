package com.pathfinder.view.components;

import java.util.List;

import de.vksi.c4j.ContractReference;

/**
 * FreeRoomSpec
 * 
 * @author alexh
 * 
 */
@ContractReference(FreeRoomViewSpecContract.class)
public interface FreeRoomViewSpec extends ComponentSpec {
	void refreshFreeRooms(List<String> raumNameList, List<String> raumLinkList,
			List<String> raumIdList, List<String> startList,
			List<String> endList);

	void hideFreeRoom();

	void showFreeRoom();
}
