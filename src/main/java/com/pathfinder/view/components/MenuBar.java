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
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
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

	private final GridLayout layout = new GridLayout(3, 1);
	private final VerticalLayout popupLayout = new VerticalLayout();
	private final PopupButton languagePopupButton = new PopupButton();
	private final Button appointmentButton = new Button(
			translator.translate(TranslationKeys.EVENT));
	private final Button backButton = new Button(
			translator.translate(TranslationKeys.BACK));
	private final Button wheelChairDriverButton = new Button();
	private final Button homeButton = new Button();

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
		this.setPrimaryStyleName("menubar");
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
		String flagFilename = THEME_RESOURCES_FOLDER
				+ UI.getCurrent().getLocale().getLanguage()
				+ THEME_RESOURCES_SUFFIX;
		languagePopupButton.setIcon(new ThemeResource(flagFilename));
	}

	private void buildWheelChairDriver() {
		wheelChairDriverButton.setIcon(new ThemeResource(
				"icon/wheelChairDriver.png"));
		wheelChairDriverButton.setPrimaryStyleName("wheelChairButtonMan");
	}

	private void addStyling() {
		this.setPrimaryStyleName("menu-bar");
		layout.setPrimaryStyleName("menulayout");
		appointmentButton.setPrimaryStyleName("menubutton");
		// TODO backButton.setPrimaryStyleName("backbutton");
	}

	private void buildMainLayout() {
		this.hideAppointmentButton();
		layout.addComponent(languagePopupButton, 0, 0);
		layout.addComponent(appointmentButton, 1, 0);
		layout.addComponent(wheelChairDriverButton, 2, 0);
		layout.setComponentAlignment(languagePopupButton, Alignment.TOP_CENTER);
		layout.setComponentAlignment(appointmentButton, Alignment.TOP_CENTER);
		layout.setComponentAlignment(wheelChairDriverButton,
				Alignment.TOP_CENTER);
		layout.setSizeFull();
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
	public void showAppointmentButton() {
		this.appointmentButton.setVisible(true);
	}

	@Override
	public void hideAppointmentButton() {
		this.appointmentButton.setVisible(false);
	}

	@Override
	public void showBackButton() {
		this.backButton.setVisible(true);
	}

	@Override
	public void hideBackButton() {
		this.backButton.setVisible(false);
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
	public void showHomeButton() {
		this.homeButton.setVisible(true);
	}

	@Override
	public void hideHomeButton() {
		this.homeButton.setVisible(false);
	}

	@Override
	public void replaceAppointmentButtonWithBackButton() {
		if (appointmentButton.equals(layout.getComponent(1, 0))) {
			layout.replaceComponent(appointmentButton, backButton);
			layout.setComponentAlignment(backButton, Alignment.TOP_CENTER);
		}
	}

	@Override
	public void replaceBackButtonWithAppointmentButton() {
		if (backButton.equals(layout.getComponent(1, 0))) {
			layout.replaceComponent(backButton, appointmentButton);
			layout.setComponentAlignment(appointmentButton,
					Alignment.TOP_CENTER);
		}
	}

	@Override
	public void replaceWheelChairButtonWithHomeButton() {
		if (wheelChairDriverButton.equals(layout.getComponent(2, 0))) {
			layout.replaceComponent(wheelChairDriverButton, homeButton);
			layout.setComponentAlignment(homeButton, Alignment.TOP_RIGHT);
		}
		homeButton.setPrimaryStyleName("homebutton");
		ThemeResource res = new ThemeResource(THEME_RESOURCES_FOLDER + "home"
				+ THEME_RESOURCES_SUFFIX);
		homeButton.setIcon(res);
	}

	@Override
	public void replaceHomeButtonWithWheelChairButton() {
		if (homeButton.equals(layout.getComponent(2, 0))) {
			layout.replaceComponent(homeButton, wheelChairDriverButton);
			layout.setComponentAlignment(wheelChairDriverButton,
					Alignment.TOP_RIGHT);
		}
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