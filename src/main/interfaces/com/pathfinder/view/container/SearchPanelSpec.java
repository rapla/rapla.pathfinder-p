package com.pathfinder.view.container;

import com.pathfinder.view.ViewSpec;

import de.vksi.c4j.ContractReference;

/**
 * SearchPanelSpec
 * 
 * @author alexh
 * 
 */
@ContractReference(SearchPanelSpecContract.class)
public interface SearchPanelSpec extends ViewSpec {
	void hideSearchPanel();

	void showSearchPanel();
}
