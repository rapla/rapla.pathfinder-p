package com.pathfinder.view.components;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;

/**
 * MenuBarSpec
 * 
 * @author alexh
 * 
 */
public interface MenuBarSpec extends ComponentSpec {
	void addClickListenerGermanButton(ClickListener listener);

	void addClickListenerEnglishButton(ClickListener listener);

	void addClickListenerAppointmentButton(ClickListener listener);

	void showAppointmentButton();

	void hideAppointmentButton();

	Button getGermanButton();

	Button getEnglishButton();
}
