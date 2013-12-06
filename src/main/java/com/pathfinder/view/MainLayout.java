package com.pathfinder.view;

import com.pathfinder.view.components.FreeRoomView;
import com.pathfinder.view.components.TimeDate;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

public class MainLayout extends CustomComponent implements MainLayoutSpec {
	private TimeDate timeDate = new TimeDate();
	private FreeRoomView freeRoomView = new FreeRoomView();
	private SearchGroup searchField = new SearchGroup();

	private VerticalLayout layout = new VerticalLayout();

	public MainLayout() {
		this.buildLayout();
		this.setCompositionRoot(layout);
	}

	@Override
	public void buildLayout() {
		this.layout.addComponent(timeDate);
		this.layout.addComponent(freeRoomView);
		this.layout.addComponent(searchField);
	}

	@Override
	public void destroyLayout() {
		layout.removeAllComponents();
	}
}
