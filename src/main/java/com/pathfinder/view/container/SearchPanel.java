package com.pathfinder.view.container;

import com.pathfinder.view.components.AccordionView;
import com.pathfinder.view.components.Keyboard;
import com.pathfinder.view.components.SearchField;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

/**
 * Defines the layout for the SearchPanel: TreeStructure, Keyboard and
 * SearchField
 * 
 * @author alexh
 * 
 */
public class SearchPanel extends CustomComponent implements SearchPanelSpec {

	private AccordionView accordionView = null;
	private Keyboard keyboard = null;
	private SearchField searchField = null;

	private VerticalLayout layout = new VerticalLayout();

	public SearchPanel(AccordionView accordionView, Keyboard keyboardView,
			SearchField searchField) {
		this.accordionView = accordionView;
		this.keyboard = keyboardView;
		this.searchField = searchField;

		this.setCompositionRoot(layout);
		this.buildLayout();
	}

	@Override
	public void buildLayout() {
		this.accordionView.setSizeFull();
		this.keyboard.setSizeFull();
		this.searchField.setSizeFull();
		this.layout.addComponent(accordionView);
		this.layout.addComponent(searchField);
		this.layout.addComponent(keyboard);
		this.layout.setSizeFull();
	}

	@Override
	public void destroyLayout() {
		layout.removeAllComponents();
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
		keyboard.updateTranslations();
		searchField.updateTranslations();
	}
}