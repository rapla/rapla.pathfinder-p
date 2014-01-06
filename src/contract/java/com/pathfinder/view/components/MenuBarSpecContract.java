/**
 * 
 */
package com.pathfinder.view.components;

import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;

import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Field.ValueChangeEvent;
import com.vaadin.ui.NativeSelect;

import de.vksi.c4j.Condition;
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
	public void addValueChangeListener(ValueChangeListener listener) {
		if (preCondition()) {
			assert listener != null : "Listener not null";
			assert target.getDropUpMenu() != null : "DropUpMenu not null";
			listenerCountBefore = target.getDropUpMenu()
					.getListeners(ValueChangeEvent.class).size();
		}
		if (postCondition()) {
			int listenerCountAfter = target.getDropUpMenu()
					.getListeners(ValueChangeEvent.class).size();
			assert listenerCountAfter == listenerCountBefore + 1 : "DropUpMenu has one more listener";
			listenerCountBefore = -5;
		}
	}

	@Override
	public void addClickListenerAppointmentButton(ClickListener listener) {
		if (preCondition()) {
			assert listener != null : "Listener not null";
			assert target.getDropUpMenu() != null : "AppointmentButton not null";
			listenerCountBefore = target.getAppointmentButton()
					.getListeners(ClickEvent.class).size();
		}
		if (postCondition()) {
			int listenerCountAfter = target.getAppointmentButton()
					.getListeners(ClickEvent.class).size();
			assert listenerCountAfter == listenerCountBefore + 1 : "AppointmentButton has one more listener";
			listenerCountBefore = -5;
		}
	}

	@Override
	public void showAppointmentButton() {
		if (postCondition()) {
			assert target.getAppointmentButton().isVisible() : "AppointmentButton is visible";
		}
	}

	@Override
	public void hideAppointmentButton() {
		if (postCondition()) {
			assert !target.getAppointmentButton().isVisible() : "AppointmentButton is not visible";
		}
	}

	@Override
	public NativeSelect getDropUpMenu() {
		if (postCondition()) {
			NativeSelect nativeSelect = Condition.result();
			assert nativeSelect != null : "Result not null";
		}
		return Condition.ignored();
	}

	@Override
	public Button getAppointmentButton() {
		if (postCondition()) {
			Button button = Condition.result();
			assert button != null : "Result not null";
		}
		return Condition.ignored();
	}

}
