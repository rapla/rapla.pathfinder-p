package com.pathfinder.view.components;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.CustomComponent;

public class AppointmentView extends CustomComponent implements
		AppointmentViewSpec {
	private static final Logger LOGGER = LogManager
			.getLogger(AppointmentView.class.getName());
	private final TranslatorSpec translator = Translator.getInstance();

	private final BrowserFrame browserFrame = new BrowserFrame();

	public AppointmentView() {
		buildLayout();
		this.setCompositionRoot(browserFrame);
	}

	private void buildLayout() {
		this.hideAppointmentView();
		this.browserFrame.setAlternateText(translator
				.translate(TranslationKeys.NO_DATA_AVAILABLE));
		this.browserFrame.setSizeFull();
	}

	@Override
	public void setAppointmentUrl(String url) {
		if (!"".equals(url)) {
			int code = 0;
			try {
				URL u = new URL(url);
				HttpURLConnection huc = (HttpURLConnection) u.openConnection();
				huc.setRequestMethod("GET"); // OR huc.setRequestMethod
												// ("HEAD");
				huc.connect();
				code = huc.getResponseCode();

				if (code != 404) {
					browserFrame.setSource(new ExternalResource(url));
				} else {
					browserFrame.setSource(new ExternalResource("about:blank"));
				}
			} catch (MalformedURLException e) {
				browserFrame.setSource(new ExternalResource("about:blank"));
				LOGGER.error("Event url was malformed");
			} catch (IOException e) {
				browserFrame.setSource(new ExternalResource("about:blank"));
				LOGGER.error("Problem to load event");
			}
		} else {
			browserFrame.setSource(new ExternalResource("about:blank"));
		}
	}

	@Override
	public void hideAppointmentView() {
		this.setVisible(false);
	}

	@Override
	public void showAppointmentView() {
		browserFrame.setHeight(90f, Unit.PERCENTAGE);
		this.setVisible(true);
	}

	@Override
	public void updateTranslations() {
		browserFrame.setAlternateText(translator
				.translate(TranslationKeys.NO_DATA_AVAILABLE));
	}
}