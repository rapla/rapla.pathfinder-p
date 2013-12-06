package com.pathfinder.view;

import com.pathfinder.view.components.SearchField;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

public class MainLayout extends CustomComponent implements MainLayoutSpec {
	private SearchField searchField = new SearchField();
	private VerticalLayout layout = new VerticalLayout();

	public MainLayout() {
		layout.addComponent(searchField);
		this.setCompositionRoot(layout);
	}
}
