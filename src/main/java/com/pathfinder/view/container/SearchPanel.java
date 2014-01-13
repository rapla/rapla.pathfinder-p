package com.pathfinder.view.container;

import com.pathfinder.view.components.AccordionView;
import com.pathfinder.view.components.AccordionViewSpec;
import com.pathfinder.view.components.SearchField;
import com.pathfinder.view.components.SearchFieldSpec;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

/**
 * Defines the layout for the SearchPanel: Accordion, Keyboard and SearchField
 * 
 * @author alexh
 * 
 */
public class SearchPanel extends CustomComponent implements SearchPanelSpec {

	private AccordionViewSpec accordionView = null;
	private SearchFieldSpec searchField = null;

	private final VerticalLayout layout = new VerticalLayout();

	public SearchPanel(AccordionView accordionView, SearchField searchField) {
		this.accordionView = accordionView;
		this.searchField = searchField;

		this.buildLayout();
		this.setCompositionRoot(layout);
	}

	private void buildLayout() {
		this.accordionView.setSizeFull();
		this.searchField.setSizeFull();
		this.layout.addComponent(accordionView);
		this.layout.addComponent(searchField);
		this.layout.setSizeFull();
	}

	@Override
	public void hideSearchPanel() {
		this.setVisible(false);
	}

	@Override
	public void showSearchPanel() {
		this.setVisible(true);
	}

	@Override
	public void updateTranslations() {
		accordionView.updateTranslations();
		searchField.updateTranslations();
	}
}