package com.pathfinder.view.layout;

import com.pathfinder.view.components.Keyboard;
import com.pathfinder.view.components.SearchField;
import com.pathfinder.view.components.TreeStructure;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

public class SearchPanel extends CustomComponent implements SearchPanelSpec {

	private TreeStructure treeStructure = null;
	private Keyboard keyboardView = null;
	private SearchField searchField = null;

	private VerticalLayout layout = new VerticalLayout();

	public SearchPanel(TreeStructure treeStructure, Keyboard keyboardView, SearchField searchField) {
		this.treeStructure  = treeStructure;
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
}
