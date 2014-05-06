package com.pathfinder;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pathfinder.model.Device;
import com.pathfinder.presenter.DataLoader;
import com.pathfinder.presenter.DataLoaderSpec;
import com.pathfinder.presenter.MobilePresenterSpec;
import com.pathfinder.presenter.StelePresenter;
import com.pathfinder.presenter.StelePresenterSpec;
import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.vaadin.annotations.Theme;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServletRequest;
import com.vaadin.server.WebBrowser;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.UI;

/**
 * PathfinderUI - Entry point for every client
 * 
 * @author alexh
 * 
 */
@Theme("rapla_pathfinder_p")
public class PathfinderUI extends UI {
	private static final Logger LOGGER = LogManager
			.getLogger(PathfinderUI.class.getName());

	private StelePresenterSpec stelePresenter = null;
	private MobilePresenterSpec mobilePresenter = null;

	private Page page = null;
	private WebBrowser webBrowser = null;
	private String userAgent = "";
	private Device device = Device.UNDEFINED;

	@Override
	protected void init(VaadinRequest request) {

		this.readUrlParameter(request.getParameterMap());

		Responsive.makeResponsive(this);

		setErrorHandler(new PathfinderErrorHandler());
		Page.getCurrent().setTitle(
				Translator.getInstance().translate(TranslationKeys.APP_TITLE));
		this.getBrowserData(request);
		this.setUiLocale(request.getLocale());
		this.printBrowserInfo(page, webBrowser, userAgent);
		this.initDataloader();
		this.buildLayout();
	}

	private void getBrowserData(VaadinRequest request) {
		// TODO set dhbwEntryPoint - read directory after pathfinder/ LA, "", RA
		// TODO Difference between getCurrent().getPage();?
		page = Page.getCurrent();
		webBrowser = getPage().getWebBrowser();
		if (request instanceof VaadinServletRequest) {
			userAgent = ((VaadinServletRequest) request)
					.getHttpServletRequest().getHeader("User-Agent")
					.toLowerCase();
		}
	}

	private void setUiLocale(Locale locale) {
		if (locale != null
				&& Translator.getInstance().isLocaleSupported(locale)) {
			setLocale(locale);
		} else {
			setLocale(Translator.getInstance().getFallbackLocale());
		}
	}

	private void printBrowserInfo(Page page, WebBrowser webBrowser,
			String userAgent) {
		LOGGER.trace(">> Browser Data <<");
		LOGGER.trace("Current window width: " + page.getBrowserWindowWidth());
		LOGGER.trace("Current window height: " + page.getBrowserWindowHeight());
		LOGGER.trace("Max browser width: " + webBrowser.getScreenWidth());
		LOGGER.trace("Max browser height: " + webBrowser.getScreenHeight());
		LOGGER.trace("User Agent: " + userAgent);
		LOGGER.trace("Is Android device: " + webBrowser.isAndroid());
		LOGGER.trace("Is iOS device: " + webBrowser.isIOS());
		LOGGER.trace("Is touch device: " + webBrowser.isTouchDevice());
	}

	private void initDataloader() {
		DataLoaderSpec dataLoader = DataLoader.getInstance();
	}

	private void buildLayout() {
		// stelePresenter = new StelePresenter();
		// setContent(stelePresenter.getDesktopLayoutView());
		// addListenerToAllChildComponents((HasComponents) getContent(),
		// stelePresenter.getUiListener());

		switch (device) {
		case UNDEFINED:
			// TODO: Check if mobile or not and build appropriate layout
			buildSteleLayout();
			break;

		case STELE_LEFT:
		case STELE_MIDDLE:
		case STELE_RIGHT:
			buildSteleLayout();
			break;

		case MOBILE:
			// TODO buildMobileLayout()
			break;

		case DESKTOP:
			// TODO buildDesktopLayout
			break;
		}

		// TODO
		// LOGGER.trace("Desktop application initialized");
		// if (webBrowser.isAndroid() || webBrowser.isIOS()
		// || webBrowser.getScreenWidth() < 768) {
		// mobilePresenter = new MobilePresenter();
		// setContent(mobilePresenter.getMobileLayoutView());
		// LOGGER.trace("Mobile application initialized");
		// } else {
		// desktopPresenter = new DesktopPresenter();
		// setContent(desktopPresenter.getDesktopLayoutView());
		// addClickListener(desktopPresenter.getUiClickListener());
		// LOGGER.trace("Desktop application initialized");
		// }
	}

	private void buildSteleLayout() {

		stelePresenter = new StelePresenter();
		stelePresenter.setDevice(device);
		stelePresenter.setUserAgent(userAgent);
		setContent(stelePresenter.getSteleLayoutView());
		addListenerToAllChildComponents((HasComponents) getContent(),
				stelePresenter.getUiListener());
	}

	/**
	 * Add listener to all UI components recursively to get notified when user
	 * interacts
	 * 
	 * @param rootComponent
	 * @param listener
	 */
	private void addListenerToAllChildComponents(HasComponents rootComponent,
			Listener listener) {
		Iterator<Component> iterator = rootComponent.iterator();
		while (iterator.hasNext()) {
			Component childComponent = iterator.next();
			if (childComponent instanceof HasComponents) {
				addListenerToAllChildComponents((HasComponents) childComponent,
						listener);
			}
			childComponent.addListener(listener);
		}
	}

	private void readUrlParameter(Map<String, String[]> parameterMap) {
		String[] parameterValue = parameterMap
				.get(Device.DEVICE_URL_PARAMETER_NAME);

		if (parameterValue != null && parameterValue.length > 0) {
			this.device = Device.getDevice(parameterValue[0]);
		}

	}
}
