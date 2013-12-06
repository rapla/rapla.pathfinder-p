package com.pathfinder.view;

import com.pathfinder.view.components.Search;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

public class MainLayout extends CustomComponent {
	private Search search = new Search();
	private VerticalLayout layout = new VerticalLayout();

	public MainLayout() {
		layout.addComponent(search);
		this.setCompositionRoot(layout);
	}
}
