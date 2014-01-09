package com.pathfinder.presenter;

import com.pathfinder.model.ResourceModel;
import com.pathfinder.view.TranslatabelSpec;
import com.pathfinder.view.container.SearchPanel;
import com.vaadin.data.util.BeanItemContainer;

import de.vksi.c4j.ContractReference;
import de.vksi.c4j.Pure;

@ContractReference(SearchPanelPresenterContract.class)
public interface SearchPanelPresenterSpec extends TranslatabelSpec {

	void addKeybordKeyToSearchString(String key);

	void deleteKeyFromSearchString();

	void clearSearchString();

	void setRoomContainer(BeanItemContainer<ResourceModel> beanItemContainer);

	void setCourseContainer(BeanItemContainer<ResourceModel> beanItemContainer);

	void setPersonContainer(BeanItemContainer<ResourceModel> beanItemContainer);

	void setPoiContainer(BeanItemContainer<ResourceModel> beanItemContainer);

	void setSearchString(String value);

	@Pure
	String getSearchString();

	@Pure
	SearchPanel getSearchPanel();

	@Pure
	int getCursorPosition();

}