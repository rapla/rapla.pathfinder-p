package com.pathfinder.presenter;

import java.util.List;
import java.util.Locale;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import org.json.simple.JSONObject;

import com.pathfinder.model.CourseModel;
import com.pathfinder.model.PersonModel;
import com.pathfinder.model.PoiModel;
import com.pathfinder.model.RoomModel;
import com.pathfinder.util.properties.ApplicationProperties;
import com.pathfinder.util.properties.ApplicationPropertiesSpec;
import com.pathfinder.util.properties.PropertiesKey;
import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.view.layout.DesktopLayout;
import com.pathfinder.view.layout.DesktopLayoutSpec;
import com.pathfinder.view.listener.DesktopLayoutViewListenerSpec;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
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
		DesktopPresenterSpec {

	// Needed sub-presenter
	private final SearchPanelPresenterSpec searchPanelPresenter = new SearchPanelPresenter();
	private final GenericDataLoaderSpec genericDataLoader = new GenericDataLoader();

	private ApplicationPropertiesSpec properties = ApplicationProperties
			.getInstance();

	// Layout
	private final DesktopLayoutSpec desktopLayout = new DesktopLayout(
			searchPanelPresenter.getSearchPanel());

	public DesktopPresenter() {
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

	@Override
	public void languageChanged(Locale locale) {
		desktopLayout.updateTranslations();
		// TODO detailContainer.updateTranslations(locale);
		searchPanelPresenter.updateTranslations();
		Page.getCurrent().setTitle(
				Translator.getInstance().translate(TranslationKeys.APP_TITLE));
	}

	@Override
	public CustomComponent getDesktopLayoutView() {
		return (DesktopLayout) desktopLayout;
	}

}