/**
 * 
 */
package com.pathfinder.view.components;

import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;

import java.util.List;

import com.vaadin.ui.Label;

import de.vksi.c4j.ClassInvariant;
import de.vksi.c4j.Condition;
import de.vksi.c4j.Target;

/**
 * @author tim
 * 
 */
public class FreeRoomViewSpecContract extends ComponentSpecContract implements
		FreeRoomViewSpec {

	@Target
	private FreeRoomViewSpec target;

	@ClassInvariant
	public void classInvariant() {
		// TODO: write invariants if required
	}

	@Override
	public void refreshFreeRooms(List<String> raumNameList,
			List<String> raumLinkList, List<String> raumIdList,
			List<String> startList, List<String> endList) {
		if (preCondition()) {
			assert raumNameList != null : "RaumNameList not null";
			assert raumLinkList != null : "RaumLinkList not null";
			assert raumIdList != null : "RaumIdList not null";
			assert startList != null : "StartList not null";
			assert endList != null : "EndList not null";
		}
	}

	@Override
	public void hideFreeRoom() {
		if (postCondition()) {
			assert !target.isVisible() : "FreeRoomView is not visible";
		}
	}

	@Override
	public void showFreeRoom() {
		if (postCondition()) {
			assert target.isVisible() : "FreeRoomView is visible";
		}
	}

	@Override
	public Label getFreeRoomLabel() {
		if (postCondition()) {
			Label label = Condition.result();
			assert label != null : "Result not null";
		}
		return Condition.ignored();
	}

}
