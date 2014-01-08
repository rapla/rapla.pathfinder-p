package com.pathfinder.presenter;

import com.pathfinder.model.CourseModel;
import com.pathfinder.model.PersonModel;
import com.pathfinder.model.PoiModel;
import com.pathfinder.model.RoomModel;
import com.pathfinder.view.container.SearchPanel;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

import de.vksi.c4j.ContractReference;
import de.vksi.c4j.Pure;

@ContractReference(DesktopPresenterSpecContract.class)
public interface DesktopPresenterSpec {

	void setRoomContainer(BeanItemContainer<RoomModel> beanItemContainer);

	void setCourseContainer(BeanItemContainer<CourseModel> beanItemContainer);

	void setPersonContainer(BeanItemContainer<PersonModel> beanItemContainer);

	void setPoiContainer(BeanItemContainer<PoiModel> beanItemContainer);

	void freeRoomHandler();

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
