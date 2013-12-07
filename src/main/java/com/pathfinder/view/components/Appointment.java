package com.pathfinder.view.components;

import org.apache.commons.lang.StringUtils;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class Appointment extends BrowserFrame implements AppointmentSpec {

	private String appointmentUrl = null;

	public Appointment() {
		this.setAlternateText("Keine Daten verfügbar");
	}

	@Override
	public void refreshAppointmentView(String url) {
		this.appointmentUrl = url;
		if (StringUtils.isNotEmpty(appointmentUrl)) {
			this.setSource(new ExternalResource(this.appointmentUrl));
		} else {
			this.setSource(new ExternalResource("about:blank"));
			// TODO Check Notification Type
			Notification.show("Kalender kann nicht abgerufen werden",
					Type.ASSISTIVE_NOTIFICATION);
		}
	}
}
