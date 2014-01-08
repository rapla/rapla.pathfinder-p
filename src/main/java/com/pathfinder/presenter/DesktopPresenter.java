package com.pathfinder.presenter;

import java.util.List;
import java.util.Locale;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import org.json.simple.JSONObject;

import com.pathfinder.model.CourseModel;
import com.pathfinder.model.KeyboardModel;
import com.pathfinder.model.PersonModel;
import com.pathfinder.model.PoiModel;
import com.pathfinder.model.RoomModel;
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
import com.pathfinder.view.container.DetailContainer;
import com.pathfinder.view.container.DetailContainerSpec;
import com.pathfinder.view.container.SearchPanel;
import com.pathfinder.view.container.SearchPanelSpec;
import com.pathfinder.view.layout.DesktopLayout;
import com.pathfinder.view.layout.DesktopLayoutSpec;
import com.pathfinder.view.listener.DesktopLayoutViewListenerSpec;
import com.pathfinder.view.listener.KeyboardViewListenerSpec;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.Page;
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

	private final ApplicationPropertiesSpec properties = ApplicationProperties
			.getInstance();

	// Added from SearchPanelPresenter
	private final AccordionViewSpec accordionView = new AccordionView();
	private final KeyboardSpec keyboard = new Keyboard();
	private final SearchFieldSpec searchField = new SearchField();
	private final SearchPanelSpec searchPanel = new SearchPanel(
			(AccordionView) accordionView, (Keyboard) keyboard,
			(SearchField) searchField);
	private DetailContainerSpec detailContainer = null;

	private final BeanFieldGroup<KeyboardModel> keyboardBinder = new BeanFieldGroup<KeyboardModel>(
			KeyboardModel.class);

	// Needed sub-presenter
	private final GenericDataLoaderSpec genericDataLoader = new GenericDataLoader();

	// Layout
	private final DesktopLayoutSpec desktopLayout = new DesktopLayout(
			searchPanel);

	public DesktopPresenter() {
		// Added from SearchPanelPresenter
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

		desktopLayout
				.addLanguageValueChangeListener(new LanguageValueChangeListener());
		desktopLayout
				.addClickListenerAppointmentButton(new AppointmentButtonClickListener());
		desktopLayout
				.addClickListenerWheelChairButton(new WheelChairButtonClickListener());
		desktopLayout.addClickListenerBackButton(new BackButtonClickListener());
		this.freeRoomHandler();
		this.scheduleFreeRoomsLoading();
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

	class DeleteAllClickListener implements ClickListener {

		@Override
		public void buttonClick(ClickEvent event) {
			clearSearchString();
		}
	}

	class LanguageValueChangeListener implements ValueChangeListener {
		public void valueChange(ValueChangeEvent event) {
			Locale value = (Locale) event.getProperty().getValue();
			UI.getCurrent().setLocale(value);
			languageChanged(value);
		}
	}

	class AppointmentButtonClickListener implements ClickListener {
		@Override
		public void buttonClick(ClickEvent event) {
			// TODO Should be variable URL - getResourceUrl();
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

	private TimerTask getTimerTask() {
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				freeRoomHandler();
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

	class TableClickListener implements ItemClickListener {
		@Override
		public void itemClick(ItemClickEvent event) {
			Object object = event.getItemId();

			detailContainer = new DetailContainer(object, null);

			// if (object instanceof RoomModel) {
			// logger.trace("Room was clicked - ItemID: "
			// + ((RoomModel) object).getName());
			// accordionView.deselectClickedItem((Table) event.getSource(),
			// event.getItemId());
			// } else if (object instanceof CourseModel) {
			// logger.trace("Course was clicked");
			// } else if (object instanceof PersonModel) {
			// logger.trace("Person was clicked");
			// } else if (object instanceof PoiModel) {
			// logger.trace("Poi was clicked");
			// } else {
			// logger.trace("Unknown item was clicked");
			// }
		}
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
	public void setRoomContainer(BeanItemContainer<RoomModel> beanItemContainer) {
		accordionView.setRoomContainer(beanItemContainer);
	}

	@Override
	public void setCourseContainer(
			BeanItemContainer<CourseModel> beanItemContainer) {
		accordionView.setCourseContainer(beanItemContainer);
	}

	@Override
	public void setPersonContainer(
			BeanItemContainer<PersonModel> beanItemContainer) {
		accordionView.setPersonContainer(beanItemContainer);
	}

	@Override
	public void setPoiContainer(BeanItemContainer<PoiModel> beanItemContainer) {
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

	public synchronized void freeRoomHandler() {

		List<String> raumNameList = new Stack<String>();
		List<String> raumLinkList = new Stack<String>();
		List<String> raumIdList = new Stack<String>();
		List<String> startList = new Stack<String>();
		List<String> endList = new Stack<String>();

		List<JSONObject> freeResourcesResult = genericDataLoader
				.getFreeResourcesResult();

		// TODO Add error handling to handle NullPointerException
		if (freeResourcesResult != null) {
			for (JSONObject result : freeResourcesResult) {
				List<JSONObject> freeResourcesResources = genericDataLoader
						.getFreeResourcesResources(result);

				String raumName = (String) freeResourcesResources.get(0).get(
						"name");
				String raumLink = (String) freeResourcesResources.get(0).get(
						"link");
				String raumId = (String) freeResourcesResources.get(0)
						.get("id");
				String start = (String) result.get("start");
				String end = (String) result.get("end");

				raumNameList.add(raumName);
				raumLinkList.add(raumLink);
				raumIdList.add(raumId);
				startList.add(start);
				endList.add(end);
			}
		}

		desktopLayout.getFreeRoom().refreshFreeRooms(raumNameList,
				raumLinkList, raumIdList, startList, endList);

	}

	@Override
	public void languageChanged(Locale locale) {
		desktopLayout.updateTranslations();
		// TODO detailContainer.updateTranslations(locale);
		searchPanel.updateTranslations();
		Page.getCurrent().setTitle(
				Translator.getInstance().translate(TranslationKeys.APP_TITLE));
	}

	@Override
	public CustomComponent getDesktopLayoutView() {
		return (DesktopLayout) desktopLayout;
	}

}