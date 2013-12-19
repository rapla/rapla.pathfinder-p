package com.pathfinder.view.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.pathfinder.view.listener.AppointmentViewListenerSpec;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class Appointment extends BrowserFrame implements AppointmentSpec {

	private List<AppointmentViewListenerSpec> listener = new ArrayList<AppointmentViewListenerSpec>();
	private TranslatorSpec translator = Translator.getInstance();

	public Appointment(String url) {
		this.setAlternateText(translator
				.translate(TranslationKeys.NO_DATA_AVAILABLE));

		if (StringUtils.isNotEmpty(url)) {
			this.setSource(new ExternalResource(url));// "http://demo.vaadin.com/sampler/")
		} else {
			this.setSource(new ExternalResource("about:blank"));
			// TODO Check Notification Type
			Notification
					.show(translator
							.translate(TranslationKeys.ERROR_LOADING_CALENAR),
							Type.ASSISTIVE_NOTIFICATION);
		}
	}

	@Override
	public void updateTranslations(Locale locale) {
		this.setAlternateText(translator
				.translate(TranslationKeys.NO_DATA_AVAILABLE));
	}

	@Override
	public void addAppointmentViewListener(AppointmentViewListenerSpec listener) {
		this.listener.add(listener);
	}
}
