package com.pathfinder.view.layout;

import com.pathfinder.view.ViewSpec;
import com.pathfinder.view.listener.MainLayoutViewListenerSpec;

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

	void addMainLayoutViewListener(MainLayoutViewListenerSpec listener);
}
