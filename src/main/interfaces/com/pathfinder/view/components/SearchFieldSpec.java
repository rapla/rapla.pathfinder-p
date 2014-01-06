package com.pathfinder.view.components;

import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextField;

import de.vksi.c4j.ContractReference;
import de.vksi.c4j.Pure;

/**
 * SearchFieldSpec
 * 
 * @author alexh
 * 
 */
@ContractReference(SearchFieldSpecContract.class)
public interface SearchFieldSpec extends ComponentSpec {
	void addSearchFieldListener(TextChangeListener listener);

	void addMagnifierClickListener(ClickListener listener);

	void addDeleteAllClickListener(ClickListener listener);

	@Pure
	TextField getSearchField();

	@Pure
	Button getMagnifierButton();

	@Pure
	Button getDeleteAllButton();
}
