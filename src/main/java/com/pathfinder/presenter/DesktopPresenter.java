package com.pathfinder.presenter;

import java.util.Locale;
import java.util.TimerTask;

import com.pathfinder.model.CourseModel;
import com.pathfinder.model.PersonModel;
import com.pathfinder.model.PoiModel;
import com.pathfinder.model.RoomModel;
import com.pathfinder.util.widgetset.DateTime;
import com.pathfinder.view.components.DateTimeSpec;
import com.pathfinder.view.components.FreeRoom;
import com.pathfinder.view.components.FreeRoomSpec;
import com.pathfinder.view.components.MenuBar;
import com.pathfinder.view.components.MenuBarSpec;
import com.pathfinder.view.container.InfoPanel;
import com.pathfinder.view.container.InfoPanelSpec;
import com.pathfinder.view.layout.DesktopLayout;
import com.pathfinder.view.layout.DesktopLayoutSpec;
import com.pathfinder.view.listener.DesktopLayoutViewListenerSpec;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

/**
 * To see something when you build the project
 * 
 * @author alexh
 * 
 */
public class DesktopPresenter implements DesktopLayoutViewListenerSpec,
		DesktopPresenterSpec {
	// Needed components
	private final DateTimeSpec dateTime = new DateTime();
	private final FreeRoomSpec freeRoomView = new FreeRoom();
	// TODO it´s not possible to use the DetailContainer yet because of a
	// NullPointerException
	// private DetailContainer detailContainer = new DetailContainer(null, null,
	// null);
	private final MenuBarSpec menuBar = new MenuBar();

	// Needed sub-presenters
	private final SearchPanelPresenterSpec searchPanelPresenter = new SearchPanelPresenter();

	// Layouts
	private final InfoPanelSpec infoPanel = new InfoPanel((DateTime) dateTime,
			(FreeRoom) freeRoomView);
	private final DesktopLayoutSpec desktopLayout = new DesktopLayout(
			(InfoPanel) infoPanel, (MenuBar) menuBar,
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
		return (DesktopLayout) desktopLayout;
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

	@Override
	public void setRoomContainer(BeanItemContainer<RoomModel> beanItemContainer) {
		searchPanelPresenter.setRoomContainer(beanItemContainer);
	}

	@Override
	public void setCourseContainer(
			BeanItemContainer<CourseModel> beanItemContainer) {
		searchPanelPresenter.setCourseContainer(beanItemContainer);
	}

	@Override
	public void setPersonContainer(
			BeanItemContainer<PersonModel> beanItemContainer) {
		searchPanelPresenter.setPersonContainer(beanItemContainer);
	}

	@Override
	public void setPoiContainer(BeanItemContainer<PoiModel> beanItemContainer) {
		searchPanelPresenter.setPoiContainer(beanItemContainer);
	}
}
