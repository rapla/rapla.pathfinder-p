package com.pathfinder.view.layout;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.pathfinder.model.Attribut;
import com.pathfinder.model.EventModel;
import com.pathfinder.model.FreeRoomModel;
import com.pathfinder.model.ResourceModel;
import com.pathfinder.util.widgetset.BackToHomeScreenListenerSpec;
import com.pathfinder.util.widgetset.DateTime;
import com.pathfinder.view.components.AccordionView;
import com.pathfinder.view.components.AccordionViewSpec;
import com.pathfinder.view.components.AppointmentView;
import com.pathfinder.view.components.AppointmentViewSpec;
import com.pathfinder.view.components.DateTimeSpec;
import com.pathfinder.view.components.FreeRoomView;
import com.pathfinder.view.components.FreeRoomViewSpec;
import com.pathfinder.view.components.Keyboard;
import com.pathfinder.view.components.KeyboardSpec;
import com.pathfinder.view.components.MenuBar;
import com.pathfinder.view.components.MenuBarSpec;
import com.pathfinder.view.components.SearchField;
import com.pathfinder.view.components.SearchFieldSpec;
import com.pathfinder.view.container.DetailContainer;
import com.pathfinder.view.container.DetailContainerSpec;
import com.pathfinder.view.container.SearchPanel;
import com.pathfinder.view.container.SearchPanelSpec;
import com.pathfinder.view.listener.KeyboardViewListenerSpec;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * Defines the main layout of the stele view
 * 
 * @author alexh
 * 
 */
public class SteleLayout extends CustomComponent implements SteleLayoutSpec {
	private final DateTimeSpec dateTime = new DateTime();
	private final FreeRoomViewSpec freeRoom = new FreeRoomView();
	private final AccordionViewSpec accordionView = new AccordionView();
	private final KeyboardSpec keyboard = new Keyboard();
	private final SearchFieldSpec searchField = new SearchField();
	private final SearchPanelSpec searchPanel = new SearchPanel(
			(AccordionView) accordionView, (SearchField) searchField);
	private final MenuBarSpec menuBar = new MenuBar();
	private final DetailContainerSpec detailContainer = new DetailContainer();
	private final AppointmentViewSpec appointmentView = new AppointmentView();

	private final VerticalLayout mainLayout = new VerticalLayout();
	private final VerticalLayout contentLayout = new VerticalLayout();
	private final VerticalLayout layoutNormal = new VerticalLayout();
	private final HorizontalLayout layoutWheelChair = new HorizontalLayout();

	public SteleLayout() {
		this.buildLayout();
		this.setCompositionRoot(mainLayout);
	}

	private void buildLayout() {
		// For the wheel chair button / view
		this.layoutNormal.addComponent(searchPanel);
		this.layoutNormal.addComponent(keyboard);

		this.contentLayout.addComponent(freeRoom);
		this.contentLayout.addComponent(layoutNormal);
		this.contentLayout.addComponent(detailContainer);
		this.contentLayout.addComponent(appointmentView);
		this.contentLayout.setSizeFull();
		// this.contentLayout.setHeight(900, Unit.PIXELS);

		this.mainLayout.addComponent(dateTime);
		this.mainLayout.addComponent(contentLayout);
		this.mainLayout.addComponent(menuBar);

		this.mainLayout.setExpandRatio(contentLayout, 1);
		setPrimaryStyleName("main");
	}

	@Override
	public void addKeyboardListener(KeyboardViewListenerSpec listener) {
		this.keyboard.addKeyboardViewListener(listener);
	}

	@Override
	public void addItemClickListener(ItemClickListener listener) {
		this.accordionView.addItemClickListener(listener);
	}

	@Override
	public void addClickListenerHomeButton(ClickListener listener) {
		menuBar.addClickListenerHomeButton(listener);
	}

	@Override
	public void addClickListenerWheelChairButton(ClickListener listener) {
		menuBar.addClickListenerWheelChairButton(listener);
	}

	@Override
	public void addSearchFieldTextChangeListener(TextChangeListener listener) {
		searchField.addSearchFieldTextChangeListener(listener);
	}

	@Override
	public void addDeleteAllClickListener(ClickListener listener) {
		this.searchField.addDeleteAllClickListener(listener);
	}

	@Override
	public void addClickListenerFlagPopup(Locale locale,
			com.vaadin.event.MouseEvents.ClickListener listener) {
		menuBar.addClickListenerFlagPopup(locale, listener);
	}

	@Override
	public void addBackToHomeListener(BackToHomeScreenListenerSpec listener) {
		dateTime.addBackToHomeListener(listener);
	}

	@Override
	public void addKeyboardViewListener(KeyboardViewListenerSpec listener) {
		keyboard.addKeyboardViewListener(listener);
	}

	@Override
	public void setRoomContainer(
			BeanItemContainer<ResourceModel> beanItemContainer) {
		accordionView.setRoomContainer(beanItemContainer);
	}

	@Override
	public void setCourseContainer(
			BeanItemContainer<ResourceModel> beanItemContainer) {
		accordionView.setCourseContainer(beanItemContainer);
	}

