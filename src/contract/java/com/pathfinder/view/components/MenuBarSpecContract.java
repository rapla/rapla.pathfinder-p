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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pathfinder.view.components.MenuBarSpec#addClickListenerWheelChairButton
	 * (com.vaadin.ui.Button.ClickListener)
	 */
	@Override
	public void addClickListenerWheelChairButton(ClickListener listener) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pathfinder.view.components.MenuBarSpec#addClickListenerBackButton
	 * (com.vaadin.ui.Button.ClickListener)
	 */
	@Override
	public void addClickListenerBackButton(ClickListener listener) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pathfinder.view.components.MenuBarSpec#
	 * replaceAppointmentButtonWithBackButton()
	 */
	@Override
	public void replaceAppointmentButtonWithBackButton() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pathfinder.view.components.MenuBarSpec#
	 * replaceBackButtonWithAppointmentButton()
	 */
	@Override
	public void replaceBackButtonWithAppointmentButton() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addClickListenerHomeButton(ClickListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showHomeButton() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hideHomeButton() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showWheelChairButton() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hideWheelChairButton() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replaceWheelChairButtonWithHomeButton() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replaceHomeButtonWithWheelChairButton() {
		// TODO Auto-generated method stub
		
	}

}
