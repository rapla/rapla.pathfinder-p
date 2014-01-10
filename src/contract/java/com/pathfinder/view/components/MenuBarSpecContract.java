/**
 * 
 */
package com.pathfinder.view.components;

import static de.vksi.c4j.Condition.preCondition;

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
	public void addClickListenerAppointmentButton(ClickListener listener) {
		if (preCondition()) {
			assert listener != null : "Listener not null";
		}
	}

	@Override
	public void showAppointmentButton() {
	}

	@Override
	public void hideAppointmentButton() {
	}

	@Override
	public void addClickListenerWheelChairButton(ClickListener listener) {

	}

	@Override
	public void addClickListenerBackButton(ClickListener listener) {
	}

	@Override
	public void replaceAppointmentButtonWithBackButton() {
	}

	@Override
	public void replaceBackButtonWithAppointmentButton() {
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
