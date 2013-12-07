package com.pathfinder.view.components;

import com.pathfinder.view.listener.AppointmentViewListenerSpec;

/**
 * AppointmentSpec
 * 
 * @author alexh
 * 
 */
public interface AppointmentSpec extends ComponentSpec {
	void refreshAppointmentView(String url);

	void addAppointmentViewListener(AppointmentViewListenerSpec listener);
}