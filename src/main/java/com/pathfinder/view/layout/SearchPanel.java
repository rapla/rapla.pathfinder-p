package com.pathfinder.view.layout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.pathfinder.view.components.Keyboard;
import com.pathfinder.view.components.SearchField;
import com.pathfinder.view.components.TreeStructure;
import com.pathfinder.view.listener.SearchPanelViewListenerSpec;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

public class SearchPanel extends CustomComponent implements SearchPanelSpec {

	private TreeStructure treeStructure = new TreeStructure();
	private Keyboard keyboard = new Keyboard();
	private SearchField searchField = new SearchField();

	private VerticalLayout layout = new VerticalLayout();

	private List<SearchPanelViewListenerSpec> listener = new ArrayList<SearchPanelViewListenerSpec>();

	public SearchPanel() {
		this.buildLayout();
		this.setCompositionRoot(layout);
	}

	@Override
	public void buildLayout() {
		this.layout.addComponent(treeStructure);
		this.layout.addComponent(keyboard);
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
		keyboard.updateTranslations(locale);
		searchField.updateTranslations(locale);
	}

}
