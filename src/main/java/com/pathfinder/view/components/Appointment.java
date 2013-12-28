package com.pathfinder.view.components;

import java.util.Locale;

import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;

public class Appointment extends BrowserFrame implements AppointmentSpec {

	private final TranslatorSpec translator = Translator.getInstance();

	public Appointment() {
		this.setAlternateText(translator
				.translate(TranslationKeys.NO_DATA_AVAILABLE));
	}

	@Override
	public void setUrl(String url) {
		if (!"".equals(url)) {
			this.setSource(new ExternalResource(url));
		} else {
			this.setSource(new ExternalResource("about:blank"));
		}
	}

	@Override
	public void updateTranslations(Locale locale) {
		this.setAlternateText(translator
				.translate(TranslationKeys.NO_DATA_AVAILABLE));
	}
}