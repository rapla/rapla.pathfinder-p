package com.pathfinder.presenter;

import de.vksi.c4j.ContractReference;
import de.vksi.c4j.Pure;

@ContractReference(SearchPanelPresenterSpecContract.class)
public interface SearchPanelPresenterSpec {

	void addKeybordKeyToSearchString(String key);

	void deleteKeyFromSearchString();

	void clearSearchString();

	@Pure
	String getSearchString();

}