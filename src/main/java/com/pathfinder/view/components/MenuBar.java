package com.pathfinder.view.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.pathfinder.view.listener.MenuBarViewListenerSpec;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;

/**
 * MenuBar with buttons for the language, wheelchair appointment view and to get back to the search panel
 * 
 * @author alexh
 * 
 */
public class MenuBar extends CustomComponent implements MenuBarSpec {

	private final HorizontalLayout mainLayout = new HorizontalLayout();
	private final TranslatorSpec translator = Translator.getInstance();
	private Button germanButton = new Button(
			translator.translate(TranslationKeys.GERMAN));
	private Button englishButton = new Button(
			translator.translate(TranslationKeys.ENGLISH));

	private List<MenuBarViewListenerSpec> listener = new ArrayList<MenuBarViewListenerSpec>();

	public MenuBar() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	private void buildMainLayout() {
		mainLayout.addComponent(germanButton);
		mainLayout.addComponent(englishButton);
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
	}

	@Override
	// TODO
	public void addMenuBarListener(MenuBarViewListenerSpec listener) {
		this.listener.add(listener);
	}
}
