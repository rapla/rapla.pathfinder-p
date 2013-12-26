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

@SuppressWarnings("serial")
public class SearchField extends CustomComponent implements SearchFieldSpec {

	private Button magnifier;
	private Button deleteAll;
	private TextField searchField;

	private ThemeResource deleteResource = new ThemeResource("icon/DeleteAll.png");
	private ThemeResource magnifierResource = new ThemeResource("icon/Magnifier.png");

	private HorizontalLayout horizontal = new HorizontalLayout();

	private TranslatorSpec translator = Translator.getInstance();

	public SearchField() {
		this.init();
	}

	private void init() {
		searchField = new TextField();
		magnifier = new Button();
		deleteAll = new Button();

		magnifier.setEnabled(false);

		searchField.setInputPrompt(translator
				.translate(TranslationKeys.SEARCH_PROMP));

		magnifier.setIcon(magnifierResource);
		deleteAll.setIcon(deleteResource);
		
		horizontal.setPrimaryStyleName("search");
		searchField.setPrimaryStyleName("searchfield");
		magnifier.setPrimaryStyleName("search-icon");
		deleteAll.setPrimaryStyleName("delete-icon");

		horizontal.addComponent(magnifier);
		horizontal.addComponent(searchField);
		horizontal.addComponent(deleteAll);

		setCompositionRoot(horizontal);
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

	public Button getMagnifier() {
		return magnifier;
	}

	public void setMagnifier(Button magnifier) {
		this.magnifier = magnifier;
	}

	// TODO wird diese Methode benötigt?
	public Button getDeleteAll() {
		return deleteAll;
	}

	public void setDeleteAll(Button deleteAll) {
		this.deleteAll = deleteAll;
	}

	public TextField getSearchField() {
		return searchField;
	}

	public void setSearchField(TextField searchField) {
		this.searchField = searchField;
	}

}
