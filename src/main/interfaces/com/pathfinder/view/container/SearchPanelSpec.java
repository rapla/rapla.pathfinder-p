package com.pathfinder.view.container;

import com.pathfinder.view.ViewSpec;
import com.pathfinder.view.listener.SearchPanelViewListenerSpec;

/**
 * SearchPanelSpec
 * 
 * @author alexh
 * 
 */
public interface SearchPanelSpec extends ViewSpec {
	void addSearchPanelViewListener(SearchPanelViewListenerSpec listener);
}