	@Override
	public void setPersonContainer(
			BeanItemContainer<ResourceModel> beanItemContainer) {
		accordionView.setPersonContainer(beanItemContainer);
	}

	@Override
	public void setPoiContainer(
			BeanItemContainer<ResourceModel> beanItemContainer) {
		accordionView.setPoiContainer(beanItemContainer);
	}

	@Override
	public void useFiltersForAllTables(String searchString) {
		accordionView.useFiltersForAllTables(searchString);
	}

	@Override
	public void refreshFreeRooms(
			BeanItemContainer<FreeRoomModel> freeRoomContainer) {
		freeRoom.refreshFreeRooms(freeRoomContainer);
	}

	@Override
	public void setAppointmentUrl(String url) {
		appointmentView.setAppointmentUrl(url);
	}

	@Override
	public void hideOpenLanguagePopup() {
		menuBar.hideOpenLanguagePopup();
	}

	@Override
	public TextField getSearchField() {
		return searchField.getSearchField();
	}

	@Override
	public void focusSearchField() {
		searchField.focusSearchField();
	}

	@Override
	public int getCursorPosition() {
		return searchField.getCursorPosition();
	}

	@Override
	public void setCursorPosition(int cursorPosition) {
		searchField.setCursorPosition(cursorPosition);
	}

	@Override
	public void hideFreeRoomView() {
		freeRoom.hideFreeRoomView();
	}

	@Override
	public void showFreeRoomView() {
		freeRoom.showFreeRoomView();
	}

	@Override
	public void hideAppointmentView() {
		appointmentView.hideAppointmentView();
	}

	@Override
	public void showAppointmentView() {
		appointmentView.showAppointmentView();
	}

	@Override
	public void showHomeButton() {
		menuBar.showHomeButton();
	}

	@Override
	public void hideHomeButton() {
		menuBar.hideHomeButton();
	}

	@Override
	public void showWheelChairButton() {
		menuBar.showWheelChairButton();
	}

	@Override
	public void hideWheelChairButton() {
		menuBar.hideWheelChairButton();
	}

	@Override
	public void changeWheelChairView() {
		if (contentLayout.getComponentIndex(layoutNormal) >= 0) {
			layoutWheelChair.addComponent(keyboard);
			layoutWheelChair.addComponent(searchPanel);
			layoutWheelChair.setSizeFull();
			this.contentLayout.replaceComponent(layoutNormal, layoutWheelChair);
			this.layoutNormal.removeAllComponents();
		} else {
			layoutNormal.addComponent(searchPanel);
			layoutNormal.addComponent(keyboard);
			layoutNormal.setSizeFull();
			this.contentLayout.replaceComponent(layoutWheelChair, layoutNormal);
			layoutWheelChair.removeAllComponents();
		}
	}

	@Override
	public void replaceWheelChairButtonWithHomeButton() {
		menuBar.replaceWheelChairButtonWithHomeButton();
	}

	@Override
	public void replaceHomeButtonWithWheelChairButton() {
		menuBar.replaceHomeButtonWithWheelChairButton();
	}

	@Override
	public Button getDeleteAllButton() {
		return searchField.getDeleteAllButton();
	}

	@Override
	public List<KeyboardViewListenerSpec> getKeyboardViewListener() {
		return keyboard.getKeyboardViewListener();
	}

	@Override
	public void hideSearchPanel() {
		searchPanel.hideSearchPanel();
	}

	@Override
	public void showSearchPanel() {
		searchPanel.showSearchPanel();
	}

	@Override
	public void deselectClickedItem(Table table, Object itemId) {
		accordionView.deselectClickedItem(table, itemId);
	}

	@Override
	public void addDetails(ResourceModel resourceModel,
			BeanItemContainer<Attribut> resourceDetails) {
		detailContainer.addDetails(resourceModel, resourceDetails);
	}

	@Override
	public void removeDetails() {
		detailContainer.removeDetails();
	}

	@Override
	public void hideDetailContainer() {
		detailContainer.hideDetailContainer();
	}

	@Override
	public void showDetailContainer() {
		detailContainer.showDetailContainer();
	}

	@Override
	public void hideKeyboard() {
		keyboard.hideKeyboard();
	}

	@Override
	public void showKeyboard() {
		keyboard.showKeyboard();
	}

	@Override
	public void updateTranslations() {
		dateTime.updateTranslations();
		freeRoom.updateTranslations();
		searchPanel.updateTranslations();
		keyboard.updateTranslations();
		detailContainer.updateTranslations();
		appointmentView.updateTranslations();
		menuBar.updateTranslations();
	}

	@Override
	public void addCalendarListener(Listener listener) {
		detailContainer.addCalendarListener(listener);
	}

	@Override
	public void updateCalenarEvents(
			BeanItemContainer<EventModel> resourceEvents,
			Date currentCalendarDate) {
		detailContainer
				.updateCalenarEvents(resourceEvents, currentCalendarDate);
	}

}
