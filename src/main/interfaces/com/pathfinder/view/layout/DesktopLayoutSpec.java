package com.pathfinder.view.layout;

import com.pathfinder.view.ViewSpec;

/**
 * MainLayoutSpec
 * 
 * @author alexh
 * 
 */
public interface DesktopLayoutSpec extends ViewSpec {
	<T> void switchToDetailView();

	void switchToSearchView();
}
