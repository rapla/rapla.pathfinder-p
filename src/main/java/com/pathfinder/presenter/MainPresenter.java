package com.pathfinder.presenter;

import java.util.Locale;

import com.pathfinder.view.components.ComponentSpec;
import com.pathfinder.view.layout.MainLayout;
import com.pathfinder.view.listener.MainLayoutViewListenerSpec;
import com.vaadin.ui.CustomComponent;

/**
 * To see something when you build the project
 * 
 * @author alexh
 * 
 */
<<<<<<< HEAD
public class MainPresenter {
	private final InfoPanelPresenter infoPanelPresenter = new InfoPanelPresenter();
	private final DetailContainerPresenter detailContainerPresenter = new DetailContainerPresenter();
	private final SearchPanelPresenter searchPanelPresenter = new SearchPanelPresenter();
	private final MenuBarPresenter menuBarPresenter = new MenuBarPresenter();
	private final MainLayout mainLayout = new MainLayout(infoPanelPresenter.getInfoPanel(), detailContainerPresenter.getDetailContainer(), searchPanelPresenter.getSearchPanel(), menuBarPresenter.getMenuBar());
=======
public class MainPresenter implements MainLayoutViewListenerSpec, ComponentSpec {
	private final MainLayout mainLayout = new MainLayout();
>>>>>>> branch 'master' of https://alexanderhaller90@code.google.com/p/rapla.pathfinder-p/

	public MainPresenter() {
		mainLayout.addMainLayoutViewListener(this);
	}

	public CustomComponent getMainLayoutView() {
		return mainLayout;
	}

	@Override
	public void updateTranslations(Locale locale) {
		mainLayout.updateTranslations(locale);
	}

	@Override
	public void languageChanged(Locale locale) {
		updateTranslations(locale);
	}
}
