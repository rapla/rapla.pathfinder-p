/**
 * 
 */
package com.pathfinder.view.components;

import java.util.Locale;

import com.vaadin.ui.Button.ClickListener;

import de.vksi.c4j.Target;

/**
 * @author tim
 * 
 */
public class MenuBarSpecContract extends ComponentSpecContract implements
		MenuBarSpec {

	@Target
	private MenuBarSpec target;
	int listenerCountBefore = -5;

	@Override
	public void addClickListenerWheelChairButton(ClickListener listener) {

	}

	@Override
	public void addClickListenerHomeButton(ClickListener listener) {
	}

	@Override
	public void showHomeButton() {
	}

	@Override
	public void hideHomeButton() {
	}

	@Override
	public void showWheelChairButton() {
	}

	@Override
	public void hideWheelChairButton() {
	}

	@Override
	public void replaceWheelChairButtonWithHomeButton() {
	}

	@Override
	public void replaceHomeButtonWithWheelChairButton() {
	}

	@Override
	public void addClickListenerFlagPopup(Locale locale,
			com.vaadin.event.MouseEvents.ClickListener listener) {
	}

	@Override
	public void hideOpenLanguagePopup() {
	}
}