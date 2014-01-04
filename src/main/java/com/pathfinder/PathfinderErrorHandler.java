/**
 * 
 */
package com.pathfinder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.ErrorEvent;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.UI;

/**
 * Handles exceptions in UI at highest level
 * 
 * @author tim
 * 
 */
public class PathfinderErrorHandler extends DefaultErrorHandler {

	/**
	 * Logging instance of this class
	 */
	private final static Logger LOG = LogManager
			.getLogger(PathfinderErrorHandler.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.server.DefaultErrorHandler#error(com.vaadin.server.ErrorEvent)
	 */
	@Override
	public void error(ErrorEvent event) {
		LOG.error("An unknown Error occured; UI will be reloaded",
				event.getThrowable());

		// Reload UI to restart application
		if (UI.getCurrent() != null)
			UI.getCurrent().close();
		if (JavaScript.getCurrent() != null)
			JavaScript.getCurrent().execute("window.location.reload();");
	}
}
