package com.pathfinder.view.components;

import java.util.Locale;

import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class SearchField extends CustomComponent implements SearchFieldSpec {

	private final Button magnifier = new Button();
	private final Button deleteAll = new Button();
	private final TextField searchField = new TextField();

	private final ThemeResource deleteResource = new ThemeResource(
			"icon/DeleteAll.png");
	private final ThemeResource magnifierResource = new ThemeResource(
			"icon/Magnifier.png");

	private final HorizontalLayout horizontal = new HorizontalLayout();

	private final TranslatorSpec translator = Translator.getInstance();

	public SearchField() {
		this.init();
	}

	private void init() {
		magnifier.setEnabled(false);
		magnifier.setIcon(magnifierResource);

		deleteAll.setIcon(deleteResource);

		searchField.setInputPrompt(translator
				.translate(TranslationKeys.SEARCH_PROMP));

		this.setStyles();
		this.buildLayout();
		setCompositionRoot(horizontal);
	}

	private void buildLayout() {
		horizontal.addComponent(magnifier);
		horizontal.addComponent(searchField);
		horizontal.addComponent(deleteAll);
	}

	private void setStyles() {
		horizontal.setPrimaryStyleName("search");
		searchField.setPrimaryStyleName("searchfield");
		magnifier.setPrimaryStyleName("search-icon");
		deleteAll.setPrimaryStyleName("delete-icon");
	}

	@Override
	public void updateTranslations(Locale locale) {
		searchField.setInputPrompt(translator
				.translate(TranslationKeys.SEARCH_PROMP));
	}

	@Override
	public void addSearchFieldListener(TextChangeListener listener) {
		searchField.addTextChangeListener(listener);
	}

	@Override
	public void addMagnifierClickListener(ClickListener listener) {
		magnifier.addClickListener(listener);
	}

	@Override
	public void addDeleteAllClickListener(ClickListener listener) {
		deleteAll.addClickListener(listener);
	}

	@Override
	public TextField getSearchField() {
		return searchField;
	}
}