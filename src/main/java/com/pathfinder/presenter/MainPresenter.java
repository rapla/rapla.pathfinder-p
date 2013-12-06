package com.pathfinder.presenter;

import com.pathfinder.view.MainLayout;
import com.vaadin.ui.CustomComponent;

/**
 * 
 * @author alexh
 * 
 *         To see something when you build the project
 */
public class MainPresenter {
	private MainLayout mainLayout = null;

	public MainPresenter() {
		this.buildTheMainLayout();
	}

	private void buildTheMainLayout() {
		mainLayout = new MainLayout();
	}

	public CustomComponent getMainLayoutView() {
		return mainLayout;
	}
}
