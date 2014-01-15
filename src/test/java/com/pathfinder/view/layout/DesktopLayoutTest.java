/**
 * 
 */
package com.pathfinder.view.layout;

import java.util.Locale;

import org.junit.Before;

import com.pathfinder.PathfinderUI;
import com.vaadin.ui.UI;

/**
 * @author tim
 * 
 */
public class DesktopLayoutTest {

	private DesktopLayout desktopLayout;

	@Before
	public void initialize() {
		PathfinderUI ui = new PathfinderUI();
		ui.setLocale(Locale.GERMAN);
		UI.setCurrent(ui);

		this.desktopLayout = new DesktopLayout();
	}
}
