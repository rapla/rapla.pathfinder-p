package com.pathfinder.presenter;

import java.util.Locale;
import java.util.TimerTask;

import com.pathfinder.model.CourseModel;
import com.pathfinder.model.PersonModel;
import com.pathfinder.model.PoiModel;
import com.pathfinder.model.RoomModel;
import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.widgetset.DateTime;
import com.pathfinder.view.components.Appointment;
import com.pathfinder.view.components.AppointmentSpec;
import com.pathfinder.view.components.DateTimeSpec;
import com.pathfinder.view.components.FreeRoom;
import com.pathfinder.view.components.FreeRoomSpec;
import com.pathfinder.view.components.MenuBar;
import com.pathfinder.view.components.MenuBarSpec;
import com.pathfinder.view.container.InfoPanel;
import com.pathfinder.view.container.InfoPanelSpec;
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
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

/**
 * The presenter for the desktop/stele view
 * 
 * @author alexh
 * 
 */
public class DesktopPresenter implements DesktopLayoutViewListenerSpec,
		DesktopPresenterSpec {
	// Needed components
	private final DateTimeSpec dateTime = new DateTime();
	private final FreeRoomSpec freeRoomView = new FreeRoom();
	private final MenuBarSpec menuBar = new MenuBar();

	// Needed sub-presenters
	private final SearchPanelPresenterSpec searchPanelPresenter = new SearchPanelPresenter();

	// Layouts
	private final InfoPanelSpec infoPanel = new InfoPanel((DateTime) dateTime,
			(FreeRoom) freeRoomView);
	private final DesktopLayoutSpec desktopLayout = new DesktopLayout(
			(InfoPanel) infoPanel, (MenuBar) menuBar,
			searchPanelPresenter.getSearchPanel());

	public DesktopPresenter() {
		menuBar.addClickListenerAppointmentButton(new AppointmentButtonClickListener());
		menuBar.addValueChangeListener(new LanguageValueChangeListener());
	}

	// TODO
	// class ResourceClickListener extends
	// {
	// Timer timer = new Timer();
	// timer.schedule(new RespawnDesktopLayoutTimer(), 60000);
	// }

	class LanguageValueChangeListener implements ValueChangeListener {
		public void valueChange(ValueChangeEvent event) {
			String[] languages = menuBar.getLanguages();
			Locale locale = Locale.GERMAN;
			final Object valueString = event.getProperty().getValue();
			if (valueString.equals(languages[0])) {
				locale = Locale.GERMAN;
			} else if (valueString.equals(languages[1])) {
				locale = Locale.ENGLISH;
			}

			UI.getCurrent().setLocale(locale);
			languageChanged(locale);
		}
	}

	class AppointmentButtonClickListener implements ClickListener {
		@Override
		public void buttonClick(ClickEvent event) {
			// TODO For testing. Should be integrated in the DesktopLayout
			Window window = new Window("Appointment");
			// TODO Should be variable URL - getResourceUrl();
			AppointmentSpec appointment = new Appointment();
			appointment
					.setUrl("http://localhost:8051/rapla/rapla?page=calendar&user=stele&file=kurs&allocatable_id=2373");
			window.setContent((Appointment) appointment);
			window.setSizeFull();
			((Appointment) appointment).setSizeFull();
			UI.getCurrent().addWindow(window);
		}
	}

	class RespawnDesktopLayoutTimer extends TimerTask {

		@Override
		public void run() {
			desktopLayout.switchToSearchView();
		}
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
		infoPanel.updateTranslations();
		// TODO detailContainer.updateTranslations(locale);
		searchPanelPresenter.updateTranslations();
		menuBar.updateTranslations();
		Page.getCurrent().setTitle(
				Translator.getInstance().translate(TranslationKeys.APP_TITLE));
	}

	@Override
	public CustomComponent getDesktopLayoutView() {
		return (DesktopLayout) desktopLayout;
	}
}
