package com.pathfinder.view.components;

import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextField;

/**
 * SearchFieldSpec
 * 
 * @author alexh
 * 
 */
public interface SearchFieldSpec extends ComponentSpec {
	void addSearchFieldListener(TextChangeListener listener);

	void addMagnifierClickListener(ClickListener listener);

	void addDeleteAllClickListener(ClickListener listener);

	TextField getSearchField();
}
