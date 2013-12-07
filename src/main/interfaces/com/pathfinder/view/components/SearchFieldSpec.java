package com.pathfinder.view.components;

import com.pathfinder.view.listener.SearchFieldViewListenerSpec;

/**
 * SearchFieldSpec
 * 
 * @author alexh
 * 
 */
public interface SearchFieldSpec extends ComponentSpec {
	void addSearchFieldListener(SearchFieldViewListenerSpec listener);
}
