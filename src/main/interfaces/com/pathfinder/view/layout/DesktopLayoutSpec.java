package com.pathfinder.view.layout;

import com.pathfinder.view.ViewSpec;
import com.pathfinder.view.listener.MainLayoutViewListenerSpec;

/**
 * MainLayoutSpec
 * 
 * @author alexh
 * 
 */
public interface DesktopLayoutSpec extends ViewSpec {
	void switchToDetailView();

	void switchToSearchView();

	void addMainLayoutViewListener(MainLayoutViewListenerSpec listener);
}
