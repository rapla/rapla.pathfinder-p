package com.pathfinder.presenter;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pathfinder.model.Attribute;
import com.pathfinder.model.AttributeKey;
import com.pathfinder.model.CalendarModel;
import com.pathfinder.model.Device;
import com.pathfinder.model.EventModel;
import com.pathfinder.model.FreeRoomModel;
import com.pathfinder.model.KeyboardModel;
import com.pathfinder.model.ResourceLink;
import com.pathfinder.model.ResourceModel;
import com.pathfinder.model.ResourceType;
import com.pathfinder.model.SessionLoggingModel;
import com.pathfinder.model.SessionLoggingModel.ClickOrigin;
import com.pathfinder.util.properties.ApplicationProperties;
import com.pathfinder.util.properties.ApplicationPropertiesSpec;
import com.pathfinder.util.properties.PropertiesKey;
import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.widgetset.BackToHomeScreenListenerSpec;
import com.pathfinder.util.widgetset.DateTime;
import com.pathfinder.view.AccordionView;
import com.pathfinder.view.AccordionViewSpec;
import com.pathfinder.view.CalendarEventComponent;
import com.pathfinder.view.DateTimeSpec;
import com.pathfinder.view.DetailEvents;
import com.pathfinder.view.DetailEventsSpec;
import com.pathfinder.view.DetailImage;
import com.pathfinder.view.DetailImageSpec;
import com.pathfinder.view.DetailInfo;
import com.pathfinder.view.DetailInfoSpec;
import com.pathfinder.view.EventSelectionView;
import com.pathfinder.view.EventSelectionViewSpec;
import com.pathfinder.view.FreeRoomView;
import com.pathfinder.view.FreeRoomViewSpec;
import com.pathfinder.view.Keyboard;
import com.pathfinder.view.KeyboardId;
import com.pathfinder.view.KeyboardSpec;
import com.pathfinder.view.MenuBar;
import com.pathfinder.view.MenuBarSpec;
import com.pathfinder.view.PersonInformationView;
import com.pathfinder.view.PersonInformationViewSpec;
import com.pathfinder.view.SearchField;
import com.pathfinder.view.SearchFieldSpec;
import com.pathfinder.view.TranslatableSpec;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.calendar.DateConstants;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component.Event;
import com.vaadin.ui.Component.Listener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.BackwardEvent;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.EventClick;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.EventClickHandler;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.ForwardEvent;

/**
 * The presenter for the desktop/stele view
 * 
 * @author alexh
 * 
 */
