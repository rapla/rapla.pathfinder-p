package com.pathfinder.view.components;

import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button.ClickListener;

/**
 * MenuBarSpec
 * 
 * @author alexh
 * 
 */
public interface MenuBarSpec extends ComponentSpec {

	void addValueChangeListener(ValueChangeListener listener);

	void addClickListenerAppointmentButton(ClickListener listener);

	void showAppointmentButton();

	void hideAppointmentButton();

	String[] getLanguages();
}
