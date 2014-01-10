package com.pathfinder.view.components;

import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.NativeSelect;

import de.vksi.c4j.ContractReference;
import de.vksi.c4j.Pure;

/**
 * MenuBarSpec
 * 
 * @author alexh
 * 
 */
@ContractReference(MenuBarSpecContract.class)
public interface MenuBarSpec extends ComponentSpec {

	void addValueChangeListener(ValueChangeListener listener);

	void addClickListenerHomeButton(ClickListener listener);

	void addClickListenerAppointmentButton(ClickListener listener);

	void addClickListenerWheelChairButton(ClickListener listener);

	void addClickListenerBackButton(ClickListener listener);

	void showHomeButton();

	void hideHomeButton();

	void showAppointmentButton();

	void hideAppointmentButton();

	void showWheelChairButton();

	void hideWheelChairButton();

	void replaceAppointmentButtonWithBackButton();

	void replaceBackButtonWithAppointmentButton();

	void replaceWheelChairButtonWithHomeButton();

	void replaceHomeButtonWithWheelChairButton();

	@Pure
	NativeSelect getDropUpMenu();

	@Pure
	Button getAppointmentButton();
}
