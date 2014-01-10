package com.pathfinder.view.components;

import com.pathfinder.view.layout.MenuBarLayoutSpec;

import de.vksi.c4j.ContractReference;

/**
 * MenuBarSpec
 * 
 * @author alexh
 * 
 */
@ContractReference(MenuBarSpecContract.class)
public interface MenuBarSpec extends ComponentSpec, MenuBarLayoutSpec {

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
}
