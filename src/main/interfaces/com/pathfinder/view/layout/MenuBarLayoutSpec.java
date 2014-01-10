/**
 * 
 */
package com.pathfinder.view.layout;

import java.util.Locale;

import com.pathfinder.view.components.MenuBarSpec;
import com.vaadin.ui.Button.ClickListener;

/**
 * Contains all the methods which the {@link DesktopLayoutSpec} shares with
 * {@link MenuBarSpec}
 * 
 * @author tim
 * 
 */
public interface MenuBarLayoutSpec {

	void addClickListenerHomeButton(ClickListener listener);

	void addClickListenerAppointmentButton(ClickListener listener);

	void addClickListenerWheelChairButton(ClickListener listener);

	void addClickListenerBackButton(ClickListener listener);

	void addClickListenerFlagPopup(Locale locale,
			com.vaadin.event.MouseEvents.ClickListener listener);

	void showAppointmentButton();

	void hideAppointmentButton();

	void hideOpenLanguagePopup();
}
