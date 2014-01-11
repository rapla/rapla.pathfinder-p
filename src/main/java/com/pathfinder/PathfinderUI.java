package com.pathfinder;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pathfinder.presenter.DataLoader;
import com.pathfinder.presenter.DataLoaderListenerSpec;
import com.pathfinder.presenter.DesktopPresenter;
import com.pathfinder.presenter.DesktopPresenterSpec;
import com.pathfinder.presenter.MobilePresenter;
import com.pathfinder.presenter.MobilePresenterSpec;
import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.vaadin.annotations.Theme;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServletRequest;
import com.vaadin.server.WebBrowser;
import com.vaadin.ui.UI;

/**
 * PathfinderUI - Entry point for every client
 * 
 * @author alexh
 * 
 */
@Theme("rapla_pathfinder_p")
public class PathfinderUI extends UI implements DataLoaderListenerSpec {
	private static final Logger LOGGER = LogManager
			.getLogger(PathfinderUI.class.getName());

	private DesktopPresenterSpec desktopPresenter = null;
	private MobilePresenterSpec mobilePresenter = null;

	private Page page = null;
	private WebBrowser webBrowser = null;
	private String userAgent = "";

	@Override
	protected void init(VaadinRequest request) {
		// TODO
		// new Responsive(this);
		setErrorHandler(new PathfinderErrorHandler());
		Page.getCurrent().setTitle(
				Translator.getInstance().translate(TranslationKeys.APP_TITLE));
		this.getBrowserData(request);
		this.setUiLocale(request.getLocale());
		this.printBrowserInfo(page, webBrowser, userAgent);
		this.buildLayout();
		this.setData();
	}

	private void getBrowserData(VaadinRequest request) {
		// TODO Difference between getCurrent().getPage();?
		page = Page.getCurrent();
		webBrowser = getPage().getWebBrowser();
		if (request instanceof VaadinServletRequest) {
			userAgent = ((VaadinServletRequest) request)
					.getHttpServletRequest().getHeader("User-Agent")
					.toLowerCase();
		}
	}

	/**
	 * Sets locale of UI as specified in parameter; if it's not supported or
	 * null, the fallback locale will be taken
	 * 
	 * @param locale
	 */
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

	private void buildLayout() {
		if (webBrowser.isAndroid() || webBrowser.isIOS()
				|| webBrowser.getScreenWidth() < 768) {
			mobilePresenter = new MobilePresenter();
			setContent(mobilePresenter.getMobileLayoutView());
			LOGGER.trace("Mobile application initialized");
		} else {
			desktopPresenter = new DesktopPresenter();
			setPrimaryStyleName("main");
			setContent(desktopPresenter.getDesktopLayoutView());
			addClickListener(desktopPresenter.getUiClickListener());
			LOGGER.trace("Desktop application initialized");
		}
	}

	private void setData() {
		DataLoader dataLoader = DataLoader.getInstance();
		desktopPresenter.setRoomContainer(dataLoader.getRoomContainer());
		desktopPresenter.setCourseContainer(dataLoader.getCourseContainer());
		desktopPresenter.setPersonContainer(dataLoader.getPersonContainer());
		desktopPresenter.setPoiContainer(dataLoader.getPoiContainer());
		// Register as DataListener to get notified if data changes
		dataLoader.addDataListener(this);
	}

	@Override
	public void dataUpdated() {
		setData();
	}
}