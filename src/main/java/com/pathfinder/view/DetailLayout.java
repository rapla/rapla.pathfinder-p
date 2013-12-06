package com.pathfinder.view;

import com.pathfinder.view.components.DetailView;
import com.pathfinder.view.components.MenuBar;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

public class DetailLayout extends CustomComponent implements DetailLayoutSpec {
	private DetailView detailView = new DetailView();
	private SearchGroup searchField = new SearchGroup();
	private MenuBar menuBar = new MenuBar();

	private VerticalLayout layout = new VerticalLayout();

	public DetailLayout() {
		this.buildLayout();
		this.setCompositionRoot(layout);
	}

	@Override
	public void buildLayout() {
		this.layout.addComponent(detailView);
		this.layout.addComponent(searchField);
		this.layout.addComponent(menuBar);
	}

	@Override
	public void destroyLayout() {
		layout.removeAllComponents();
	}

}
