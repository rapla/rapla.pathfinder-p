package com.pathfinder.view.components;

import com.pathfinder.view.listener.MenuBarViewListenerSpec;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;

/**
 * MenuBarSpec
 * 
 * @author alexh
 * 
 */
public interface MenuBarSpec extends ComponentSpec {
	void addMenuBarListener(MenuBarViewListenerSpec listener);

	void addClickListenerGermanButton(ClickListener listener);

	void addClickListenerEnglishButton(ClickListener listener);

	Button getGermanButton();

	Button getEnglishButton();
}
