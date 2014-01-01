package com.pathfinder.view.components;

import com.vaadin.ui.Button.ClickListener;

/**
 * MenuBarSpec
 * 
 * @author alexh
 * 
 */
public interface MenuBarSpec extends ComponentSpec {
	void addClickListenerAppointmentButton(ClickListener listener);

	void showAppointmentButton();

	void hideAppointmentButton();


}
