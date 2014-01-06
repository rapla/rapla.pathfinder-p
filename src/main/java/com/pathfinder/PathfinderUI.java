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

@Theme("rapla_pathfinder_p")
public class PathfinderUI extends UI implements DataLoaderListenerSpec {

	private static final Logger logger = LogManager
			.getLogger(PathfinderUI.class.getName());
	private DesktopPresenterSpec desktopPresenter = null;
	private MobilePresenterSpec mobilePresenter = null;

	@Override
	protected void init(VaadinRequest request) {
		setUiLocale(request.getLocale());
		setErrorHandler(new PathfinderErrorHandler());
		Page.getCurrent().setTitle(
				Translator.getInstance().translate(TranslationKeys.APP_TITLE));
		this.buildLayout(request);
	}

	private void buildLayout(VaadinRequest request) {
		Page page = Page.getCurrent();
		getCurrent().getPage();
		WebBrowser webBrowser = getPage().getWebBrowser();
		//BrowserInfo is for client side - WebBrowser for server side 
		// BrowserInfo browserInfo = BrowserInfo.get();
		String userAgent = "";
		if (request instanceof VaadinServletRequest) {
			userAgent = ((VaadinServletRequest) request)
					.getHttpServletRequest().getHeader("User-Agent")
					.toLowerCase();
		}
		// printBrowserInfo(page, webBrowser, browserInfo, userAgent);
		printBrowserInfo(page, webBrowser, userAgent);

		if (isMobileUserAgent(userAgent) || webBrowser.getScreenWidth() < 768) {
			mobilePresenter = new MobilePresenter();
			setContent(mobilePresenter.getMobileLayoutView());
			logger.trace("Mobile application initialized");
		} else {
			desktopPresenter = new DesktopPresenter();
			setPrimaryStyleName("main");
			setContent(desktopPresenter.getDesktopLayoutView());
			logger.trace("Desktop application initialized");
		}

		setData();
		// Register as DataListener to get notified if data changes
		// DataLoader.getInstance().addDataListener(this);
	}

	private void printBrowserInfo(Page page, WebBrowser webBrowser,
			String userAgent) {
		// Page page, WebBrowser webBrowser,
		// BrowserInfo browserInfo, String userAgent
		logger.trace(">> Browser Data <<");
		logger.trace("Current window width: " + page.getBrowserWindowWidth());
		logger.trace("Max browser width: " + webBrowser.getScreenWidth());
		// logger.trace("3: " + browserInfo.getScreenWidth());
		logger.trace("User Agent: " + userAgent);
	}

	private boolean isMobileUserAgent(String userAgent) {
		boolean mobileUserAgent = false;
		String[] mobileAgents = new String[] { "ipad", "iphone", "mobile",
				"android", "ios", "blackberry", "phone", "phone" };

		for (String agent : mobileAgents) {
			if (agent.equals(userAgent)) {
				mobileUserAgent = true;
			}
		}
		return mobileUserAgent;
	}

	private void setData() {
		DataLoader dataLoader = DataLoader.getInstance();
		desktopPresenter.setRoomContainer(dataLoader.getRoomContainer());
		desktopPresenter.setCourseContainer(dataLoader.getCourseContainer());
		desktopPresenter.setPersonContainer(dataLoader.getPersonContainer());
		desktopPresenter.setPoiContainer(dataLoader.getPoiContainer());
		// Register as DataListener to get notified if data changes
		DataLoader.getInstance().addDataListener(this);
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

	@Override
	public void dataUpdated() {
		setData();
	}
}