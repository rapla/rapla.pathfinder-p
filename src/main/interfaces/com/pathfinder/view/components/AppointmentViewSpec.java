package com.pathfinder.view.components;

/**
 * AppointmentSpec
 * 
 * @author alexh
 * 
 */
public interface AppointmentViewSpec extends ComponentSpec {
	void setUrl(String url);

	void hideAppointmentView();

	void showAppointmentView();
}
