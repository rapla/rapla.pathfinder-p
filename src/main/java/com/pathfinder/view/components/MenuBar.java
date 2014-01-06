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
import com.vaadin.ui.Image;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.UI;

/**
 * MenuBar with buttons for the language, appointment and wheelchair
 * 
 * @author alexh
 * 
 */
public class MenuBar extends CustomComponent implements MenuBarSpec {
	private final HorizontalLayout horizontalLayout = new HorizontalLayout();
	private final TranslatorSpec translator = Translator.getInstance();

	private ThemeResource res;
	private Image image;

	private final NativeSelect dropUpMenu = new NativeSelect();
	private Button appointmentButton = new Button(
			translator.translate(TranslationKeys.EVENT));

	public MenuBar() {
		buildLanguagePicture();
		buildLanguageMenu();
		buildMainLayout();
		setCompositionRoot(horizontalLayout);
	}

	private void buildLanguagePicture() {
		String language = "" + UI.getCurrent().getLocale();
		language = language.substring(0, 2);
		res = new ThemeResource("icon/" + language + ".png");
		image = new Image(null, res);
		image.setPrimaryStyleName("language-Picture");

	}

	private void buildLanguageMenu() {

		Map<String, Locale> languages = new HashMap<String, Locale>();
		languages.put("deutsch", Locale.GERMAN);
		languages.put("english", Locale.ENGLISH);
		languages.put("español", new Locale("es"));
		languages.put("français", Locale.FRENCH);

		for (String language : languages.keySet()) {
			Locale locale = languages.get(language);
			dropUpMenu.addItem(languages.get(language));
			dropUpMenu.setItemCaption(locale, language);
		}

		dropUpMenu.setNullSelectionAllowed(false);
		dropUpMenu.setValue(UI.getCurrent().getLocale());
		dropUpMenu.setImmediate(true);
		dropUpMenu.setPrimaryStyleName("languages");
		buildMainLayout();
		setCompositionRoot(horizontalLayout);
	}

	private void buildMainLayout() {
		horizontalLayout.addComponent(image);
		horizontalLayout.addComponent(dropUpMenu);
		horizontalLayout.addComponent(appointmentButton);

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
	public void showAppointmentButton() {
		this.appointmentButton.setVisible(true);
	}

	@Override
	public void hideAppointmentButton() {
		this.appointmentButton.setVisible(false);
	}

	@Override
	public void updateTranslations() {
		appointmentButton.setCaption(translator
				.translate(TranslationKeys.EVENT));
		res = new ThemeResource("icon/" + UI.getCurrent().getLocale() + ".png");
		image.setSource(res);
	}
}