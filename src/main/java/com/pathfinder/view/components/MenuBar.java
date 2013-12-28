package com.pathfinder.view.components;

import java.util.Locale;

import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;

/**
 * MenuBar with buttons for the language, appointment and wheelchair
 * 
 * @author alexh
 * 
 */
public class MenuBar extends CustomComponent implements MenuBarSpec {

	private final HorizontalLayout horizontalLayout = new HorizontalLayout();
	private final TranslatorSpec translator = Translator.getInstance();
	private Button germanButton = new Button(
			translator.translate(TranslationKeys.GERMAN));
	private Button englishButton = new Button(
			translator.translate(TranslationKeys.ENGLISH));
	private Button appointmentButton = new Button(
			translator.translate(TranslationKeys.EVENT));

	public MenuBar() {
		buildMainLayout();
		setCompositionRoot(horizontalLayout);
	}

	private void buildMainLayout() {
		horizontalLayout.addComponent(germanButton);
		horizontalLayout.addComponent(englishButton);
		horizontalLayout.addComponent(appointmentButton);
		
		// TODO Only commented for testing
		// this.appointmentButton.setVisible(false);
	}

	@Override
	public void addClickListenerGermanButton(ClickListener listener) {
		germanButton.addClickListener(listener);
	}

	@Override
	public void addClickListenerEnglishButton(ClickListener listener) {
		englishButton.addClickListener(listener);
	}

	@Override
	public void addClickListenerAppointmentButton(ClickListener listener) {
		appointmentButton.addClickListener(listener);
	}

	@Override
	public void showAppointmentButton() {
		this.appointmentButton.setVisible(true);
	}

	@Override
	public void hideAppointmentButton() {
		this.appointmentButton.setVisible(false);
	}

	@Override
	public Button getGermanButton() {
		return this.germanButton;
	}

	@Override
	public Button getEnglishButton() {
		return this.englishButton;
	}

	@Override
	public void updateTranslations(Locale locale) {
		germanButton.setCaption(translator.translate(TranslationKeys.GERMAN));
		englishButton.setCaption(translator.translate(TranslationKeys.ENGLISH));
		appointmentButton.setCaption(translator
				.translate(TranslationKeys.EVENT));
	}
}