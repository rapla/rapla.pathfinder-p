package com.pathfinder.view.components;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.BaseTheme;

/**
 * MenuBar with buttons for the language, appointment and wheelchair
 * 
 * @author alexh
 * 
 */
public class MenuBar extends CustomComponent implements MenuBarSpec {
	private final HorizontalLayout layout = new HorizontalLayout();
	private final TranslatorSpec translator = Translator.getInstance();

	private ThemeResource res;
	private final NativeSelect dropUpMenu = new NativeSelect();
	private Button appointmentButton = new Button(
			translator.translate(TranslationKeys.EVENT));
	private Button backButton = new Button(
			translator.translate(TranslationKeys.BACK));
	private Button wheelChairDriverButton = new Button();

	public MenuBar() {
		buildLanguagePicture();
		buildLanguageMenu();
		buildWheelChairDriver();
		buildMainLayout();

		setCompositionRoot(layout);
	}

	private void buildLanguagePicture() {
		String language = "" + UI.getCurrent().getLocale();
		language = language.substring(0, 2);
		res = new ThemeResource("icon/" + language + ".png");
		dropUpMenu.setIcon(res);
	}

	private void buildLanguageMenu() {
		Map<String, Locale> languages = new HashMap<String, Locale>();
		languages.put("deutsch", Locale.GERMANY);
		languages.put("english", Locale.US);
		languages.put("español", new Locale("es"));
		languages.put("français", Locale.FRANCE);
		dropUpMenu.setPrimaryStyleName("languages");

		for (String language : languages.keySet()) {
			Locale locale = languages.get(language);
			dropUpMenu.addItem(languages.get(language));
			dropUpMenu.setItemCaption(locale, language);
		}

		dropUpMenu.setNullSelectionAllowed(false);
		dropUpMenu.setValue(UI.getCurrent().getLocale());
		dropUpMenu.setImmediate(true);
	}

	private void buildWheelChairDriver() {
		wheelChairDriverButton.setStyleName(BaseTheme.BUTTON_LINK);
		wheelChairDriverButton.setIcon(new ThemeResource(
				"icon/wheelChairDriver.png"));
		wheelChairDriverButton.setPrimaryStyleName("wheelChairButtonMan");
	}

	private void buildMainLayout() {
		layout.addComponent(dropUpMenu);
		layout.addComponent(appointmentButton);
		layout.addComponent(wheelChairDriverButton);

		// TODO Only commented for testing
		// this.appointmentButton.setVisible(false);
	}

	@Override
	public void addValueChangeListener(ValueChangeListener listener) {
		this.dropUpMenu.addValueChangeListener(listener);
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
	public void showAppointmentButton() {
		this.appointmentButton.setVisible(true);
	}

	@Override
	public void hideAppointmentButton() {
		this.appointmentButton.setVisible(false);
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
	public NativeSelect getDropUpMenu() {
		return dropUpMenu;
	}

	@Override
	public Button getAppointmentButton() {
		return appointmentButton;
	}

	@Override
	public void updateTranslations() {
		appointmentButton.setCaption(translator
				.translate(TranslationKeys.EVENT));
		backButton.setCaption(translator.translate(TranslationKeys.BACK));
		String language = "" + UI.getCurrent().getLocale();
		res = new ThemeResource("icon/" + language.substring(0, 2) + ".png");
		dropUpMenu.setIcon(res);
	}
}