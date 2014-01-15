package com.pathfinder.view.components;

import java.util.Locale;

import com.vaadin.ui.Button.ClickListener;

import de.vksi.c4j.ContractReference;

/**
 * MenuBarSpec
 * 
 * @author alexh
 * 
 */
@ContractReference(MenuBarSpecContract.class)
public interface MenuBarSpec extends ComponentSpec {

	void addClickListenerHomeButton(ClickListener listener);

	void addClickListenerWheelChairButton(ClickListener listener);

	void addClickListenerFlagPopup(Locale locale,
			com.vaadin.event.MouseEvents.ClickListener listener);

	void showWheelChairButton();

	void hideWheelChairButton();

	void showHomeButton();

	void hideHomeButton();

	void hideOpenLanguagePopup();

	void replaceWheelChairButtonWithHomeButton();

	void replaceHomeButtonWithWheelChairButton();
}
