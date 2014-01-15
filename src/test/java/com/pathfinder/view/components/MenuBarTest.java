/**
 * 
 */
package com.pathfinder.view.components;

import java.util.Iterator;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pathfinder.PathfinderUI;
import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.UI;

/**
 * @author tim
 * 
 */
public class MenuBarTest {

	private MenuBar menuBar;
	private Button appointmentButton;
	private PathfinderUI ui = new PathfinderUI();
	private TranslatorSpec translator = Translator.getInstance();

	@Before
	public void initialize() {
		ui = new PathfinderUI();
		ui.setLocale(Locale.ENGLISH);
		UI.setCurrent(ui);

		menuBar = new MenuBar();

		Component rootLayout = menuBar.iterator().next();

		Iterator<Component> iterator = ((HasComponents) rootLayout).iterator();
		iterator.next();
		appointmentButton = (Button) iterator.next();
	}

	private boolean buttonclicked = false;

	@Test
	public void addClickListenerAppointmentButtonTest() {
		ClickListener listener = new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				buttonclicked = true;
			}
		};

		// Check if above defined listener is the same as Button's listener
		Assert.assertEquals(listener,
				appointmentButton.getListeners(ClickEvent.class).iterator()
						.next());

		appointmentButton.click();

		Assert.assertTrue(buttonclicked);
	}

	@Test
	public void updateTranslationsTest() {

		String expectedTranslation = translator.translate(
				TranslationKeys.EVENT, Locale.ENGLISH);
		String actualTranslation = appointmentButton.getCaption();
		Assert.assertEquals(expectedTranslation, actualTranslation);

		ui.setLocale(Locale.GERMAN);
		menuBar.updateTranslations();
		expectedTranslation = translator.translate(TranslationKeys.EVENT,
				Locale.GERMAN);
		actualTranslation = appointmentButton.getCaption();
		Assert.assertEquals(expectedTranslation, actualTranslation);

	}
}
