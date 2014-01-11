package com.pathfinder.view.components;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.vaadin.hene.popupbutton.PopupButton;

import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * MenuBar with buttons for language, appointment, home and wheelchair-mode
 * 
 * @author alexh
 * 
 */
public class MenuBar extends CustomComponent implements MenuBarSpec {
	private final TranslatorSpec translator = Translator.getInstance();

	private final HorizontalLayout layout = new HorizontalLayout();
	private final VerticalLayout popupLayout = new VerticalLayout();
	private final PopupButton languagePopupButton = new PopupButton();
	private final Button homeButton = new Button("Home");
	private final Button appointmentButton = new Button(
			translator.translate(TranslationKeys.EVENT));
	private final Button backButton = new Button(
			translator.translate(TranslationKeys.BACK));
	private final Button wheelChairDriverButton = new Button();

	// Saves all flag image resources to avoid unnecessary reloading of
	// resources
	private Map<Locale, Resource> flagResources = new HashMap<Locale, Resource>();
	// Saves all flag images to allow adding of listeners in presenter class
	private Map<Locale, Image> flagImages = new HashMap<Locale, Image>();

	private final String THEME_RESOURCES_FOLDER = "icon/";
	private final String THEME_RESOURCES_SUFFIX = ".png";
	private final String STYLE_CLASS_LANGUAGE_BUTTON = "languageButton";
	private final String STYLE_CLASS_FLAG_IMAGE = "flagImage";

	public MenuBar() {
		buildLanguagePopup();
		buildWheelChairDriver();
		buildMainLayout();
		setCompositionRoot(layout);
	}

	private void loadFlagResources() {
		String flagFilename;
		for (Locale locale : translator.getSupportedLocales()) {
			flagFilename = THEME_RESOURCES_FOLDER + locale.getLanguage()
					+ THEME_RESOURCES_SUFFIX;
			flagResources.put(locale, new ThemeResource(flagFilename));
		}
	}

	private void buildLanguagePopup() {
		popupLayout.setPrimaryStyleName(STYLE_CLASS_LANGUAGE_BUTTON);

		loadFlagResources();

		Image flagImage;
		for (Locale locale : flagResources.keySet()) {
			flagImage = new Image();
			flagImage.setPrimaryStyleName(STYLE_CLASS_FLAG_IMAGE);
			flagImage.setSource(flagResources.get(locale));
			flagImages.put(locale, flagImage);
			popupLayout.addComponent(flagImage);
		}

		languagePopupButton.setContent(popupLayout);
		languagePopupButton.setPrimaryStyleName(STYLE_CLASS_LANGUAGE_BUTTON);
		languagePopupButton.setIcon(flagResources.get(UI.getCurrent()
				.getLocale()));
	}

	private void buildWheelChairDriver() {
		wheelChairDriverButton.setIcon(new ThemeResource(
				"icon/wheelChairDriver.png"));
		wheelChairDriverButton.setPrimaryStyleName("wheelChairButtonMan");
	}

	private void buildMainLayout() {
		layout.addComponent(languagePopupButton);
		layout.addComponent(appointmentButton);
		layout.addComponent(wheelChairDriverButton);

		// TODO Only commented for testing
		// this.hideAppointmentButton();
	}

	@Override
	public void addClickListenerFlagPopup(Locale locale,
			com.vaadin.event.MouseEvents.ClickListener listener) {
		flagImages.get(locale).addClickListener(listener);
	}

	@Override
	public void addClickListenerHomeButton(ClickListener listener) {
		homeButton.addClickListener(listener);
	}

	@Override
	public void addClickListenerAppointmentButton(ClickListener listener) {
		appointmentButton.addClickListener(listener);
	}

	@Override
	public void addClickListenerWheelChairButton(ClickListener listener) {
		wheelChairDriverButton.addClickListener(listener);
	}

	@Override
	public void addClickListenerBackButton(ClickListener listener) {
		backButton.addClickListener(listener);
	}

	@Override
	public void hideOpenLanguagePopup() {
		languagePopupButton.setPopupVisible(false);
	}

	@Override
	public void showHomeButton() {
		this.homeButton.setVisible(true);
	}

	@Override
	public void hideHomeButton() {
		this.homeButton.setVisible(false);
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
	public void showWheelChairButton() {
		this.wheelChairDriverButton.setVisible(true);
	}

	@Override
	public void hideWheelChairButton() {
		this.wheelChairDriverButton.setVisible(false);
	}

	@Override
	public void replaceAppointmentButtonWithBackButton() {
		layout.replaceComponent(appointmentButton, backButton);
	}

	@Override
	public void replaceBackButtonWithAppointmentButton() {
		layout.replaceComponent(backButton, appointmentButton);
	}

	@Override
	public void replaceWheelChairButtonWithHomeButton() {
		layout.replaceComponent(wheelChairDriverButton, homeButton);
	}

	@Override
	public void replaceHomeButtonWithWheelChairButton() {
		layout.replaceComponent(homeButton, wheelChairDriverButton);
	}

	@Override
	public void updateTranslations() {
		appointmentButton.setCaption(translator
				.translate(TranslationKeys.EVENT));
		backButton.setCaption(translator.translate(TranslationKeys.BACK));
		languagePopupButton.setIcon(flagResources.get(UI.getCurrent()
				.getLocale()));
	}
}