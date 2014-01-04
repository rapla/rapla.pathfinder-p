/**
 * 
 */
package com.pathfinder;

import org.junit.Test;

import com.vaadin.server.ErrorEvent;

/**
 * @author tim
 * 
 */
public class PathfinderErrorHandlerTest {

	@Test
	public void errorTest() {
		PathfinderErrorHandler handler = new PathfinderErrorHandler();
		ErrorEvent error = new ErrorEvent(new IllegalAccessException(
				"Oops, a very critical error occured!"));
		handler.error(error);
	}
}
