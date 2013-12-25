package com.pathfinder.view.components;

import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.Button.ClickListener;

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
}
