package com.pathfinder.view;

import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class SearchField extends CustomComponent implements SearchFieldSpec {
	private final TranslatorSpec translator = Translator.getInstance();

	private final HorizontalLayout layout = new HorizontalLayout();
	private final Button deleteAllButton = new Button();
	private final TextField searchField = new TextField();

	private final ThemeResource deleteResource = new ThemeResource(
			"icon/DeleteAll.png");

	public SearchField() {
		this.init();
		this.addStyling();
		this.buildLayout();
		setCompositionRoot(layout);
	}

	private void init() {
		deleteAllButton.setIcon(deleteResource);
		searchField.setInputPrompt(translator
				.translate(TranslationKeys.SEARCH_PROMPT));
		searchField.setImmediate(true);
		searchField.setBuffered(false);
	}

	private void buildLayout() {
		layout.addComponent(searchField);
		layout.addComponent(deleteAllButton);
		this.setSizeFull();
	}

	private void addStyling() {
		layout.setPrimaryStyleName("search");
		searchField.setPrimaryStyleName("searchfield");
		deleteAllButton.setPrimaryStyleName("delete-icon");
	}

	@Override
	public void addSearchFieldValueChangeListener(ValueChangeListener listener) {
		searchField.addValueChangeListener(listener);
	}

	@Override
	public void addDeleteAllClickListener(ClickListener listener) {
		deleteAllButton.addClickListener(listener);
	}

	public void setSearchFieldValue(String newValue) {
		searchField.setValue(newValue);
	}

	@Override
	public void focusSearchField() {
		searchField.focus();
	}

	@Override
	public int getCursorPosition() {
		return searchField.getCursorPosition();
	}

	@Override
	public void setCursorPosition(int cursorPosition) {
		searchField.setCursorPosition(cursorPosition);
	}

	@Override
	public Button getDeleteAllButton() {
		return deleteAllButton;
	}

	@Override
	public TextField getSearchField() {
		return searchField;
	}

	@Override
	public void hideSearchField() {
		this.setVisible(false);
	}

	@Override
	public void showSearchField() {
		this.setVisible(true);
	}

	@Override
	public void updateTranslations() {
		searchField.setInputPrompt(translator
				.translate(TranslationKeys.SEARCH_PROMPT));
	}
}