package com.pathfinder.presenter;

import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pathfinder.model.KeyboardModel;
import com.pathfinder.model.ResourceModel;
import com.pathfinder.util.properties.ApplicationProperties;
import com.pathfinder.util.properties.ApplicationPropertiesSpec;
import com.pathfinder.util.properties.PropertiesKey;
import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.view.components.AccordionView;
import com.pathfinder.view.components.AccordionViewSpec;
import com.pathfinder.view.components.Keyboard;
import com.pathfinder.view.components.KeyboardId;
import com.pathfinder.view.components.KeyboardSpec;
import com.pathfinder.view.components.SearchField;
import com.pathfinder.view.components.SearchFieldSpec;
import com.pathfinder.view.container.SearchPanel;
import com.pathfinder.view.container.SearchPanelSpec;
import com.pathfinder.view.layout.DesktopLayout;
import com.pathfinder.view.layout.DesktopLayoutSpec;
import com.pathfinder.view.listener.DesktopLayoutViewListenerSpec;
import com.pathfinder.view.listener.KeyboardViewListenerSpec;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

/**
 * The presenter for the desktop/stele view
 * 
 * @author alexh
 * 
 */
public class DesktopPresenter implements DesktopLayoutViewListenerSpec,
		DesktopPresenterSpec, KeyboardViewListenerSpec {
	private static final Logger LOGGER = LogManager
			.getLogger(DesktopPresenter.class.getName());
	private final ApplicationPropertiesSpec properties = ApplicationProperties
			.getInstance();
	private final DataLoader dataLoader = DataLoader.getInstance();
	private final BeanFieldGroup<KeyboardModel> keyboardBinder = new BeanFieldGroup<KeyboardModel>(
			KeyboardModel.class);

	// TODO Move this inizialitations to DesktopLayout
	private final AccordionViewSpec accordionView = new AccordionView();
	private final KeyboardSpec keyboard = new Keyboard();
	private final SearchFieldSpec searchField = new SearchField();
	private final SearchPanelSpec searchPanel = new SearchPanel(
			(AccordionView) accordionView, (Keyboard) keyboard,
			(SearchField) searchField);

	// TODO Remove SearchPanel from DesktopLayout constructor
	private final DesktopLayoutSpec desktopLayout = new DesktopLayout(
			searchPanel);

	private final int goBackHomeIntervall = properties
			.getIntProperty(PropertiesKey.BACK_TO_HOME_TIMER);

	public DesktopPresenter() {
		this.keyboard.addListener(this);
		this.keyboardBinder.setBuffered(false);
		this.keyboardBinder.setItemDataSource(new KeyboardModel());
		this.keyboardBinder.bind(searchField.getSearchField(),
				KeyboardModel.PROPERTY_SEARCHSTRING);

		this.accordionView
				.addItemClickListenerRoomTable(new TableClickListener());
		this.accordionView
				.addItemClickListenerCourseTable(new TableClickListener());
		this.accordionView
				.addItemClickListenerPersonTable(new TableClickListener());
		this.accordionView
				.addItemClickListenerPoiTable(new TableClickListener());

		this.searchField
				.addDeleteAllClickListener(new DeleteAllClickListener());

		desktopLayout.addClickListenerHomeButton(new HomeButtonClickListener());
		desktopLayout
				.addClickListenerAppointmentButton(new AppointmentButtonClickListener());
		desktopLayout
				.addClickListenerWheelChairButton(new WheelChairButtonClickListener());
		desktopLayout.addClickListenerBackButton(new BackButtonClickListener());

		for (Locale locale : Translator.getInstance().getSupportedLocales()) {
			desktopLayout.addClickListenerFlagPopup(locale,
					new FlagImageClickListener(locale));
		}

		this.refreshFreeRooms();
		this.scheduleFreeRoomsLoading();
		this.startBackToHomeTimer();
	}

	// TODO
	// class ResourceClickListener extends
	// {
	// Timer timer = new Timer();
	// timer.schedule(new RespawnDesktopLayoutTimer(), 60000);
	// }

	@Override
	public void buttonClick(KeyboardId keyId) {

		KeyboardId id = (KeyboardId) keyId;
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
			addKeybordKeyToSearchString(keyId.getLabel());
			break;
		}
	}

	class TableClickListener implements ItemClickListener {
		@Override
		public void itemClick(ItemClickEvent event) {
			ResourceModel resource = (ResourceModel) event.getItemId();
			LOGGER.trace(resource.getType() + " element was clicked: "
					+ resource.getName());
			desktopLayout.switchToDetailView(resource);
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
			desktopLayout.switchToSearchView();
		}
	}

	class AppointmentButtonClickListener implements ClickListener {
		@Override
		public void buttonClick(ClickEvent event) {
			// TODO Should be variable URL - getResourceUrl();
			// ResourceModel resource = (ResourceModel) event.getComponent();
			// desktopLayout.setAppointmentUrl(resource.getLink());
			desktopLayout.setAppointmentUrl(properties
					.getProperty(PropertiesKey.APPOINTMENT_BASE_URL)
					+ "&allocatable_id=2373");
			desktopLayout.switchToAppointmentView();
		}
	}

	class WheelChairButtonClickListener implements ClickListener {
		@Override
		public void buttonClick(ClickEvent event) {
			Notification.show("You pressed the WheelchairMan!");
		}
	}

	class BackButtonClickListener implements ClickListener {
		@Override
		public void buttonClick(ClickEvent event) {
			desktopLayout.switchToSearchView();
		}
	}

	class RespawnDesktopLayoutTimer extends TimerTask {
		@Override
		public void run() {
			desktopLayout.switchToSearchView();
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
			desktopLayout.hideOpenLanguagePopup();
			languageChanged(locale);
		}

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

		searchField.getSearchField().focus();
		setChangePosCounter(oldCursorPosition + 1);
	}

	public void deleteKeyFromSearchString() {
		int oldCursorPosition = getChangePosCounter();

		StringBuilder newSearchString = new StringBuilder(getSearchString());

		if (oldCursorPosition > 0
				&& oldCursorPosition <= newSearchString.length()) {
			newSearchString.deleteCharAt(oldCursorPosition - 1);

			setSearchString(newSearchString.toString());

			searchField.getSearchField().focus();
			setChangePosCounter(oldCursorPosition - 1);
		} else {
			searchField.getSearchField().focus();
		}
	}

	public void clearSearchString() {
		setSearchString("");
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
	public SearchPanel getSearchPanel() {
		return (SearchPanel) searchPanel;
	}

	public int getChangePosCounter() {
		return searchField.getSearchField().getCursorPosition();
	}

	public void setChangePosCounter(int cursorPosition) {
		if (cursorPosition >= 0 && cursorPosition <= getSearchString().length())
			searchField.getSearchField().setCursorPosition(cursorPosition);
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
		accordionView.useFiltersForAllTables(getSearchString());
	}

	@Override
	public int getCursorPosition() {
		return searchField.getSearchField().getCursorPosition();
	}

	public synchronized void refreshFreeRooms() {
		desktopLayout.refreshFreeRooms(dataLoader.getFreeResources());
	}

	@Override
	public void languageChanged(Locale locale) {
		if (!UI.getCurrent().getLocale().equals(locale)) {
			UI.getCurrent().setLocale(locale);
			desktopLayout.updateTranslations();
			searchPanel.updateTranslations();
			Page.getCurrent().setTitle(
					Translator.getInstance().translate(
							TranslationKeys.APP_TITLE));
		}
	}

	@Override
	public CustomComponent getDesktopLayoutView() {
		return (DesktopLayout) desktopLayout;
	}

	@Override
	public void startBackToHomeTimer() {
		Timer backToHomeTimer = new Timer();
		TimerTask backToHomeTimerTask = new TimerTask() {

			@Override
			public void run() {
				if (isTimeToGoHome()) {
					goBackToHomeScreenAndRestoreDefaultSettings();
				}
			}
		};

		backToHomeTimer.schedule(backToHomeTimerTask, 0, 1000);
	}

	private void goBackToHomeScreenAndRestoreDefaultSettings() {
		desktopLayout.switchToSearchView();
		clearSearchString();
		languageChanged(VaadinSession.getCurrent().getLocale());
		UI.getCurrent().push();
	}

	private boolean isTimeToGoHome() {
		boolean result = false;

		long lastRequest = VaadinSession.getCurrent().getLastRequestTimestamp();
		long millisecondsSinceLastRequest = new Date().getTime() - lastRequest;

		if (millisecondsSinceLastRequest >= goBackHomeIntervall) {
			VaadinSession.getCurrent().setLastRequestTimestamp(
					new Date().getTime());
			result = true;
		}

		return result;
	}
}