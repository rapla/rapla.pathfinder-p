package com.pathfinder.view.layout;

import com.pathfinder.util.widgetset.DateTime;
import com.pathfinder.view.components.Appointment;
import com.pathfinder.view.components.DateTimeSpec;
import com.pathfinder.view.components.FreeRoom;
import com.pathfinder.view.components.FreeRoomSpec;
import com.pathfinder.view.components.MenuBar;
import com.pathfinder.view.container.DetailContainer;
import com.pathfinder.view.container.SearchPanel;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

/**
 * Defines the main layout of the stele navigation
 * 
 * @author alexh
 * 
 */
public class DesktopLayout extends CustomComponent implements DesktopLayoutSpec {
	private final DateTimeSpec dateTime = new DateTime();
	private final FreeRoomSpec freeRoom = new FreeRoom();
	private MenuBar menuBar = null;
	private SearchPanel searchPanel = null;
	private DetailContainer<?> detailContainer = null;
	private Appointment appointment = null;

	private final VerticalLayout layout = new VerticalLayout();

	public DesktopLayout(MenuBar menuBar, SearchPanel searchPanel,
			Appointment appointment) {
		this.menuBar = menuBar;
		this.searchPanel = searchPanel;
		this.appointment = appointment;

		this.setCompositionRoot(layout);
		this.buildLayout();
	}

	@Override
	public void buildLayout() {
		this.layout.addComponent((DateTime) dateTime);
		this.layout.addComponent((FreeRoom) freeRoom);
		this.layout.addComponent(searchPanel);
		this.layout.addComponent(menuBar);
	}

	@Override
	public void switchToSearchView() {
		Component oldComponent = layout.getComponent(1);
		layout.replaceComponent(oldComponent, searchPanel);
		detailContainer = null;
	}

	@Override
	public <T> void switchToDetailView() {
		Component oldComponent = layout.getComponent(1);
		detailContainer = new DetailContainer<T>(null, null, null);
		layout.replaceComponent(oldComponent, detailContainer);
	}

	@Override
	public void switchToAppointmentView() {
		Component oldComponent = layout.getComponent(1);
		layout.replaceComponent(oldComponent, appointment);
	}

	@Override
	public void setAppointmentUrl(String url) {
		appointment.setUrl(url);
	}

	@Override
	public void destroyLayout() {
		layout.removeAllComponents();
	}

	@Override
	public void updateTranslations() {
		dateTime.updateTranslations();
		freeRoom.updateTranslations();
	}
}