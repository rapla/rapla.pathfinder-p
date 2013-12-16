package com.pathfinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pathfinder.presenter.DesktopPresenter;
import com.pathfinder.presenter.MobilePresenter;
import com.pathfinder.translation.TranslationKeys;
import com.pathfinder.translation.Translator;
import com.vaadin.annotations.Theme;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServletRequest;
import com.vaadin.server.WebBrowser;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("rapla_pathfinder_p")
public class PathfinderUI extends UI {

	private static final Logger logger = LogManager
			.getLogger(PathfinderUI.class.getName());

	@Override
	protected void init(VaadinRequest request) {
		setUiLocale(request.getLocale());
		setErrorHandler(new PathfinderErrorHandler());
		Page.getCurrent().setTitle(
				Translator.getInstance().translate(TranslationKeys.APP_TITLE));

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

		if (!isMobileUserAgent(userAgent) || browser.getScreenWidth() > 768) {
			setContent(new DesktopPresenter().getDesktopLayoutView());
			logger.trace("Desktop application initialized");
		} else {
			setContent(new MobilePresenter().getMobileLayoutView());
			logger.trace("Mobile application initialized");
		}
	}

	private boolean isMobileUserAgent(String userAgent) {
		boolean mobileUserAgent = false;
		String[] mobileAgents = new String[] { "ipad", "iphone", "mobile",
				"android", "ios", "blackberry", "phone" };
		ArrayList<String> mobileAgentList = new ArrayList<String>(
				Arrays.asList(mobileAgents));

		for (String agent : mobileAgentList) {
			if (agent.equals(userAgent)) {
				mobileUserAgent = true;
			}
		}
		return mobileUserAgent;
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

}