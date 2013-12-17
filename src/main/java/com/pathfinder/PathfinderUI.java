package com.pathfinder;

import javax.servlet.annotation.WebServlet;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

import com.pathfinder.presenter.MainPresenter;
import com.pathfinder.translation.Translator;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.external.org.slf4j.Logger;
import com.vaadin.external.org.slf4j.LoggerFactory;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletRequest;
import com.vaadin.server.WebBrowser;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("rapla_pathfinder_p")
public class PathfinderUI extends UI {
	
	

	// Logger logger = LogManager.getLogger(PathfinderUI.class.getName());

	private Logger log = LoggerFactory.getLogger(getClass());

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = PathfinderUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {

		// Setting locale of UI as locale of request, if locale supported by
		// translator and not null; otherwise use fallback locale
	
		this.setStyleName("main");
		if (request.getLocale() != null
				&& Translator.getInstance().isLocaleSupported(
						request.getLocale())) {
			setLocale(request.getLocale());
		} else {
			setLocale(Translator.getInstance().getFallbackLocale());
		}

		/* Method 1 */
		Page.getCurrent().getBrowserWindowWidth();

		/* Method 2 */
		WebBrowser browser = getPage().getWebBrowser();

		// if (browser.getScreenWidth() > 1024 && browser.getScreenHeight() >
		// 768) {
		// // TODO
		// } else {
		// // TODO
		// }

		/* Method 3 */
		if (request instanceof VaadinServletRequest) {
			HttpServletRequest httpRequest = ((VaadinServletRequest) request)
					.getHttpServletRequest();
			String userAgent = httpRequest.getHeader("User-Agent")
					.toLowerCase();

			// TODO: Check user agent for all tablet matching keywords
			if (userAgent.contains("ipad")) {
				// ...
			}

		}

		setContent(new MainPresenter().getMainLayoutView());
		// logger.trace("Application initialized");
		log.debug("Log");
	}

}