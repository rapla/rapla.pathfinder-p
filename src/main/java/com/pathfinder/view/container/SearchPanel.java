package com.pathfinder.view.container;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.pathfinder.view.components.Keyboard;
import com.pathfinder.view.components.SearchField;
import com.pathfinder.view.components.AccordionView;
import com.pathfinder.view.listener.SearchPanelViewListenerSpec;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * Defines the layout for the SearchPanel: TreeStructure, Keyboard and SearchField
 * 
 * @author alexh
 *
 */
public class SearchPanel extends CustomComponent implements SearchPanelSpec {

	private AccordionView accordionView = null;
	private Keyboard keyboard = null;
	private SearchField searchField = null;

	private VerticalLayout layout = new VerticalLayout();
	private HorizontalLayout horziontalLayout = new HorizontalLayout();

	private List<SearchPanelViewListenerSpec> listener = new ArrayList<SearchPanelViewListenerSpec>();

	public SearchPanel(AccordionView accordionView, Keyboard keyboardView,
			SearchField searchField) {
		this.accordionView = accordionView;
		this.keyboard = keyboardView;
		this.searchField = searchField;
		
		this.buildLayout();
		this.setCompositionRoot(layout);
	}

	@Override
	public void buildLayout() {
		this.accordionView.setSizeFull();
		this.keyboard.setSizeFull();
		this.searchField.setSizeFull();
		this.layout.addComponent(accordionView);
		this.layout.addComponent(keyboard);
		this.layout.addComponent(searchField);
		this.layout.setSizeFull();
	}

	@Override
	public void destroyLayout() {
		layout.removeAllComponents();
	}

	@Override
	public void addSearchPanelViewListener(SearchPanelViewListenerSpec listener) {
		this.listener.add(listener);
	}

	@Override
	public void updateTranslations(Locale locale) {
		accordionView.updateTranslations(locale);
		keyboard.updateTranslations(locale);
		searchField.updateTranslations(locale);
	}
}
