package com.pathfinder.view.layout;

import com.pathfinder.view.ViewSpec;
import com.pathfinder.view.components.AccordionViewSpec;
import com.pathfinder.view.components.DateTimeSpec;
import com.pathfinder.view.components.FreeRoomViewSpec;
import com.pathfinder.view.components.KeyboardSpec;
import com.pathfinder.view.components.MenuBarSpec;
import com.pathfinder.view.components.SearchFieldSpec;

/**
 * MainLayoutSpec
 * 
 * @author alexh
 * 
 */
public interface SteleLayoutSpec extends ViewSpec, AccordionViewSpec,
		DateTimeSpec, FreeRoomViewSpec, KeyboardSpec, MenuBarSpec,
		SearchFieldSpec, DetailContainerSpec {

	void changeToWheelChairView();

	void changeToNonWheelChairView();
}