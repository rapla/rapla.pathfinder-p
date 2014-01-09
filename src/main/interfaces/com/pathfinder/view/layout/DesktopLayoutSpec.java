package com.pathfinder.view.layout;

import com.pathfinder.model.FreeRoomModel;
import com.pathfinder.view.ViewSpec;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button.ClickListener;

/**
 * MainLayoutSpec
 * 
 * @author alexh
 * 
 */
public interface DesktopLayoutSpec extends ViewSpec {
	void addLanguageValueChangeListener(ValueChangeListener listener);

	void addClickListenerAppointmentButton(ClickListener listener);

	void addClickListenerWheelChairButton(ClickListener listener);

	void addClickListenerBackButton(ClickListener listener);

	void hideAppointmentButton();

	void showAppointmentButton();

	void switchToSearchView();

	<T> void switchToDetailView();

	void switchToAppointmentView();

	void setAppointmentUrl(String url);

	void refreshFreeRooms(BeanItemContainer<FreeRoomModel> freeRoomContainer);
}
