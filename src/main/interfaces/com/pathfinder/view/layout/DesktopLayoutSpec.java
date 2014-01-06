package com.pathfinder.view.layout;

import com.pathfinder.view.ViewSpec;
import com.pathfinder.view.components.FreeRoomViewSpec;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button.ClickListener;

import de.vksi.c4j.ContractReference;
import de.vksi.c4j.Pure;

/**
 * MainLayoutSpec
 * 
 * @author alexh
 * 
 */
@ContractReference(DesktopLayoutSpecContract.class)
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

	@Pure
	FreeRoomViewSpec getFreeRoom();
}
