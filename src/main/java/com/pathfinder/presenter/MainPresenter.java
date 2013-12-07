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
	private final InfoPanelPresenter infoPanelPresenter = new InfoPanelPresenter();
	private final DetailContainerPresenter detailContainerPresenter = new DetailContainerPresenter();
	private final SearchPanelPresenter searchPanelPresenter = new SearchPanelPresenter();
	private final MenuBarPresenter menuBarPresenter = new MenuBarPresenter();
	private final MainLayout mainLayout = new MainLayout(infoPanelPresenter.getInfoPanel(), detailContainerPresenter.getDetailContainer(), searchPanelPresenter.getSearchPanel(), menuBarPresenter.getMenuBar());

	public MainPresenter() {
	}

	public CustomComponent getMainLayoutView() {
		return mainLayout;
	}
}
