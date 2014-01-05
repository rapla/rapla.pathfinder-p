package com.pathfinder.view.layout;

import com.pathfinder.view.ViewSpec;
import com.pathfinder.view.components.FreeRoomViewSpec;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button.ClickListener;

/**
 * MainLayoutSpec
 * 
 * @author alexh
 * 
 */
public interface DesktopLayoutSpec extends ViewSpec {
	void addClickListenerAppointmentButton(ClickListener listener);

	void addLanguageValueChangeListener(ValueChangeListener listener);

	void hideAppointmentButton();

	void showAppointmentButton();

	void switchToSearchView();

	<T> void switchToDetailView();

	void switchToAppointmentView();

	void setAppointmentUrl(String url);

	FreeRoomViewSpec getFreeRoom();
}
