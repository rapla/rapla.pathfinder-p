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
	private final static Logger LOGGER = LogManager
			.getLogger(PathfinderErrorHandler.class);

	@Override
	public void error(ErrorEvent event) {
		LOGGER.error("An unknown Error occured; UI will be reloaded",
				event.getThrowable());

		// Reload UI to restart application
		if (UI.getCurrent() != null)
			UI.getCurrent().close();
		if (JavaScript.getCurrent() != null)
			JavaScript.getCurrent().execute("window.location.reload();");
	}
}