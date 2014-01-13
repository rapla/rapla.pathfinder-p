package com.pathfinder.view.components;

import com.vaadin.data.Property.ValueChangeListener;
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
	void addSearchFieldValueChangeListener(ValueChangeListener listener);

	void addMagnifierClickListener(ClickListener listener);

	void addDeleteAllClickListener(ClickListener listener);

	void focusSearchField();

	int getCursorPosition();

	void setCursorPosition(int cursorPosition);

	TextField getSearchField();

	Button getMagnifierButton();

	Button getDeleteAllButton();
}
