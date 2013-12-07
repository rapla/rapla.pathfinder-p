package com.pathfinder.view.components;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.vaadin.ui.Label;

public class TimeDate extends Label implements TimeDateSpec {

	public TimeDate() {
		this.refreshTimeAndDate();
	}

	@Override
	public void refreshTimeAndDate() {
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		// TODO Automatisch Sommer/Winterzeit?
		// TODO Timer der die Uhrzeit aktualisiert
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));
		this.setValue(formatter.format(new Date()) + "Uhr");
	}
}
