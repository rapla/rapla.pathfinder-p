package com.pathfinder.view.container;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.pathfinder.view.components.KeyboardView;
import com.pathfinder.view.components.SearchField;
import com.pathfinder.view.components.TreeStructure;
import com.pathfinder.view.listener.SearchPanelViewListenerSpec;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

public class SearchPanel extends CustomComponent implements SearchPanelSpec {

	private TreeStructure treeStructure = null;
	private KeyboardView keyboardView = null;
	private SearchField searchField = null;

	private VerticalLayout layout = new VerticalLayout();

	private List<SearchPanelViewListenerSpec> listener = new ArrayList<SearchPanelViewListenerSpec>();

	public SearchPanel(TreeStructure treeStructure, KeyboardView keyboardView,
			SearchField searchField) {
		this.treeStructure = treeStructure;
		this.keyboardView = keyboardView;
		this.searchField = searchField;
		this.buildLayout();
		this.setCompositionRoot(layout);
	}

	@Override
	public void buildLayout() {
		this.layout.addComponent(treeStructure);
		this.layout.addComponent(keyboardView);
		this.layout.addComponent(searchField);
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
		keyboardView.updateTranslations(locale);
		searchField.updateTranslations(locale);
	}

}
