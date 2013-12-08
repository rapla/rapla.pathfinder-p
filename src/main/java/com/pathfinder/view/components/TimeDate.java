package com.pathfinder.view.components;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import com.pathfinder.translation.TranslationKeys;
import com.pathfinder.translation.Translator;
import com.pathfinder.translation.TranslatorSpec;
import com.pathfinder.view.listener.TimeDateViewListenerSpec;
import com.vaadin.ui.Label;

public class TimeDate extends Label implements TimeDateSpec {

	private List<TimeDateViewListenerSpec> listener = new ArrayList<TimeDateViewListenerSpec>();
	private TranslatorSpec translator = Translator.getInstance();

	public TimeDate() {
		this.refreshTimeAndDate();
	}

	@Override
	public void refreshTimeAndDate() {
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		// TODO Automatisch Sommer/Winterzeit?
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));
		this.setValue(formatter.format(new Date()) + " "
				+ translator.translate(TranslationKeys.OCLOCK));
	}

	@Override
	public void updateTranslations(Locale locale) {
		refreshTimeAndDate();
	}

	@Override
	public void addTimeDateListener(TimeDateViewListenerSpec listener) {
		this.listener.add(listener);
	}

}