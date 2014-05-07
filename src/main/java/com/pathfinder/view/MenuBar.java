package com.pathfinder.view;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.vaadin.hene.popupbutton.PopupButton;

import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.pathfinder.view.MenuBarSpec;
import com.vaadin.event.ShortcutAction.KeyCode;
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
	private final Button wheelChairButton = new Button();
	private final Button homeButton = new Button();

	// Saves all flag image resources to avoid unnecessary reloading of
	// resources
	private Map<Locale, Resource> flagResources = new HashMap<Locale, Resource>();
	// Saves all flag images to allow adding of listeners in presenter class
	private Map<Locale, Image> flagImages = new HashMap<Locale, Image>();

	private final String THEME_RESOURCES_FOLDER = "icon/";
	private final String THEME_RESOURCES_SUFFIX = ".png";
	private final String STYLE_CLASS_MENUBAR = "menuBar";
	private final String STYLE_CLASS_LANGUAGE_BUTTON = "languageButton";
	private final String STYLE_CLASS_FLAG_IMAGE = "flagImage";
	private final String STYLE_CLASS_WHEELCHAIR_BUTTON = "wheelChairButton";
	private final String STYLE_CLASS_HOME_BUTTON = "homeButton";

	public MenuBar() {
		this.buildLanguagePopup();
		this.buildButtons();
		this.buildMainLayout();
		this.addStyling();
		this.setCompositionRoot(layout);
	}

	private void buildLanguagePopup() {
		// load flag resources
		String flagFilename;
		for (Locale locale : translator.getSupportedLocales()) {
			flagFilename = THEME_RESOURCES_FOLDER + locale.getLanguage()
					+ THEME_RESOURCES_SUFFIX;
			flagResources.put(locale, new ThemeResource(flagFilename));
		}

		// build language popup
		Image flagImage;
		for (Locale locale : flagResources.keySet()) {
			flagImage = new Image();
			flagImage.setPrimaryStyleName(STYLE_CLASS_FLAG_IMAGE);
			flagImage.setHeight(60, Unit.PIXELS);
			flagImage.setSource(flagResources.get(locale));
			flagImages.put(locale, flagImage);
			popupLayout.addComponent(flagImage);
		}

		languagePopupButton.setContent(popupLayout);
		languagePopupButton.setPrimaryStyleName(STYLE_CLASS_LANGUAGE_BUTTON);
		String recentFlagFilename = THEME_RESOURCES_FOLDER
				+ UI.getCurrent().getLocale().getLanguage()
				+ THEME_RESOURCES_SUFFIX;
		languagePopupButton.setClickShortcut(KeyCode.ARROW_UP);
		languagePopupButton.setIcon(new ThemeResource(recentFlagFilename));
	}

	private void buildButtons() {
		wheelChairButton.setIcon(new ThemeResource(THEME_RESOURCES_FOLDER
				+ "wheelChairDriver" + THEME_RESOURCES_SUFFIX));
		homeButton.setIcon(new ThemeResource(THEME_RESOURCES_FOLDER + "home"
				+ THEME_RESOURCES_SUFFIX));
	}

	private void buildMainLayout() {
		layout.addComponent(languagePopupButton, 0, 0);
		layout.addComponent(wheelChairButton, 2, 0);
		layout.setComponentAlignment(languagePopupButton, Alignment.TOP_LEFT);
		layout.setComponentAlignment(wheelChairButton, Alignment.TOP_RIGHT);
		layout.setSizeFull();
	}

	private void addStyling() {
		this.setPrimaryStyleName(STYLE_CLASS_MENUBAR);
		popupLayout.setPrimaryStyleName(STYLE_CLASS_LANGUAGE_BUTTON);
		wheelChairButton.setPrimaryStyleName(STYLE_CLASS_WHEELCHAIR_BUTTON);
		homeButton.setPrimaryStyleName(STYLE_CLASS_HOME_BUTTON);
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
	public void addClickListenerWheelChairButton(ClickListener listener) {
		wheelChairButton.addClickListener(listener);
	}

	@Override
	public void hideOpenLanguagePopup() {
		languagePopupButton.setPopupVisible(false);
	}

	@Override
	public void showWheelChairButton() {
		this.wheelChairButton.setVisible(true);
	}

	@Override
	public void hideWheelChairButton() {
		this.wheelChairButton.setVisible(false);
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
	public void replaceWheelChairButtonWithHomeButton() {
		if (wheelChairButton.equals(layout.getComponent(2, 0))) {
			layout.replaceComponent(wheelChairButton, homeButton);
			layout.setComponentAlignment(homeButton, Alignment.TOP_RIGHT);
		}
	}

	@Override
	public void replaceHomeButtonWithWheelChairButton() {
		if (homeButton.equals(layout.getComponent(2, 0))) {
			layout.replaceComponent(homeButton, wheelChairButton);
			layout.setComponentAlignment(wheelChairButton, Alignment.TOP_RIGHT);
		}
	}

	@Override
	public void updateTranslations() {
		String flagFilename = THEME_RESOURCES_FOLDER
				+ UI.getCurrent().getLocale().getLanguage()
				+ THEME_RESOURCES_SUFFIX;
		languagePopupButton.setIcon(new ThemeResource(flagFilename));
	}
}