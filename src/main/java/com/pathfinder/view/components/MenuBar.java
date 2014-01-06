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
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
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
	private Button wheelChairDriverButton = new Button();

	private final NativeSelect dropUpMenu = new NativeSelect();
	private Button appointmentButton = new Button(
			translator.translate(TranslationKeys.EVENT));

	public MenuBar() {
		buildLanguagePicture();
		buildLanguageMenu();
		buildWheelChairDriver();
		buildMainLayout();

		setCompositionRoot(horizontalLayout);
	}

	private void buildWheelChairDriver() {
		wheelChairDriverButton.setStyleName(BaseTheme.BUTTON_LINK);
		wheelChairDriverButton.setIcon(new ThemeResource(
				"icon/wheelChairDriver.png"));
		wheelChairDriverButton.setPrimaryStyleName("wheelChairButtonMan");
		wheelChairDriverButton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				Notification.show("You pressed the WheelchairMan!");

			}
		});

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

		buildMainLayout();
		setCompositionRoot(horizontalLayout);

	}

	private void buildMainLayout() {
		horizontalLayout.addComponent(dropUpMenu);
		horizontalLayout.addComponent(appointmentButton);
		horizontalLayout.addComponent(wheelChairDriverButton);

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
		String language = "" + UI.getCurrent().getLocale();
		res = new ThemeResource("icon/" + language.substring(0, 2) + ".png");
		dropUpMenu.setIcon(res);
	}

	@Override
	public NativeSelect getDropUpMenu() {
		return dropUpMenu;
	}

	@Override
	public Button getAppointmentButton() {
		return appointmentButton;
	}
}