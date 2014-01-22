package com.pathfinder.presenter;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pathfinder.model.Attribut;
import com.pathfinder.model.CalendarModel;
import com.pathfinder.model.EventModel;
import com.pathfinder.model.FreeRoomModel;
import com.pathfinder.model.KeyboardModel;
import com.pathfinder.model.ResourceModel;
import com.pathfinder.model.ResourceModel.ResourceType;
import com.pathfinder.util.properties.ApplicationProperties;
import com.pathfinder.util.properties.ApplicationPropertiesSpec;
import com.pathfinder.util.properties.PropertiesKey;
import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.widgetset.BackToHomeScreenListenerSpec;
import com.pathfinder.util.widgetset.DateTime;
import com.pathfinder.view.AccordionView;
import com.pathfinder.view.AccordionViewSpec;
import com.pathfinder.view.DateTimeSpec;
import com.pathfinder.view.DetailContainer;
import com.pathfinder.view.DetailContainerSpec;
import com.pathfinder.view.FreeRoomView;
import com.pathfinder.view.FreeRoomViewSpec;
import com.pathfinder.view.Keyboard;
import com.pathfinder.view.KeyboardId;
import com.pathfinder.view.KeyboardSpec;
import com.pathfinder.view.MenuBar;
import com.pathfinder.view.MenuBarSpec;
import com.pathfinder.view.SearchField;
import com.pathfinder.view.SearchFieldSpec;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component.Event;
import com.vaadin.ui.Component.Listener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.BackwardEvent;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.ForwardEvent;

/**
 * The presenter for the desktop/stele view
 * 
 * @author alexh
 * 
 */
