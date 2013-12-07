package com.pathfinder.view.layout;

/**
 * MainLayoutSpec
 * 
 * @author alexh
 *
 */
public interface MainLayoutSpec extends ViewSpec {
	void hideInfoPanel();
	void showInfoPanel();
	void hideDetailContainer();
	void showDetailContainer();
	void hideSearchPanel();
	void showSearchPanel();
}
