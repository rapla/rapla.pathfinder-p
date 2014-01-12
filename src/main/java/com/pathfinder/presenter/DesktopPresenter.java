package com.pathfinder.presenter;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pathfinder.model.Attribut;
import com.pathfinder.model.KeyboardModel;
import com.pathfinder.model.ResourceModel;
import com.pathfinder.util.properties.ApplicationProperties;
import com.pathfinder.util.properties.ApplicationPropertiesSpec;
import com.pathfinder.util.properties.PropertiesKey;
import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.widgetset.BackToHomeScreenListenerSpec;
import com.pathfinder.view.components.KeyboardId;
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
import com.vaadin.ui.UI;

/**
 * The presenter for the desktop/stele view
 * 
 * @author alexh
 * 
 */
public class DesktopPresenter implements DesktopLayoutViewListenerSpec,
		DesktopPresenterSpec, KeyboardViewListenerSpec, DataLoaderListenerSpec {
	private static final Logger LOGGER = LogManager
			.getLogger(DesktopPresenter.class.getName());
	private final ApplicationPropertiesSpec properties = ApplicationProperties
			.getInstance();
	private final DataLoader dataLoader = DataLoader.getInstance();
	private final BeanFieldGroup<KeyboardModel> keyboardBinder = new BeanFieldGroup<KeyboardModel>(
			KeyboardModel.class);

	private final DesktopLayoutSpec desktopLayout = new DesktopLayout();

	private final int goBackHomeIntervall = properties
			.getIntProperty(PropertiesKey.BACK_TO_HOME_TIMER);

	private ResourceModel resource = null;

	private long lastUserInteractionTimestamp;

	public DesktopPresenter() {
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
		this.desktopLayout.addKeyboardListener(this);
		this.keyboardBinder.setBuffered(false);
		this.keyboardBinder.setItemDataSource(new KeyboardModel());
		this.keyboardBinder.bind(desktopLayout.getSearchField(),
				KeyboardModel.PROPERTY_SEARCHSTRING);
		this.desktopLayout.addItemClickListener(new TableDetailClickListener());
		this.desktopLayout
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

		desktopLayout.addBackToHomeListener(new BackToHomeListener());
	}

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

	@Override
	public void dataUpdated() {
		this.setResourceData();
	}

	class TableDetailClickListener implements ItemClickListener {
		@Override
		public void itemClick(ItemClickEvent event) {
			resource = (ResourceModel) event.getItemId();
			LOGGER.trace(resource.getType() + " element was clicked: "
					+ resource.getName());
			switchToDetailView(resource);
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
			switchToSearchView();
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
			switchToAppointmentView();
		}
	}

	class WheelChairButtonClickListener implements ClickListener {
		@Override
		public void buttonClick(ClickEvent event) {
			changeWheelChairView();
		}
	}

	class BackButtonClickListener implements ClickListener {
		@Override
		public void buttonClick(ClickEvent event) {
			switchToDetailView(resource);
		}
	}

	class RespawnDesktopLayoutTimer extends TimerTask {
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
			desktopLayout.hideOpenLanguagePopup();
			languageChanged(locale);
		}
	}

	class BackToHomeListener implements BackToHomeScreenListenerSpec {

		@Override
		public void timeToGoHome() {
			if (isTimeToGoHome()) {
				goBackToHomeScreenAndRestoreDefaultSettings();
			}
		}

	}

	@Override
	public com.vaadin.event.MouseEvents.ClickListener getUiClickListener() {
		return new com.vaadin.event.MouseEvents.ClickListener() {

			@Override
			public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
				lastUserInteractionTimestamp = new Date().getTime();
			}
		};
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

	@Override
	public void switchToSearchView() {
		// Hiding
		desktopLayout.hideAppointmentView();
		desktopLayout.hideDetailContainer();
		desktopLayout.removeDetails();

		// Adapting MenuBar
		desktopLayout.replaceHomeButtonWithWheelChairButton();
		desktopLayout.replaceBackButtonWithAppointmentButton();
		desktopLayout.hideAppointmentButton();

		// Showing
		desktopLayout.showFreeRoomView();
		desktopLayout.showSearchPanel();
		desktopLayout.showKeyboard();
	}

	@Override
	public void switchToDetailView(ResourceModel resource) {
		List<Attribut> resourceDetails = dataLoader.getResourceDetails(
				resource.getId(), UI.getCurrent().getLocale());

		// Hiding
		desktopLayout.hideAppointmentView();
		desktopLayout.hideFreeRoomView();
		desktopLayout.hideSearchPanel();
		desktopLayout.hideKeyboard();

		// Adapting MenuBar
		desktopLayout.replaceWheelChairButtonWithHomeButton();

		// for (Attribut attribut : resourceDetails) {
		// if ("resourceUrl".equals(attribut.getLabel())) {
		// desktopLayout.showAppointmentButton();
		// }
		// }
		desktopLayout.showAppointmentButton();

		// Showing
		desktopLayout.addDetails(resource, resourceDetails);
		desktopLayout.showDetailContainer();
	}

	@Override
	public void switchToAppointmentView() {
		// Hiding
		desktopLayout.hideFreeRoomView();
		desktopLayout.hideSearchPanel();
		desktopLayout.hideKeyboard();
		desktopLayout.hideDetailContainer();
		desktopLayout.removeDetails();

		// Adapting MenuBar
		desktopLayout.replaceAppointmentButtonWithBackButton();

		// Showing
		desktopLayout.showAppointmentView();
	}

	@Override
	public void changeWheelChairView() {
		desktopLayout.changeWheelChairView();
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

		desktopLayout.focusSearchField();
		setChangePosCounter(oldCursorPosition + 1);
	}

	public void deleteKeyFromSearchString() {
		int oldCursorPosition = getChangePosCounter();

		StringBuilder newSearchString = new StringBuilder(getSearchString());

		if (oldCursorPosition > 0
				&& oldCursorPosition <= newSearchString.length()) {
			newSearchString.deleteCharAt(oldCursorPosition - 1);

			setSearchString(newSearchString.toString());

			desktopLayout.focusSearchField();
			setChangePosCounter(oldCursorPosition - 1);
		} else {
			desktopLayout.focusSearchField();
		}
	}

	public void clearSearchString() {
		setSearchString("");
	}

	@Override
	public void setRoomContainer(
			BeanItemContainer<ResourceModel> beanItemContainer) {
		desktopLayout.setRoomContainer(beanItemContainer);
	}

	@Override
	public void setCourseContainer(
			BeanItemContainer<ResourceModel> beanItemContainer) {
		desktopLayout.setCourseContainer(beanItemContainer);
	}

	@Override
	public void setPersonContainer(
			BeanItemContainer<ResourceModel> beanItemContainer) {
		desktopLayout.setPersonContainer(beanItemContainer);
	}

	@Override
	public void setPoiContainer(
			BeanItemContainer<ResourceModel> beanItemContainer) {
		desktopLayout.setPoiContainer(beanItemContainer);
	}

	public int getChangePosCounter() {
		return desktopLayout.getCursorPosition();
	}

	public void setChangePosCounter(int cursorPosition) {
		if (cursorPosition >= 0 && cursorPosition <= getSearchString().length())
			desktopLayout.setCursorPosition(cursorPosition);
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
		desktopLayout.useFiltersForAllTables(getSearchString());
	}

	@Override
	public int getCursorPosition() {
		return desktopLayout.getCursorPosition();
	}

	public synchronized void refreshFreeRooms() {
		desktopLayout.refreshFreeRooms(dataLoader.getFreeResources());
	}

	@Override
	public void languageChanged(Locale locale) {
		if (!UI.getCurrent().getLocale().equals(locale)) {
			UI.getCurrent().setLocale(locale);
			desktopLayout.updateTranslations();
			Page.getCurrent().setTitle(
					Translator.getInstance().translate(
							TranslationKeys.APP_TITLE));
		}
	}

	@Override
	public DesktopLayoutSpec getDesktopLayoutView() {
		return desktopLayout;
	}

	private void goBackToHomeScreenAndRestoreDefaultSettings() {
		switchToSearchView();
		clearSearchString();
		languageChanged(VaadinSession.getCurrent().getLocale());
	}

	private boolean isTimeToGoHome() {
		boolean result = false;

		long millisecondsSinceLastRequest = new Date().getTime()
				- lastUserInteractionTimestamp;

		if (millisecondsSinceLastRequest >= goBackHomeIntervall) {
			lastUserInteractionTimestamp = new Date().getTime();
			result = true;
		}

		return result;
	}
}