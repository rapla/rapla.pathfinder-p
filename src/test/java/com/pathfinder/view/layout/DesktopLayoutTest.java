/**
 * 
 */
package com.pathfinder.view.layout;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import com.pathfinder.PathfinderUI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
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

	@Test
	public void addClickListenerAppointmentButtonTest() {
		desktopLayout.addClickListenerAppointmentButton(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}
		});
	}

}
