package com.pathfinder.view.layout;

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
