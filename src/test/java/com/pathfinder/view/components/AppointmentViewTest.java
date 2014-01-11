/**
 * 
 */
package com.pathfinder.view.components;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pathfinder.PathfinderUI;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.UI;

/**
 * @author tim
 * 
 */
public class AppointmentViewTest {
	private AppointmentView appointmentView;
	private TranslatorSpec translator = Translator.getInstance();

	@Before
	public void initialize() {
		appointmentView = new AppointmentView();
	}

	@Test
	public void setUrlTest() {

		BrowserFrame browserFrame = (BrowserFrame) ((CustomComponent) appointmentView)
				.iterator().next();

		appointmentView.setAppointmentUrl("TestUrl");
		Assert.assertEquals("TestUrl",
				((ExternalResource) browserFrame.getSource()).getURL());

		appointmentView.setAppointmentUrl("");
		Assert.assertEquals("about:blank",
				((ExternalResource) browserFrame.getSource()).getURL());

	}

	@Test
	public void showHideAppointmentViewTest() {
		Assert.assertTrue(appointmentView.isVisible());
		appointmentView.hideAppointmentView();
		Assert.assertFalse(appointmentView.isVisible());
		appointmentView.showAppointmentView();
		Assert.assertTrue(appointmentView.isVisible());
	}

	// TODO reactivate, when Appointmentview finished
	// @Test
	public void updateTranslationsTest() {
		PathfinderUI ui = new PathfinderUI();
		ui.setLocale(Locale.ENGLISH);
		UI.setCurrent(ui);
		initialize();

		// String expectedTranslation = translator.translate(
		// TranslationKeys.NO_DATA_AVAILABLE, Locale.ENGLISH);
		// String actualTranslation = browserFrame.getAlternateText();
		// Assert.assertEquals(expectedTranslation, actualTranslation);
		//
		// ui.setLocale(Locale.GERMAN);
		// appointmentView.updateTranslations();
		// expectedTranslation = translator.translate(
		// TranslationKeys.NO_DATA_AVAILABLE, Locale.GERMAN);
		// actualTranslation = browserFrame.getAlternateText();
		// Assert.assertEquals(expectedTranslation, actualTranslation);
	}

}
