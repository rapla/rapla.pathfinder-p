package com.pathfinder.view.layout;

import com.pathfinder.model.FreeRoomModel;
import com.pathfinder.model.ResourceModel;
import com.pathfinder.view.ViewSpec;
import com.pathfinder.view.listener.KeyboardViewListenerSpec;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextField;

/**
 * MainLayoutSpec
 * 
 * @author alexh
 * 
 */
public interface DesktopLayoutSpec extends ViewSpec, MenuBarLayoutSpec {

	void addKeyboardListener(KeyboardViewListenerSpec listener);

	void addItemClickListenerRoomTable(ItemClickListener listener);

	void addItemClickListenerCourseTable(ItemClickListener listener);

	void addItemClickListenerPersonTable(ItemClickListener listener);

	void addItemClickListenerPoiTable(ItemClickListener listener);

	void addClickListenerHomeButton(ClickListener listener);

	void addClickListenerAppointmentButton(ClickListener listener);

	void addClickListenerWheelChairButton(ClickListener listener);

	void addClickListenerBackButton(ClickListener listener);

	void addDeleteAllClickListener(ClickListener listener);

	void setRoomContainer(BeanItemContainer<ResourceModel> beanItemContainer);

	void setCourseContainer(BeanItemContainer<ResourceModel> beanItemContainer);

	void setPersonContainer(BeanItemContainer<ResourceModel> beanItemContainer);

	void setPoiContainer(BeanItemContainer<ResourceModel> beanItemContainer);

	void useFiltersForAllTables(String searchString);

	void hideAppointmentButton();

	void showAppointmentButton();

	void switchToSearchView();

	void switchToDetailView(ResourceModel resource);

	void switchToAppointmentView();

	void setAppointmentUrl(String url);

	void refreshFreeRooms(BeanItemContainer<FreeRoomModel> freeRoomContainer);
	
	void focusSearchField();
	
	int getCursorPosition();

	void setCursorPosition(int cursorPosition);

	TextField getSearchField();
}