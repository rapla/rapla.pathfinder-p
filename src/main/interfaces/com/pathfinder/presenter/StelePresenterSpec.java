package com.pathfinder.presenter;

import java.util.Locale;

import com.pathfinder.model.ResourceModel;
import com.pathfinder.view.layout.SteleLayoutSpec;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component.Listener;

import de.vksi.c4j.ContractReference;
import de.vksi.c4j.Pure;

@ContractReference(StelePresenterSpecContract.class)
public interface StelePresenterSpec {

	void switchToSearchView();

	void switchToDetailView();

	void changeToWheelChairView();

	void changeToNonWheelChairView();

	void setRoomContainer(BeanItemContainer<ResourceModel> beanItemContainer);

	void setCourseContainer(BeanItemContainer<ResourceModel> beanItemContainer);

	void setPersonContainer(BeanItemContainer<ResourceModel> beanItemContainer);

	void setPoiContainer(BeanItemContainer<ResourceModel> beanItemContainer);

	void refreshFreeRooms();

	SteleLayoutSpec getSteleLayoutView();

	void addKeybordKeyToSearchString(String key);

	void deleteKeyFromSearchString();

	void clearSearchString();

	void setSearchString(String value);

	void languageChanged(Locale locale);

	@Pure
	String getSearchString();

	@Pure
	int getCursorPosition();

	@Pure
	Listener getUiListener();
}
