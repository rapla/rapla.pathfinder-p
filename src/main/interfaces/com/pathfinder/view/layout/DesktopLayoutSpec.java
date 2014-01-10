package com.pathfinder.view.layout;

import com.pathfinder.model.FreeRoomModel;
import com.pathfinder.model.ResourceModel;
import com.pathfinder.view.ViewSpec;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button.ClickListener;

/**
 * MainLayoutSpec
 * 
 * @author alexh
 * 
 */
public interface DesktopLayoutSpec extends ViewSpec, MenuBarLayoutSpec {

	void addClickListenerHomeButton(ClickListener listener);

	void addClickListenerAppointmentButton(ClickListener listener);

	void addClickListenerWheelChairButton(ClickListener listener);

	void addClickListenerBackButton(ClickListener listener);

	void hideAppointmentButton();

	void showAppointmentButton();

	void switchToSearchView();

	void switchToDetailView(ResourceModel resource);

	void switchToAppointmentView();

	void setAppointmentUrl(String url);

	void refreshFreeRooms(BeanItemContainer<FreeRoomModel> freeRoomContainer);
}