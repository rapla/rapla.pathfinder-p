package com.pathfinder.presenter;

import com.pathfinder.view.layout.DesktopLayout;
import com.pathfinder.view.layout.MobileLayout;
import com.vaadin.ui.CustomComponent;

public class MobilePresenter implements MobilePresenterSpec{
	// private final InfoPanelPresenter infoPanelPresenter = new
	// InfoPanelPresenter();
	// private final DetailContainerPresenter detailContainerPresenter = new
	// DetailContainerPresenter();
	// private final SearchPanelPresenter searchPanelPresenter = new
	// SearchPanelPresenter();
	// private final MenuBarPresenter menuBarPresenter = new MenuBarPresenter();
	private final MobileLayout mobileLayout = new MobileLayout();
	
	public CustomComponent getMobileLayoutView() {
		return mobileLayout;
	}

}
