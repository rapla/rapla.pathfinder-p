/**
 * 
 */
package com.pathfinder.view.components;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pathfinder.PathfinderUI;
import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.ui.UI;

/**
 * @author tim
 * 
 */
public class FreeRoomViewTest {

	private FreeRoomView freeRoomView;
	private TranslatorSpec translator = Translator.getInstance();

	@Before
	public void initialize() {
		this.freeRoomView = new FreeRoomView();
	}

	@Test
	public void showHideFreeRoomTest() {
		Assert.assertTrue(freeRoomView.isVisible());
		freeRoomView.hideFreeRoom();
		Assert.assertFalse(freeRoomView.isVisible());
		freeRoomView.showFreeRoom();
		Assert.assertTrue(freeRoomView.isVisible());
	}

	@Test
	public void updateTranslationTest() {
		PathfinderUI ui = new PathfinderUI();
		ui.setLocale(Locale.ENGLISH);
		UI.setCurrent(ui);
		initialize();

		String expectedTranslation = translator.translate(
				TranslationKeys.CURRENTLY_FREE_ROOMS, Locale.ENGLISH);
		String actualTranslation = freeRoomView.getCaption();
		Assert.assertEquals(expectedTranslation, actualTranslation);

		ui.setLocale(Locale.GERMAN);
		freeRoomView.updateTranslations();
		expectedTranslation = translator.translate(
				TranslationKeys.CURRENTLY_FREE_ROOMS, Locale.GERMAN);
		actualTranslation = freeRoomView.getCaption();
		Assert.assertEquals(expectedTranslation, actualTranslation);
	}

}
