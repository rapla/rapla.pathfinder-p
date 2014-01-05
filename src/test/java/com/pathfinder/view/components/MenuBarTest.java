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
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.UI;

/**
 * @author tim
 * 
 */
public class MenuBarTest {

	private MenuBar menuBar;
	private NativeSelect dropDown;
	private Button appointmentButton;
	private TranslatorSpec translator = Translator.getInstance();

	@Before
	public void initialize() {
		this.menuBar = new MenuBar();

		AbstractOrderedLayout rootLayout = (AbstractOrderedLayout) ((CustomComponent) menuBar)
				.iterator().next();
		Iterator<Component> iterator = rootLayout.iterator();
		dropDown = (NativeSelect) iterator.next();
		appointmentButton = (Button) iterator.next();
	}

	@Test
	public void addValueChangeListenerTest() {
		ValueChangeListener listener = new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				// Dummy method
			}
		};

		menuBar.addValueChangeListener(listener);

		// Check if above defined listener is the same as Dropdown's listener
		Assert.assertEquals(listener,
				dropDown.getListeners(ValueChangeEvent.class).iterator().next());

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

		menuBar.addClickListenerAppointmentButton(listener);

		// Check if above defined listener is the same as Button's listener
		Assert.assertEquals(listener,
				appointmentButton.getListeners(ClickEvent.class).iterator()
						.next());

		appointmentButton.click();

		Assert.assertTrue(buttonclicked);
	}

	@Test
	public void showHideAppointmentButtonTest() {
		Assert.assertTrue(appointmentButton.isVisible());

		menuBar.hideAppointmentButton();
		Assert.assertFalse(appointmentButton.isVisible());

		menuBar.showAppointmentButton();
		Assert.assertTrue(appointmentButton.isVisible());
	}

	@Test
	public void getLanguagesTest() {
		Assert.assertTrue(menuBar.getLanguages().length > 0);
	}

	@Test
	public void updateTranslationsTest() {
		PathfinderUI ui = new PathfinderUI();
		ui.setLocale(Locale.ENGLISH);
		UI.setCurrent(ui);
		initialize();

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
