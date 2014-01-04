package com.pathfinder.view.components;

import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;

/**
 * MenuBar with buttons for the language, appointment and wheelchair
 * 
 * @author alexh
 * 
 */
public class MenuBar extends CustomComponent implements MenuBarSpec {
	private final HorizontalLayout horizontalLayout = new HorizontalLayout();
	private final TranslatorSpec translator = Translator.getInstance();

	private final String[] languages = { "deutsch", "english" };
	private final NativeSelect dropUpMenu = new NativeSelect();
	private Button appointmentButton = new Button(
			translator.translate(TranslationKeys.EVENT));

	public MenuBar() {
		buildLanguageMenu();
		buildMainLayout();
		setCompositionRoot(horizontalLayout);
	}

	private void buildLanguageMenu() {
		for (int i = 0; i < languages.length; i++) {
			dropUpMenu.addItem(languages[i]);
			dropUpMenu.setItemCaption(i, languages[i]);
		}
		dropUpMenu.setNullSelectionAllowed(false);
		dropUpMenu.setValue(languages[0]);
		dropUpMenu.setImmediate(true);
		dropUpMenu.setPrimaryStyleName("languages");
		buildMainLayout();
		setCompositionRoot(horizontalLayout);
	}

	private void buildMainLayout() {
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
	public String[] getLanguages() {
		return languages;
	}

	@Override
	public void updateTranslations() {
		appointmentButton.setCaption(translator
				.translate(TranslationKeys.EVENT));
	}
}