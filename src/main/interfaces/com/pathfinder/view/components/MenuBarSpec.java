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

	void addClickListenerAppointmentButton(ClickListener listener);

	void showAppointmentButton();

	void hideAppointmentButton();

	@Pure
	NativeSelect getDropUpMenu();

	@Pure
	Button getAppointmentButton();
}
