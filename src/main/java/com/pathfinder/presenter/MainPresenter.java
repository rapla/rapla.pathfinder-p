package com.pathfinder.presenter;

import java.util.Locale;

import com.pathfinder.view.layout.DesktopLayout;
import com.pathfinder.view.listener.MainLayoutViewListenerSpec;
import com.vaadin.ui.CustomComponent;

/**
 * To see something when you build the project
 * 
 * @author alexh
 * 
 */
public class MainPresenter implements MainLayoutViewListenerSpec,
		MainPresenterSpec {
	private final InfoPanelPresenter infoPanelPresenter = new InfoPanelPresenter();
	private final DetailContainerPresenter detailContainerPresenter = new DetailContainerPresenter();
	private final SearchPanelPresenter searchPanelPresenter = new SearchPanelPresenter();
	private final MenuBarPresenter menuBarPresenter = new MenuBarPresenter();
	private final DesktopLayout desktopLayout = new DesktopLayout(
			infoPanelPresenter.getInfoPanel(),
			detailContainerPresenter.getDetailContainer(),
			searchPanelPresenter.getSearchPanel(),
			menuBarPresenter.getMenuBar());

	public MainPresenter() {
		desktopLayout.addMainLayoutViewListener(this);
	}

	public CustomComponent getMainLayoutView() {
		return desktopLayout;
	}

	@Override
	public void languageChanged(Locale locale) {
		desktopLayout.updateTranslations(locale);
	}
}
