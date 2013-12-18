package com.pathfinder.presenter;

import java.util.Locale;
import java.util.TimerTask;

import com.pathfinder.util.widgetset.DateTime;
import com.pathfinder.view.components.FreeRoom;
import com.pathfinder.view.components.MenuBar;
import com.pathfinder.view.container.DetailContainer;
import com.pathfinder.view.container.InfoPanel;
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
	// Needed components
	private final DateTime dateTime = new DateTime();
	private final FreeRoom freeRoomView = new FreeRoom();
	// TODO it´s not possible to use the DetailContainer yet because of a
	// NullPointerException
	// private DetailContainer detailContainer = new DetailContainer(null, null,
	// null);
	private final MenuBar menuBar = new MenuBar();

	// Needed sub-presenters
	private final SearchPanelPresenter searchPanelPresenter = new SearchPanelPresenter();

	// Layouts
	private final InfoPanel infoPanel = new InfoPanel(dateTime, freeRoomView);
	private final DesktopLayout desktopLayout = new DesktopLayout(infoPanel,
			menuBar, searchPanelPresenter.getSearchPanel());

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
