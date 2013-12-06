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
	void hideDetail();
	void showDetail();
	void hideSearchPanel();
	void showSearchPanel();
}