public class StelePresenter implements StelePresenterSpec,
		DataLoaderListenerSpec {
	private static final Logger LOGGER = LogManager
			.getLogger(StelePresenter.class.getName());
	private final ApplicationPropertiesSpec properties = ApplicationProperties
			.getInstance();
	private final DataLoaderSpec dataLoader = DataLoader.getInstance();

	private final DateTimeSpec dateTime = new DateTime();
	private final FreeRoomViewSpec freeRoom = new FreeRoomView();
	private final AccordionViewSpec accordionView = new AccordionView();
	private final KeyboardSpec keyboard = new Keyboard();
	private final SearchFieldSpec searchField = new SearchField();
	private final MenuBarSpec menuBar = new MenuBar();
	private final DetailContainerSpec detailContainer = new DetailContainer();

	private CustomComponent component = new CustomComponent();
	private final VerticalLayout mainLayout = new VerticalLayout();
	private final VerticalLayout contentLayout = new VerticalLayout();
	private final VerticalLayout layoutNormal = new VerticalLayout();
	private final HorizontalLayout layoutWheelChair = new HorizontalLayout();

	private final BeanFieldGroup<KeyboardModel> keyboardBinder = new BeanFieldGroup<KeyboardModel>(
			KeyboardModel.class);

	private final int goBackHomeIntervall = properties
			.getIntProperty(PropertiesKey.BACK_TO_HOME_TIMER);

	private ResourceModel resource = null;
	private BeanItemContainer<Attribut> resourceDetails = null;
	private BeanItemContainer<EventModel> resourceEvents;

	private long lastUserInteractionTimestamp;
	private boolean wentBackToHomeScreen = true;

	private CalendarModel calendarModel = new CalendarModel();

	private Listener uiListener = null;

	public StelePresenter() {
		// Register as DataListener to get notified if data changes
		dataLoader.addDataListener(this);
		this.setResourceData();
		this.buildLayout();
		this.initListeners();
		this.refreshFreeRooms();
		this.scheduleFreeRoomsLoading();
	}

	private void setResourceData() {
		this.setRoomContainer(dataLoader.getRoomContainer());
		this.setCourseContainer(dataLoader.getCourseContainer());
		this.setPersonContainer(dataLoader.getPersonContainer());
		this.setPoiContainer(dataLoader.getPoiContainer());
	}

	private void buildLayout() {
		// For the wheel chair button / view
		this.layoutNormal.addComponent(accordionView);
		this.layoutNormal.addComponent(searchField);
		this.layoutNormal.addComponent(keyboard);

		this.contentLayout.addComponent(freeRoom);
		this.contentLayout.addComponent(layoutNormal);
		this.contentLayout.addComponent(detailContainer);
		this.contentLayout.setSizeFull();
		// this.contentLayout.setHeight(900, Unit.PIXELS);

		this.mainLayout.addComponent(dateTime);
		this.mainLayout.addComponent(contentLayout);
		this.mainLayout.addComponent(menuBar);
		this.mainLayout.setExpandRatio(contentLayout, 1);

		// TODO Maybe an own CustomComponent?
		this.mainLayout.setPrimaryStyleName("stele");
	}

	private void initListeners() {
		this.keyboardBinder.setBuffered(false);
		this.keyboardBinder.setItemDataSource(new KeyboardModel());
		this.keyboardBinder.bind(searchField.getSearchField(),
				KeyboardModel.PROPERTY_SEARCHSTRING);

		// TODO addItemClickListener change to addAccordionItemClickListener
		this.accordionView.addItemClickListener(new ResourcesClickListener());

		this.freeRoom.addTableItemClickListener(new ResourcesClickListener());

		// TODO: Mit Keyboard in die Suche schreiben
		// this.desktopLayout
		// .addSearchFieldTextChangeListener(new
		// SearchFieldTextChangeListener());

		this.searchField
				.addDeleteAllClickListener(new DeleteAllClickListener());

		this.menuBar.addClickListenerHomeButton(new HomeButtonClickListener());
		this.menuBar
				.addClickListenerWheelChairButton(new WheelChairButtonClickListener());

		for (Locale locale : Translator.getInstance().getSupportedLocales()) {
			this.menuBar.addClickListenerFlagPopup(locale,
					new FlagImageClickListener(locale));
		}

		this.dateTime.addBackToHomeListener(new BackToHomeListener());
		this.detailContainer.addCalendarListener(new CalendarListener());
		this.keyboard
				.addKeyboardButtonListener(new KeyboardButtonClickListener());
	}

	@Override
	public void dataUpdated() {
		this.setResourceData();
	}

	class ResourcesClickListener implements ItemClickListener {
		@Override
		public void itemClick(ItemClickEvent event) {

			if (event.getItemId() instanceof ResourceModel)
				resource = (ResourceModel) event.getItemId();
			if (event.getItemId() instanceof FreeRoomModel) {
				FreeRoomModel freeResource = (FreeRoomModel) event.getItemId();
				resource = new ResourceModel();

				resource.setId(freeResource.getId());
				resource.setName(freeResource.getName());
				resource.setType(ResourceType.ROOM.toString());
			}

			resourceDetails = dataLoader.getResourceDetails(resource.getId(),
					UI.getCurrent().getLocale());

			calendarModel.setBeginningOfCurrentDay(new Date());

			updateCalendarEvents();

			LOGGER.trace(resource.getType() + " element was clicked: "
					+ resource.getName());
			switchToDetailView();
		}
	}

	class SearchFieldTextChangeListener implements TextChangeListener {
		@Override
		public void textChange(TextChangeEvent event) {
			LOGGER.trace("SearchString: " + getSearchString());
			accordionView.useFiltersForAllTables(getSearchString());
			searchField.focusSearchField();
		}
	}

	class SearchFieldValueChangeListener implements ValueChangeListener {
		@Override
		public void valueChange(ValueChangeEvent event) {
			LOGGER.trace("SearchString: " + getSearchString());
			accordionView.useFiltersForAllTables(getSearchString());
			searchField.focusSearchField();
		}
	}

	class DeleteAllClickListener implements ClickListener {
		@Override
		public void buttonClick(ClickEvent event) {
			clearSearchString();
		}
	}

	class HomeButtonClickListener implements ClickListener {
		@Override
		public void buttonClick(ClickEvent event) {
			changeToNonWheelChairView();
			switchToSearchView();
		}
	}

	class WheelChairButtonClickListener implements ClickListener {
		@Override
		public void buttonClick(ClickEvent event) {
			changeToWheelChairView();
		}
	}

	class RespawnSteleLayoutTimer extends TimerTask {
		@Override
		public void run() {
			switchToSearchView();
		}
	}

	class FlagImageClickListener implements
			com.vaadin.event.MouseEvents.ClickListener {

		private Locale locale;

		public FlagImageClickListener(Locale locale) {
			this.locale = locale;
		}

		@Override
		public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
			// If clicked language different than current language, then update
			// translations
			menuBar.hideOpenLanguagePopup();
			languageChanged(locale);
		}
	}

	class BackToHomeListener implements BackToHomeScreenListenerSpec {

		@Override
		public void timeToGoHome() {
			if (isTimeToGoHome()) {
				LOGGER.trace("Restoring default Search View because user interaction timer expired");
				goBackToHomeScreenAndRestoreDefaultSettings();
			}
		}

	}

	class CalendarListener implements Listener {

		@Override
		public void componentEvent(Event event) {

			long sevenDays = 7 * 24 * 60 * 60 * 1000;
			if (event instanceof ForwardEvent) {
				long calendarDayMinusOne = calendarModel
						.getBeginningOfCurrentDay().getTime() + sevenDays;
				calendarModel.setBeginningOfCurrentDay(new Date(
						calendarDayMinusOne));
				updateCalendarEvents();
			} else if (event instanceof BackwardEvent) {
				long calendarDayPlusOne = calendarModel
						.getBeginningOfCurrentDay().getTime() - sevenDays;
				calendarModel.setBeginningOfCurrentDay(new Date(
						calendarDayPlusOne));
				updateCalendarEvents();
			}
		}

	}

	class KeyboardButtonClickListener implements ClickListener {

		@Override
		public void buttonClick(ClickEvent event) {

			KeyboardId id = (KeyboardId) event.getButton().getData();
			switch (id) {
			case DELETE:
				deleteKeyFromSearchString();
				break;
			case SPACE:
				addKeybordKeyToSearchString(" ");
				break;
			case LEFT:
				setChangePosCounter(getChangePosCounter() - 1);
				break;
			case RIGHT:
				setChangePosCounter(getChangePosCounter() + 1);
				break;
			default:
				addKeybordKeyToSearchString(id.getLabel());
				break;
			}

		}

	}

	private void updateCalendarEvents() {

		if (resource != null) {

			Date firstDayOfWeek = getFirstDayOfWeek(calendarModel
					.getBeginningOfCurrentDay());
			Date lastDayOfWeek = getLastDayOfWeek(calendarModel
					.getEndOfCurrentDay());

			resourceEvents = dataLoader.getEvent(resource.getId(),
					firstDayOfWeek, lastDayOfWeek, UI.getCurrent().getLocale());

			detailContainer.updateCalendarEvents(resourceEvents,
					firstDayOfWeek, lastDayOfWeek);
		}
	}

	@Override
	public Listener getUiListener() {
		if (uiListener == null) {

			uiListener = new Listener() {

				@Override
				public void componentEvent(Event event) {
					if (event instanceof ClickEvent
							|| event instanceof com.vaadin.event.MouseEvents.ClickEvent
							|| event instanceof ForwardEvent
							|| event instanceof BackwardEvent) {
						lastUserInteractionTimestamp = new Date().getTime();
						wentBackToHomeScreen = false;
						if (event.getComponent() != null)
							LOGGER.trace("There was an user interaction; caption: "
									+ event.getComponent().getCaption()
									+ "; primary style name: "
									+ event.getComponent()
											.getPrimaryStyleName());
					}
				}
			};
		}
		return uiListener;
	}

	private TimerTask getTimerTask() {
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				refreshFreeRooms();
			}
		};
		return timerTask;
	}

	private void scheduleFreeRoomsLoading() {

		// Starts after specified interval and repeats in the same interval (see
		// application.properties)
		long loadInterval = properties
				.getIntProperty(PropertiesKey.DATA_LOAD_INTERVAL_FREE_ROOMS);
		new Timer().schedule(getTimerTask(), loadInterval, loadInterval);
	}

	private void goBackToHomeScreenAndRestoreDefaultSettings() {
		switchToSearchView();
		clearSearchString();
		languageChanged(VaadinSession.getCurrent().getLocale());
	}

	private boolean isTimeToGoHome() {
		boolean result = false;

		if (!wentBackToHomeScreen) {
			long millisecondsSinceLastRequest = new Date().getTime()
					- lastUserInteractionTimestamp;

			if (millisecondsSinceLastRequest >= goBackHomeIntervall) {
				lastUserInteractionTimestamp = new Date().getTime();
				result = true;
				wentBackToHomeScreen = true;
			}
		}

		return result;
	}

	@Override
	public void switchToSearchView() {
		// Null setting
		this.resource = null;
		this.resourceDetails = null;

		// Hiding
		detailContainer.hideDetailContainer();
		detailContainer.removeDetails();

		// Adapting MenuBar
		menuBar.replaceHomeButtonWithWheelChairButton();

		// Showing
		freeRoom.showFreeRoomView();
		accordionView.showAccordionView();
		searchField.showSearchField();
		keyboard.showKeyboard();
	}

	@Override
	public void switchToDetailView() {
		// Hiding
		freeRoom.hideFreeRoomView();
		accordionView.hideAccordionView();
		searchField.hideSearchField();
		keyboard.hideKeyboard();

		// Adapting MenuBar
		menuBar.replaceWheelChairButtonWithHomeButton();

		// Showing
		// for (Attribut attribut : resourceDetails.getItemIds()) {
		// if ("resourcueurl".equals(attribut.getKey())) {
		// }

		detailContainer.removeDetails();
		detailContainer.addDetails(resource, resourceDetails);
		detailContainer.showDetailContainer();
	}

	@Override
	public void changeToWheelChairView() {
		if (contentLayout.getComponentIndex(layoutNormal) >= 0) {
			VerticalLayout rightSide = new VerticalLayout();
			rightSide.addComponent(accordionView);
			rightSide.addComponent(searchField);

			layoutWheelChair.addComponent(keyboard);
			layoutWheelChair.addComponent(rightSide);
			layoutWheelChair.setSizeFull();
			this.contentLayout.replaceComponent(layoutNormal, layoutWheelChair);
			this.layoutNormal.removeAllComponents();
		}

		menuBar.replaceWheelChairButtonWithHomeButton();
	}

	@Override
	public void changeToNonWheelChairView() {
		if (contentLayout.getComponentIndex(layoutWheelChair) >= 0) {
			layoutNormal.addComponent(accordionView);
			layoutNormal.addComponent(searchField);
			layoutNormal.addComponent(keyboard);
			layoutNormal.setSizeFull();
			this.contentLayout.replaceComponent(layoutWheelChair, layoutNormal);
			layoutWheelChair.removeAllComponents();
		}

		menuBar.replaceHomeButtonWithWheelChairButton();
	}

	public synchronized void refreshFreeRooms() {
		freeRoom.refreshFreeRooms(dataLoader.getFreeResources());
	}

	public void addKeybordKeyToSearchString(String key) {
		int oldCursorPosition = getChangePosCounter();

		StringBuilder newSearchString = new StringBuilder(getSearchString());

		if (oldCursorPosition < newSearchString.length()
				&& oldCursorPosition >= 0) {
			newSearchString.insert(oldCursorPosition, key);
		} else {
			newSearchString.append(key);
		}
		setSearchString(newSearchString.toString());

		// TODO Remove if all works
		searchField.focusSearchField();
		setChangePosCounter(oldCursorPosition + 1);
	}

	public void deleteKeyFromSearchString() {
		int oldCursorPosition = getChangePosCounter();

		StringBuilder newSearchString = new StringBuilder(getSearchString());

		if (oldCursorPosition > 0
				&& oldCursorPosition <= newSearchString.length()) {
			newSearchString.deleteCharAt(oldCursorPosition - 1);

			setSearchString(newSearchString.toString());

			// TODO Remove if all works
			searchField.focusSearchField();
			setChangePosCounter(oldCursorPosition - 1);
		} else {
			// TODO Remove if all works
			searchField.focusSearchField();
		}
	}

	public void clearSearchString() {
		this.setSearchString("");
	}

	public int getChangePosCounter() {
		return searchField.getCursorPosition();
	}

	public void setChangePosCounter(int cursorPosition) {
		if (cursorPosition >= 0 && cursorPosition <= getSearchString().length())
			searchField.setCursorPosition(cursorPosition);
	}

	@Override
	public int getCursorPosition() {
		return searchField.getCursorPosition();
	}

	@Override
	public String getSearchString() {
		String returnString = (String) keyboardBinder.getItemDataSource()
				.getItemProperty(KeyboardModel.PROPERTY_SEARCHSTRING)
				.getValue();
		if (returnString == null)
			returnString = "";
		return returnString;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setSearchString(String value) {
		keyboardBinder.getItemDataSource()
				.getItemProperty(KeyboardModel.PROPERTY_SEARCHSTRING)
				.setValue(value);
		// TODO Remove if all works
		accordionView.useFiltersForAllTables(getSearchString());
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
	public void languageChanged(Locale locale) {
		if (!UI.getCurrent().getLocale().equals(locale)) {
			UI.getCurrent().setLocale(locale);
			this.updateTranslations();
			Page.getCurrent().setTitle(
					Translator.getInstance().translate(
							TranslationKeys.APP_TITLE));
		}
	}

	@Override
	public AbstractLayout getSteleLayoutView() {
		return mainLayout;
	}

	@Override
	public boolean isTimeToGetRemoved() {

		boolean result = false;

		long tenMinutesAgo = new Date().getTime() - 10 * 60 * 1000;

		long lastHeartbeat = UI.getCurrent().getLastHeartbeatTimestamp();

		if (lastHeartbeat < tenMinutesAgo)
			result = true;

		return result;
	}

	private Date getFirstDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		return cal.getTime();
	}

	private Date getLastDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return cal.getTime();
	}

	// TODO @Override
	public void updateTranslations() {
		dateTime.updateTranslations();
		freeRoom.updateTranslations();
		accordionView.updateTranslations();
		searchField.updateTranslations();
		keyboard.updateTranslations();
		detailContainer.updateTranslations();
		menuBar.updateTranslations();
	}
}
