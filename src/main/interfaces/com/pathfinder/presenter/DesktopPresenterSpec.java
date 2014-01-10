package com.pathfinder.presenter;

import com.pathfinder.model.ResourceModel;
import com.pathfinder.view.container.SearchPanel;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

import de.vksi.c4j.ContractReference;
import de.vksi.c4j.Pure;

@ContractReference(DesktopPresenterSpecContract.class)
public interface DesktopPresenterSpec {

	void setRoomContainer(BeanItemContainer<ResourceModel> beanItemContainer);

	void setCourseContainer(BeanItemContainer<ResourceModel> beanItemContainer);

	void setPersonContainer(BeanItemContainer<ResourceModel> beanItemContainer);

	void setPoiContainer(BeanItemContainer<ResourceModel> beanItemContainer);

	void refreshFreeRooms();

	CustomComponent getDesktopLayoutView();

	void addKeybordKeyToSearchString(String key);

	void deleteKeyFromSearchString();

	void clearSearchString();

	void setSearchString(String value);

	@Pure
	String getSearchString();

	@Pure
	SearchPanel getSearchPanel();

	@Pure
	int getCursorPosition();
}
