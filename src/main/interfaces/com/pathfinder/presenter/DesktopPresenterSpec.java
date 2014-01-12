package com.pathfinder.presenter;

import com.pathfinder.model.ResourceModel;
import com.pathfinder.view.layout.DesktopLayoutSpec;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.MouseEvents.ClickListener;

import de.vksi.c4j.ContractReference;
import de.vksi.c4j.Pure;

@ContractReference(DesktopPresenterSpecContract.class)
public interface DesktopPresenterSpec {

	void switchToSearchView();

	void switchToDetailView(ResourceModel resource);

	void switchToAppointmentView();
	
	void changeWheelChairView();

	void setRoomContainer(BeanItemContainer<ResourceModel> beanItemContainer);

	void setCourseContainer(BeanItemContainer<ResourceModel> beanItemContainer);

	void setPersonContainer(BeanItemContainer<ResourceModel> beanItemContainer);

	void setPoiContainer(BeanItemContainer<ResourceModel> beanItemContainer);

	void refreshFreeRooms();

	DesktopLayoutSpec getDesktopLayoutView();

	void addKeybordKeyToSearchString(String key);

	void deleteKeyFromSearchString();

	void clearSearchString();

	void setSearchString(String value);

	@Pure
	String getSearchString();

	@Pure
	int getCursorPosition();

	@Pure
	ClickListener getUiClickListener();
}
