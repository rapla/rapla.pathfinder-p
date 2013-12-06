package com.pathfinder.view;

import com.pathfinder.view.components.FreeRoomView;
import com.pathfinder.view.components.MenuBar;
import com.pathfinder.view.components.TimeDate;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class MainLayout extends CustomComponent implements MainLayoutSpec {
	private TimeDate timeDate = new TimeDate();
	private FreeRoomView freeRoomView = new FreeRoomView();
	private SearchGroup searchField = new SearchGroup();
	private MenuBar menuBar = new MenuBar();

	private VerticalLayout layout = new VerticalLayout();

	public MainLayout() {
		this.buildLayout();
		this.setCompositionRoot(layout);
	}

	@Override
	public void buildLayout() {
		
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.addComponent(timeDate);
		horizontalLayout.addComponent(freeRoomView);
		this.layout.addComponent(horizontalLayout);
		this.layout.addComponent(searchField);
		this.layout.addComponent(menuBar);
	}

	@Override
	public void destroyLayout() {
		layout.removeAllComponents();
	}
}
