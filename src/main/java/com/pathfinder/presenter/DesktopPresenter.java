package com.pathfinder.presenter;

import java.util.Locale;
import java.util.TimerTask;

import com.pathfinder.view.layout.DesktopLayout;
import com.pathfinder.view.listener.MainLayoutViewListenerSpec;
import com.vaadin.ui.CustomComponent;

/**
 * To see something when you build the project
 * 
 * @author alexh
 * 
 */
public class DesktopPresenter implements MainLayoutViewListenerSpec,
		DesktopPresenterSpec {
	// These components are always needed
	private final InfoPanelPresenter infoPanelPresenter = new InfoPanelPresenter();
	private final MenuBarPresenter menuBarPresenter = new MenuBarPresenter();
	private final SearchPanelPresenter searchPanelPresenter = new SearchPanelPresenter();

	private final DesktopLayout desktopLayout = new DesktopLayout(
			infoPanelPresenter.getInfoPanel(), menuBarPresenter.getMenuBar(),
			searchPanelPresenter.getSearchPanel());

	public DesktopPresenter() {
		desktopLayout.addMainLayoutViewListener(this);
	}

	// class ResourceClickListener extends
	// {
	// Timer timer = new Timer();
	// timer.schedule(new RespawnDesktopLayoutTimer(), 60000);
	// }

	@Override
	public CustomComponent getDesktopLayoutView() {
		return desktopLayout;
	}

	@Override
	public void languageChanged(Locale locale) {
		desktopLayout.updateTranslations(locale);
	}

	class RespawnDesktopLayoutTimer extends TimerTask {

		@Override
		public void run() {
			desktopLayout.switchToSearchView();
		}
	}
}
