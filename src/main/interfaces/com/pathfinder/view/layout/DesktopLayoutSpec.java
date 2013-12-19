package com.pathfinder.view.layout;

import com.pathfinder.view.ViewSpec;
import com.pathfinder.view.listener.DesktopLayoutViewListenerSpec;

/**
 * MainLayoutSpec
 * 
 * @author alexh
 * 
 */
public interface DesktopLayoutSpec extends ViewSpec {
	<T> void switchToDetailView();

	void switchToSearchView();

	void addMainLayoutViewListener(DesktopLayoutViewListenerSpec listener);
}
