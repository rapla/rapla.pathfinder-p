package com.pathfinder.view.components;

import com.vaadin.ui.BrowserFrame;

import de.vksi.c4j.ContractReference;
import de.vksi.c4j.Pure;

/**
 * AppointmentSpec
 * 
 * @author alexh
 * 
 */
@ContractReference(AppointmentViewSpecContract.class)
public interface AppointmentViewSpec extends ComponentSpec {
	void setUrl(String url);

	void hideAppointmentView();

	void showAppointmentView();

	@Pure
	BrowserFrame getBrowserFrame();
}
