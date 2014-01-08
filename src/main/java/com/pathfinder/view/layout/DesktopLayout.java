package com.pathfinder.view.layout;

import com.pathfinder.util.widgetset.DateTime;
import com.pathfinder.view.components.AppointmentView;
import com.pathfinder.view.components.AppointmentViewSpec;
import com.pathfinder.view.components.DateTimeSpec;
import com.pathfinder.view.components.FreeRoomView;
import com.pathfinder.view.components.FreeRoomViewSpec;
import com.pathfinder.view.components.MenuBar;
import com.pathfinder.view.components.MenuBarSpec;
import com.pathfinder.view.container.DetailContainerSpec;
import com.pathfinder.view.container.SearchPanelSpec;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

/**
 * Defines the main layout of the stele/desktop navigation
 * 
 * @author alexh
 * 
 */
public class DesktopLayout extends CustomComponent implements DesktopLayoutSpec {
	private final DateTimeSpec dateTime = new DateTime();
	private final FreeRoomViewSpec freeRoom = new FreeRoomView();
	private final MenuBarSpec menuBar = new MenuBar();
	private SearchPanelSpec searchPanel = null;
	private DetailContainerSpec detailContainer = null;
	private final AppointmentViewSpec appointmentView = new AppointmentView();

	private final VerticalLayout layout = new VerticalLayout();

	public DesktopLayout(SearchPanelSpec searchPanel) {
		this.searchPanel = searchPanel;

		this.buildLayout();
		this.setCompositionRoot(layout);
	}

	@Override
	public void buildLayout() {
		this.layout.addComponent(dateTime);
		this.layout.addComponent(freeRoom);
		this.layout.addComponent(searchPanel);
		// TODO
		// this.layout.addComponent(detailContainer);
		this.layout.addComponent(appointmentView);
		this.layout.addComponent(menuBar);
	}

	@Override
	public void addLanguageValueChangeListener(ValueChangeListener listener) {
		menuBar.addValueChangeListener(listener);
	}

	@Override
	public void addClickListenerAppointmentButton(ClickListener listener) {
		menuBar.addClickListenerAppointmentButton(listener);
	}

	@Override
	public void addClickListenerWheelChairButton(ClickListener listener) {
		menuBar.addClickListenerWheelChairButton(listener);
	}

	@Override
	public void addClickListenerBackButton(ClickListener listener) {
		menuBar.addClickListenerBackButton(listener);
	}

	@Override
	public void hideAppointmentButton() {
		menuBar.hideAppointmentButton();
	}

	@Override
	public void showAppointmentButton() {
		menuBar.showAppointmentButton();
	}

	@Override
	public void switchToSearchView() {
		menuBar.replaceBackButtonWithAppointmentButton();
		appointmentView.hideAppointmentView();
		// TODO
		// detailContainer.removeDetails(...);
		// detailContainer.hideDetailContainer();
		freeRoom.showFreeRoom();
		searchPanel.showSearchPanel();
	}

	@Override
	public <T> void switchToDetailView() {
		// TODO
		// detailContainer.setDetails(...);
		appointmentView.hideAppointmentView();
		freeRoom.hideFreeRoom();
		searchPanel.hideSearchPanel();
		// detailContainer.showDetailContainer();
	}

	@Override
	public void switchToAppointmentView() {
		menuBar.replaceAppointmentButtonWithBackButton();
		// TODO
		// detailContainer.removeDetails(...);
		// detailContainer.hideDetailContainer();
		freeRoom.hideFreeRoom();
		searchPanel.hideSearchPanel();
		appointmentView.showAppointmentView();
	}

	@Override
	public void setAppointmentUrl(String url) {
		appointmentView.setUrl(url);
	}

	@Override
	public void destroyLayout() {
		layout.removeAllComponents();
	}

	public FreeRoomViewSpec getFreeRoom() {
		return freeRoom;
	}

	@Override
	public void updateTranslations() {
		dateTime.updateTranslations();
		freeRoom.updateTranslations();
		appointmentView.updateTranslations();
		menuBar.updateTranslations();
	}
}