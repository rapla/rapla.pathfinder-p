package com.pathfinder.view.container;

import com.pathfinder.view.ViewSpec;

/**
 * InfoPanelSpec
 * 
 * @author alexh
 * 
 */
public interface InfoPanelSpec extends ViewSpec {
	void hideDateTime();

	void showDateTime();

	void hideFreeRoom();

	void showFreeRoom();

	void hideInfoPanel();

	void showInfoPanel();
}
