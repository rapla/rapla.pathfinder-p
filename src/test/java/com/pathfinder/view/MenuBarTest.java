/**
 * 
 */
package com.pathfinder.view;

import java.util.Iterator;
import java.util.Locale;

import org.junit.Before;

import com.pathfinder.PathfinderUI;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.UI;

/**
 * @author tim
 * 
 */
public class MenuBarTest {

	private MenuBar menuBar;
	private PathfinderUI ui = new PathfinderUI();

	@Before
	public void initialize() {
		ui = new PathfinderUI();
		ui.setLocale(Locale.ENGLISH);
		UI.setCurrent(ui);

		menuBar = new MenuBar();

		Component rootLayout = menuBar.iterator().next();

		Iterator<Component> iterator = ((HasComponents) rootLayout).iterator();
		iterator.next();
		// Button appointmentButton = (Button) iterator.next();
	}

}
