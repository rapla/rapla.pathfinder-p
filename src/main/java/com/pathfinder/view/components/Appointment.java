package com.pathfinder.view.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import com.pathfinder.translation.TranslationKeys;
import com.pathfinder.translation.Translator;
import com.pathfinder.view.listener.AppointmentViewListenerSpec;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class Appointment extends BrowserFrame implements AppointmentSpec {

	private String appointmentUrl = null;
	private List<AppointmentViewListenerSpec> listener = new ArrayList<AppointmentViewListenerSpec>();
	private Translator translator = Translator.getInstance();

	public Appointment() {
		this.setAlternateText(translator
				.translate(TranslationKeys.NO_DATA_AVAILABLE));
	}

	@Override
	public void refreshAppointmentView(String url) {
		this.appointmentUrl = url;
		if (StringUtils.isNotEmpty(appointmentUrl)) {
			this.setSource(new ExternalResource(this.appointmentUrl));
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
