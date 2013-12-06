package com.pathfinder.presenter;

import com.pathfinder.view.layout.MainLayout;
import com.vaadin.ui.CustomComponent;

/**
 * To see something when you build the project
 * 
 * @author alexh
 * 
 */
public class MainPresenter {
	private final MainLayout mainLayout = new MainLayout();

	public MainPresenter() {
	}

	public CustomComponent getMainLayoutView() {
		return mainLayout;
	}
}
