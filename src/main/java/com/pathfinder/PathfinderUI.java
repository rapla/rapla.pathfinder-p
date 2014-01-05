package com.pathfinder;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

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
		/* Browser data */
		logger.trace(">> Browser Data <<");

		/* Returns the current width of the browser window */
		Page.getCurrent().getBrowserWindowWidth();
		logger.trace("Current window width: "
				+ Page.getCurrent().getBrowserWindowWidth());

		/* Returns the max width of the browser window */
		WebBrowser browser = getPage().getWebBrowser();
		logger.trace("Max browser width: " + browser.getScreenWidth());

		/* Returns the user agent */
		String userAgent = "";
		if (request instanceof VaadinServletRequest) {
			HttpServletRequest httpRequest = ((VaadinServletRequest) request)
					.getHttpServletRequest();
			userAgent = httpRequest.getHeader("User-Agent").toLowerCase();
			logger.trace("User Agent: " + userAgent);
		}

		if (isMobileUserAgent(userAgent) || browser.getScreenWidth() < 768) {
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