package com.pathfinder.view;

import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextField;

import de.vksi.c4j.ContractReference;

/**
 * SearchFieldSpec
 * 
 * @author alexh
 * 
 */
@ContractReference(SearchFieldSpecContract.class)
public interface SearchFieldSpec extends ComponentSpec {
	void addSearchFieldTextChangeListener(TextChangeListener listener);

	void addDeleteAllClickListener(ClickListener listener);

	void setSearchFieldValue(String newValue);

	void focusSearchField();

	int getCursorPosition();

	void setCursorPosition(int cursorPosition);

	TextField getSearchField();

	void hideSearchField();

	void showSearchField();

	Button getDeleteAllButton();
}