public class MainPresenter implements MainPresenterSpec,
		DataLoaderListenerSpec, TranslatableSpec {
	private static final Logger LOGGER = LogManager
			.getLogger(MainPresenter.class.getName());
	private final ApplicationPropertiesSpec properties = ApplicationProperties
			.getInstance();
	private final DataLoaderSpec dataLoader = DataLoader.getInstance();

	private final int goBackHomeIntervall = properties
			.getIntProperty(PropertiesKey.BACK_TO_HOME_TIMER);
	private long lastUserInteractionTimestamp;
	private boolean wentBackToHomeScreen = true;

	private final DateTimeSpec dateTime = new DateTime();
	private final FreeRoomViewSpec freeRoom = new FreeRoomView();
	private final AccordionViewSpec accordionView = new AccordionView();
	private final KeyboardSpec keyboardView = new Keyboard();
	private final SearchFieldSpec searchField = new SearchField();
	private final DetailInfoSpec detailInfo = new DetailInfo();
	private final DetailImageSpec detailImage = new DetailImage();
	private final DetailEventsSpec detailEvents = new DetailEvents();
	private final MenuBarSpec menuBar = new MenuBar();
	private final EventSelectionViewSpec eventSelectionView = new EventSelectionView();
	private final Image logo = new Image();
	private final PersonInformationViewSpec personInformationView = new PersonInformationView();

	private final VerticalLayout mainLayout = new VerticalLayout();
	private final VerticalLayout contentLayout = new VerticalLayout();
	private final VerticalLayout detailLayout = new VerticalLayout();
	private final VerticalLayout layoutNormal = new VerticalLayout();
	private final HorizontalLayout layoutWheelChair = new HorizontalLayout();

	private final KeyboardModel keyboardModel = new KeyboardModel();
	private ResourceModel resource = null;
	private BeanItemContainer<Attribute> resourceDetails = null;
	private BeanItemContainer<EventModel> resourceEvents = null;
	private CalendarModel calendarModel = new CalendarModel();
	private Device device;
	private SessionLoggingModel sessionLoggingModel;

	private Listener uiListener = null;

	private Timer freeRoomsTimer;

	private UI ui;

	public MainPresenter(UI ui) {

		this.ui = ui;

		// Register as DataListener to get notified if data changes
		dataLoader.addDataListener(this);
		this.setResourceData();
		this.buildLayout();
		this.addStyling();
		this.initBindings();
		this.initListeners();
		this.refreshFreeRooms();
		this.scheduleFreeRoomsLoading();
	}

	private void setResourceData() {
		accordionView.setRoomContainer(dataLoader.getRoomContainer());
		accordionView.setCourseContainer(dataLoader.getCourseContainer());
		accordionView.setPersonContainer(dataLoader.getPersonContainer());
		accordionView.setPoiContainer(dataLoader.getPoiContainer());
	}

	private void buildLayout() {
		// For the wheel chair button / view
		this.layoutNormal.addComponent(accordionView);
		this.layoutNormal.addComponent(searchField);
		this.layoutNormal.addComponent(keyboardView);
		this.layoutNormal.setSizeFull();

		ThemeResource res = new ThemeResource("img/logo.png");
		logo.setSource(res);
		logo.setPrimaryStyleName("logo-dhbw");
		logo.setHeight(11, Unit.EM);
		this.contentLayout.addComponent(logo);
		this.contentLayout.addComponent(freeRoom);
		this.contentLayout.addComponent(layoutNormal);
		this.contentLayout.setSizeFull();

		this.detailLayout.addComponent(detailImage);
		this.detailLayout.addComponent(detailEvents);
		this.detailLayout.addComponent(detailInfo);
		this.detailImage.setSizeFull();
		this.detailEvents.setSizeFull();
		this.detailInfo.setSizeFull();
		this.detailLayout.setSizeFull();

		this.mainLayout.addComponent(dateTime);
		this.mainLayout.addComponent(contentLayout);
		this.mainLayout.addComponent(detailLayout);
		this.detailLayout.setVisible(false);
		this.mainLayout.addComponent(menuBar);
		// TODO to work, you have to set all other components to sizeUndefined
		// this.mainLayout.setExpandRatio(contentLayout, 1);
	}

	private void addStyling() {
		this.mainLayout.setPrimaryStyleName("stele");
	}

	BeanFieldGroup<KeyboardModel> keyboardBinder;

	private void initBindings() {
		keyboardBinder = new BeanFieldGroup<KeyboardModel>(KeyboardModel.class);
		keyboardBinder.setItemDataSource(keyboardModel);
		keyboardBinder.bind(searchField.getSearchField(),
				KeyboardModel.PROPERTY_SEARCHSTRING);
		keyboardBinder.setBuffered(false);
	}

	private void initListeners() {
		this.accordionView
				.addAccordionTableItemClickListener(new ResourcesClickListener());
		this.freeRoom.addTableItemClickListener(new ResourcesClickListener());
		searchField
				.addSearchFieldTextChangeListener(new SearchFieldTextChangeListener());
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
		this.detailEvents.addCalendarListener(new CalendarListener());
		this.detailEvents.setEventClickHandler(new CalendarEventClickHandler());
		this.keyboardView
				.addKeyboardButtonListener(new KeyboardButtonClickListener());
		this.eventSelectionView
				.addButtonClickListener(new EventResourceSelectedListener());
		this.detailInfo
				.addInfoTableItemClickListener(new ResourcesClickListener());
	}

	@Override
	public void dataUpdated() {
		this.setResourceData();
	}

	class ResourcesClickListener implements ItemClickListener {

		@Override
		public void itemClick(ItemClickEvent event) {

			if (event.getItemId() instanceof ResourceModel) {
				// Resource in accordion was clicked
				resource = (ResourceModel) event.getItemId();
				sessionLoggingModel.sendLoggingInfoToRapla(resource,
						ClickOrigin.SEARCH_RESULTS, getSearchString());
			} else if (event.getItemId() instanceof FreeRoomModel) {
				// Resource in free rooms table was clicked
				FreeRoomModel freeResource = (FreeRoomModel) event.getItemId();
				resource = new ResourceModel();

				resource.setId(freeResource.getId());
				resource.setName(freeResource.getName());
				resource.setType(ResourceType.ROOM.toString());
				sessionLoggingModel.sendLoggingInfoToRapla(resource,
						ClickOrigin.FREE_ROOMS, "");
			} else if (event.getItemId() instanceof Attribute) {
				// Resource in detail view was clicked
				boolean showDetailView = doAttributeAction((Attribute) event
						.getItemId());
				if (!showDetailView)
					return;

				sessionLoggingModel.sendLoggingInfoToRapla(resource,
						ClickOrigin.DETAILS, "");
			}

			prepareDetailView();
		}

		private boolean doAttributeAction(Attribute attribute) {
			boolean showDetailView = false;

			switch (attribute.getKey()) {
			case RESOURCE_LINK:
				if (attribute instanceof ResourceLink) {
					resource = ((ResourceLink) attribute).getResourceModel();
					showDetailView = true;
				}
				break;
			case INFO_KEY:
				personInformationView.showInformation(attribute.getPerson(),
						attribute.getInformation());
				break;
			default:
			}

			return showDetailView;
		}
	}

	class SearchFieldTextChangeListener implements TextChangeListener {
		@Override
		public void textChange(TextChangeEvent event) {
			setSearchString(event.getText());
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

		private boolean isTimeToGoHome() {
			boolean result = false;

			if (!wentBackToHomeScreen && device.isStele()) {
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
	}

	class CalendarListener implements Listener {

		@Override
		public void componentEvent(Event event) {
			long sevenDays = 7 * DateConstants.DAYINMILLIS;
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
				setPositionCounter(getPositionCounter() - 1);
				break;
			case RIGHT:
				setPositionCounter(getPositionCounter() + 1);
				break;
			default:
				addKeybordKeyToSearchString(id.getLabel());
				break;
			}

		}

	}

	class CalendarEventClickHandler implements EventClickHandler {

		@Override
		public void eventClick(EventClick event) {
			if (event.getCalendarEvent() instanceof CalendarEventComponent) {
				CalendarEventComponent calendarEventComponent = (CalendarEventComponent) (event
						.getCalendarEvent());

				if (calendarEventComponent.getEventModel().getResources()
						.size() > 0) {
					eventSelectionView
							.showEventResourceSelection(calendarEventComponent
									.getEventModel().getResources());
				}
			}
		}
	}

	class EventResourceSelectedListener implements ClickListener {

		@Override
		public void buttonClick(ClickEvent event) {
			if (event.getButton().getData() instanceof ResourceModel) {
				resource = (ResourceModel) event.getButton().getData();
				sessionLoggingModel.sendLoggingInfoToRapla(resource,
						ClickOrigin.CALENDAR, "");
				prepareDetailView();
			}
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

	private void prepareDetailView() {
		if (resource != null) {
			resourceDetails = dataLoader.getResourceDetails(resource.getId(),
					ui.getLocale());

			if (resourceDetails != null) {
				calendarModel.setBeginningOfCurrentDay(new Date());

				updateCalendarEvents();

				LOGGER.trace(resource.getType() + " element was clicked: "
						+ resource.getName());
				switchToDetailView();
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
					firstDayOfWeek, lastDayOfWeek, ui.getLocale());

			detailEvents.setEvents(resourceEvents, firstDayOfWeek,
					lastDayOfWeek);
		}
	}

	private void scheduleFreeRoomsLoading() {
		// Starts after specified interval and repeats in the same interval (see
		// application.properties)
		long loadInterval = properties
				.getIntProperty(PropertiesKey.DATA_LOAD_INTERVAL_FREE_ROOMS);
		freeRoomsTimer = new Timer(true);
		freeRoomsTimer.schedule(getTimerTask(), loadInterval, loadInterval);
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

	private void goBackToHomeScreenAndRestoreDefaultSettings() {
		eventSelectionView.close();
		personInformationView.close();
		switchToSearchView();
		clearSearchString();
		languageChanged(VaadinSession.getCurrent().getLocale());
	}

	@Override
	public void switchToSearchView() {
		// Null setting
		this.resource = null;
		this.resourceDetails = null;

		// Hiding
		this.detailLayout.setVisible(false);
		detailInfo.removeDetails();
		detailImage.removeImage();
		this.resource = null;

		// Adapting MenuBar
		menuBar.replaceHomeButtonWithWheelChairButton();

		// Showing
		freeRoom.showFreeRoomView();
		accordionView.showAccordionView();
		searchField.showSearchField();
		keyboardView.showKeyboard();
	}

	@Override
	public void switchToDetailView() {
		// Hiding
		freeRoom.hideFreeRoomView();
		accordionView.hideAccordionView();
		searchField.hideSearchField();
		keyboardView.hideKeyboard();
		logo.setPrimaryStyleName("logo-dhbw");

		// Adapting MenuBar
		menuBar.replaceWheelChairButtonWithHomeButton();

		// Showing
		detailInfo.removeDetails();
		detailInfo.addDetails(resourceDetails, resource.getType());
		detailImage.removeImage();
		setImage(resourceDetails, resource.getName());
		this.detailLayout.setVisible(true);
	}

	private void setImage(BeanItemContainer<Attribute> resourceDetails,
			String resourceName) {
		for (Attribute attribute : resourceDetails.getItemIds()) {
			if (attribute.getKey() == AttributeKey.LOCATION) {
				detailImage.setImage(device.getUrlPicturePrefix(),
						resourceName, attribute.getValue());
			} else if (attribute.getKey() == AttributeKey.RESOURCE_LINK) {
				if (attribute instanceof ResourceLink) {
					ResourceModel resourceModel = ((ResourceLink) attribute)
							.getResourceModel();
					setImage(dataLoader.getResourceDetails(
							resourceModel.getId(), ui.getLocale()),
							resourceModel.getName());
					break;
				}
			}
		}
	}

	@Override
	public void changeToWheelChairView() {
		if (contentLayout.getComponentIndex(layoutNormal) >= 0) {
			VerticalLayout rightSide = new VerticalLayout();
			VerticalLayout leftSide = new VerticalLayout();
			leftSide.setPrimaryStyleName("leftWheel");
			rightSide.setPrimaryStyleName("rightWheel");
			layoutWheelChair.setPrimaryStyleName("wheelchair");
			rightSide.addComponent(accordionView);
			rightSide.addComponent(searchField);

			freeRoom.removeStyleName("freeroom");
			freeRoom.setPrimaryStyleName("wheelchair-freeroom");

			logo.setPrimaryStyleName("logo");
			logo.setHeight(35, Unit.EM);

			leftSide.addComponent(searchField);
			leftSide.addComponent(keyboardView);
			layoutWheelChair.addComponent(leftSide);
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
			layoutNormal.addComponent(keyboardView);

			freeRoom.removeStyleName("wheelchair-freeroom");
			freeRoom.setPrimaryStyleName("freeroom");
			logo.setPrimaryStyleName("logo-dhbw");

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
		int oldCursorPosition = getPositionCounter();

		StringBuilder newSearchString = new StringBuilder(getSearchString());

		if (oldCursorPosition < newSearchString.length()
				&& oldCursorPosition >= 0) {
			newSearchString.insert(oldCursorPosition, key);
		} else {
			newSearchString.append(key);
		}
		setSearchString(newSearchString.toString());
		setPositionCounter(oldCursorPosition + 1);
	}

	public void deleteKeyFromSearchString() {
		int oldCursorPosition = getPositionCounter();

		StringBuilder newSearchString = new StringBuilder(getSearchString());

		if (oldCursorPosition > 0
				&& oldCursorPosition <= newSearchString.length()) {
			newSearchString.deleteCharAt(oldCursorPosition - 1);

			setSearchString(newSearchString.toString());
			setPositionCounter(oldCursorPosition - 1);
		} else {
			searchField.focusSearchField();
		}
	}

	public int getPositionCounter() {
		return searchField.getCursorPosition();
	}

	public void setPositionCounter(int cursorPosition) {
		if (cursorPosition >= 0 && cursorPosition <= getSearchString().length())
			searchField.setCursorPosition(cursorPosition);
	}

	@Override
	public int getCursorPosition() {
		return searchField.getCursorPosition();
	}

	public void clearSearchString() {
		this.setSearchString("");
	}

	@Override
	public String getSearchString() {
		return keyboardModel.getSearchString();
	}

	@Override
	public void setSearchString(String value) {
		searchField.setSearchFieldValue(value);
		accordionView.useFiltersForAllTables(getSearchString());
		searchField.focusSearchField();
		LOGGER.trace("SearchString: " + this.getSearchString());
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
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		return cal.getTime();
	}

	@Override
	public void languageChanged(Locale locale) {
		if (!ui.getLocale().equals(locale)) {
			ui.setLocale(locale);
			this.updateTranslations();
			Page.getCurrent().setTitle(
					Translator.getInstance().translate(
							TranslationKeys.APP_TITLE));
		}
	}

	@Override
	public void updateTranslations() {
		dateTime.updateTranslations();
		freeRoom.updateTranslations();
		accordionView.updateTranslations();
		searchField.updateTranslations();
		keyboardView.updateTranslations();
		menuBar.updateTranslations();

		if (resource != null) {
			detailInfo.removeDetails();
			detailInfo.addDetails(
					dataLoader.getResourceDetails(resource.getId(),
							ui.getLocale()), resource.getType());
		}
		detailEvents.updateTranslations();
		eventSelectionView.updateTranslations();
		personInformationView.updateTranslations();
	}

	@Override
	public boolean isTimeToGetRemoved() {
		boolean result = false;

		long tenMinutesAgo = new Date().getTime() - 10
				* DateConstants.MINUTEINMILLIS;

		long lastHeartbeat = ui.getLastHeartbeatTimestamp();

		LOGGER.info("Last heartbeat: " + new Date(lastHeartbeat).toString());
		LOGGER.info("UI: " + ui);

		if (lastHeartbeat < tenMinutesAgo)
			result = true;

		return result;
	}

	@Override
	public AbstractLayout getSteleLayoutView() {
		return mainLayout;
	}

	@Override
	public void setDevice(Device steleLocation) {
		if (steleLocation != null) {
			this.device = steleLocation;
			this.detailInfo.setDevice(steleLocation);
		}
	}

	@Override
	public void destroy() {
		freeRoomsTimer.cancel();
		dateTime.doCleanup();
		freeRoom.doCleanup();
		accordionView.doCleanup();
		keyboardView.doCleanup();
		searchField.doCleanup();
		detailInfo.doCleanup();
		detailImage.doCleanup();
		detailEvents.doCleanup();
		menuBar.doCleanup();
		eventSelectionView.doCleanup();
		personInformationView.doCleanup();
	}

	public void setSessionLoggingModel(SessionLoggingModel sessionLoggingModel) {
		this.sessionLoggingModel = sessionLoggingModel;
	}
}
