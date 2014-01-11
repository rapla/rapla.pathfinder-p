package com.pathfinder.view.components;

import de.vksi.c4j.ContractReference;

/**
 * AppointmentSpec
 * 
 * @author alexh
 * 
 */
@ContractReference(AppointmentViewSpecContract.class)
public interface AppointmentViewSpec extends ComponentSpec {
	void setAppointmentUrl(String url);

	void hideAppointmentView();

	void showAppointmentView();
}
