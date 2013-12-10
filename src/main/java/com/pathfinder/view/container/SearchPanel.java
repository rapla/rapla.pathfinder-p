package com.pathfinder.view.container;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.pathfinder.view.components.ClearButton;
import com.pathfinder.view.components.Keyboard;
import com.pathfinder.view.components.SearchField;
import com.pathfinder.view.components.TreeStructure;
import com.pathfinder.view.listener.SearchPanelViewListenerSpec;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class SearchPanel extends CustomComponent implements SearchPanelSpec {

	private TreeStructure treeStructure = null;
	private Keyboard keyboard = null;
	private SearchField searchField = null;
	private ClearButton clearButton = null;

	private VerticalLayout layout = new VerticalLayout();
	private HorizontalLayout horziontalLayout = new HorizontalLayout();

	private List<SearchPanelViewListenerSpec> listener = new ArrayList<SearchPanelViewListenerSpec>();

	public SearchPanel(TreeStructure treeStructure, Keyboard keyboardView,
			SearchField searchField, ClearButton clearButton) {
		this.treeStructure = treeStructure;
		this.keyboard = keyboardView;
		this.searchField = searchField;
		this.clearButton = clearButton;
		this.buildLayout();
		this.setCompositionRoot(layout);
	}

	@Override
	public void buildLayout() {
		this.layout.addComponent(treeStructure);
		this.layout.addComponent(keyboard);
		this.horziontalLayout.addComponent(searchField);
		this.horziontalLayout.addComponent(clearButton);
		this.layout.addComponent(horziontalLayout);
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
		treeStructure.updateTranslations(locale);
		keyboard.updateTranslations(locale);
		searchField.updateTranslations(locale);
	}

}
