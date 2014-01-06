package com.pathfinder.view.components;

import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.CustomComponent;

public class AppointmentView extends CustomComponent implements
		AppointmentViewSpec {

	private final TranslatorSpec translator = Translator.getInstance();
	private final BrowserFrame browserFrame = new BrowserFrame();

	public AppointmentView() {
		buildLayout();
	}

	private void buildLayout() {
		browserFrame.setAlternateText(translator
				.translate(TranslationKeys.NO_DATA_AVAILABLE));
		browserFrame.setSizeFull();
		this.setCompositionRoot(browserFrame);
	}

	@Override
	public void setUrl(String url) {
		if (!"".equals(url)) {
			browserFrame.setSource(new ExternalResource(url));
		} else {
			browserFrame.setSource(new ExternalResource("about:blank"));
		}
	}

	@Override
	public void hideAppointmentView() {
		this.setSizeFull();
		this.setVisible(false);
	}

	@Override
	public void showAppointmentView() {
		browserFrame.setHeight(1000f, Unit.PIXELS);
		this.setVisible(true);
	}

	@Override
	public void updateTranslations() {
		browserFrame.setAlternateText(translator
				.translate(TranslationKeys.NO_DATA_AVAILABLE));
	}

	@Override
	public BrowserFrame getBrowserFrame() {
		return browserFrame;
	}
}