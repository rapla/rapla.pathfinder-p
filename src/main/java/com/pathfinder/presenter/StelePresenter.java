package com.pathfinder.presenter;

import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pathfinder.model.Attribut;
import com.pathfinder.model.CalendarModel;
import com.pathfinder.model.EventModel;
import com.pathfinder.model.KeyboardModel;
import com.pathfinder.model.ResourceModel;
import com.pathfinder.util.properties.ApplicationProperties;
import com.pathfinder.util.properties.ApplicationPropertiesSpec;
import com.pathfinder.util.properties.PropertiesKey;
import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.widgetset.BackToHomeScreenListenerSpec;
import com.pathfinder.view.components.KeyboardId;
import com.pathfinder.view.layout.SteleLayout;
import com.pathfinder.view.layout.SteleLayoutSpec;
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
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component.Event;
import com.vaadin.ui.Component.Listener;
import com.vaadin.ui.UI;
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
	private final BeanFieldGroup<KeyboardModel> keyboardBinder = new BeanFieldGroup<KeyboardModel>(
			KeyboardModel.class);

	private final SteleLayoutSpec steleLayout = new SteleLayout();

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

	private void initListeners() {
		this.keyboardBinder.setBuffered(false);
		this.keyboardBinder.setItemDataSource(new KeyboardModel());
		this.keyboardBinder.bind(steleLayout.getSearchField(),
				KeyboardModel.PROPERTY_SEARCHSTRING);
		this.steleLayout.addItemClickListener(new TableDetailClickListener());
		// TODO
		// this.desktopLayout
		// .addSearchFieldTextChangeListener(new
		// SearchFieldTextChangeListener());
		this.steleLayout
				.addDeleteAllClickListener(new DeleteAllClickListener());
		steleLayout.addClickListenerHomeButton(new HomeButtonClickListener());
		steleLayout
				.addClickListenerWheelChairButton(new WheelChairButtonClickListener());

		for (Locale locale : Translator.getInstance().getSupportedLocales()) {
			steleLayout.addClickListenerFlagPopup(locale,
					new FlagImageClickListener(locale));
		}

		steleLayout.addBackToHomeListener(new BackToHomeListener());
		steleLayout.addCalendarListener(new CalendarListener());
		steleLayout
				.addKeyboardButtonListener(new KeyboardButtonClickListener());
	}

	@Override
	public void dataUpdated() {
		this.setResourceData();
	}

	class TableDetailClickListener implements ItemClickListener {
		@Override
		public void itemClick(ItemClickEvent event) {
			resource = (ResourceModel) event.getItemId();
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
			steleLayout.useFiltersForAllTables(getSearchString());
			// TODO Is this necessary?
			steleLayout.focusSearchField();
		}
	}

	class SearchFieldValueChangeListener implements ValueChangeListener {
		@Override
		public void valueChange(ValueChangeEvent event) {
			LOGGER.trace("SearchString: " + getSearchString());
			steleLayout.useFiltersForAllTables(getSearchString());
			// TODO Is this necessary?
			// desktopLayout.focusSearchField();
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
			steleLayout.hideOpenLanguagePopup();
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

			long oneDay = 24 * 60 * 60 * 1000;
			if (event instanceof ForwardEvent) {
				long calendarDayMinusOne = calendarModel
						.getBeginningOfCurrentDay().getTime() + oneDay;
				calendarModel.setBeginningOfCurrentDay(new Date(
						calendarDayMinusOne));
				updateCalendarEvents();
			} else if (event instanceof BackwardEvent) {
				long calendarDayPlusOne = calendarModel
						.getBeginningOfCurrentDay().getTime() - oneDay;
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

			resourceEvents = dataLoader.getEvent(resource.getId(),
					calendarModel.getBeginningOfCurrentDay(), calendarModel
							.getEndOfCurrentDay(), UI.getCurrent().getLocale());

			steleLayout.updateCalenarEvents(resourceEvents,
					calendarModel.getBeginningOfCurrentDay());
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
		steleLayout.hideDetailContainer();
		steleLayout.removeDetails();

		// Adapting MenuBar
		steleLayout.replaceHomeButtonWithWheelChairButton();

		// Showing
		steleLayout.showFreeRoomView();
		steleLayout.showAccordionView();
		steleLayout.showSearchField();
		steleLayout.showKeyboard();
	}

	@Override
	public void switchToDetailView() {
		// Hiding
		steleLayout.hideFreeRoomView();
		steleLayout.hideAccordionView();
		steleLayout.hideSearchField();
		steleLayout.hideKeyboard();

		// Adapting MenuBar
		steleLayout.replaceWheelChairButtonWithHomeButton();

		// Showing
		// for (Attribut attribut : resourceDetails.getItemIds()) {
		// if ("resourcueurl".equals(attribut.getKey())) {
		// }
		steleLayout.removeDetails();
		steleLayout.addDetails(resource, resourceDetails);
		steleLayout.showDetailContainer();
	}

	@Override
	public void changeToWheelChairView() {
		steleLayout.changeToWheelChairView();
		steleLayout.replaceWheelChairButtonWithHomeButton();
	}

	@Override
	public void changeToNonWheelChairView() {
		steleLayout.changeToNonWheelChairView();
		steleLayout.replaceHomeButtonWithWheelChairButton();
	}

	public synchronized void refreshFreeRooms() {
		steleLayout.refreshFreeRooms(dataLoader.getFreeResources());
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
		steleLayout.focusSearchField();
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
			steleLayout.focusSearchField();
			setChangePosCounter(oldCursorPosition - 1);
		} else {
			// TODO Remove if all works
			steleLayout.focusSearchField();
		}
	}

	public void clearSearchString() {
		this.setSearchString("");
	}

	public int getChangePosCounter() {
		return steleLayout.getCursorPosition();
	}

	public void setChangePosCounter(int cursorPosition) {
		if (cursorPosition >= 0 && cursorPosition <= getSearchString().length())
			steleLayout.setCursorPosition(cursorPosition);
	}

	@Override
	public int getCursorPosition() {
		return steleLayout.getCursorPosition();
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
		steleLayout.useFiltersForAllTables(getSearchString());
	}

	@Override
	public void setRoomContainer(
			BeanItemContainer<ResourceModel> beanItemContainer) {
		steleLayout.setRoomContainer(beanItemContainer);
	}

	@Override
	public void setCourseContainer(
			BeanItemContainer<ResourceModel> beanItemContainer) {
		steleLayout.setCourseContainer(beanItemContainer);
	}

	@Override
	public void setPersonContainer(
			BeanItemContainer<ResourceModel> beanItemContainer) {
		steleLayout.setPersonContainer(beanItemContainer);
	}

	@Override
	public void setPoiContainer(
			BeanItemContainer<ResourceModel> beanItemContainer) {
		steleLayout.setPoiContainer(beanItemContainer);
	}

	@Override
	public void languageChanged(Locale locale) {
		if (!UI.getCurrent().getLocale().equals(locale)) {
			UI.getCurrent().setLocale(locale);
			steleLayout.updateTranslations();
			Page.getCurrent().setTitle(
					Translator.getInstance().translate(
							TranslationKeys.APP_TITLE));
		}
	}

	@Override
	public SteleLayoutSpec getSteleLayoutView() {
		return steleLayout;
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

}