package com.pathfinder.presenter;

import java.util.Locale;
import java.util.TimerTask;

import com.pathfinder.model.CourseModel;
import com.pathfinder.model.PersonModel;
import com.pathfinder.model.PoiModel;
import com.pathfinder.model.RoomModel;
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

	// Layout
	private final DesktopLayoutSpec desktopLayout = new DesktopLayout(
			searchPanelPresenter.getSearchPanel());

	public DesktopPresenter() {
		desktopLayout
				.addClickListenerAppointmentButton(new AppointmentButtonClickListener());
		desktopLayout
				.addLanguageValueChangeListener(new LanguageValueChangeListener());
	}

	// TODO
	// class ResourceClickListener extends
	// {
	// Timer timer = new Timer();
	// timer.schedule(new RespawnDesktopLayoutTimer(), 60000);
	// }

	class LanguageValueChangeListener implements ValueChangeListener {
		public void valueChange(ValueChangeEvent event) {
			Locale locale = Locale.GERMAN;
			Locale value = (Locale) event.getProperty().getValue();
			UI.getCurrent().setLocale(value);
			languageChanged(locale);
		}
	}

	class AppointmentButtonClickListener implements ClickListener {
		@Override
		public void buttonClick(ClickEvent event) {
			// TODO Should be variable URL - getResourceUrl();
			desktopLayout
					.setAppointmentUrl("http://localhost:8051/rapla/rapla?page=calendar&user=stele&file=kurs&allocatable_id=2373");
			desktopLayout.switchToAppointmentView();
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