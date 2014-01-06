/**
 * 
 */
package com.pathfinder.view.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pathfinder.PathfinderUI;
import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
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

	@Test
	public void refreshRoomListTest() {

		List<String> raumNameList = new ArrayList<String>();
		List<String> raumIdList = new ArrayList<String>();
		List<String> raumLinkList = new ArrayList<String>();
		List<String> startList = new ArrayList<String>();
		List<String> endList = new ArrayList<String>();
		for (int zaehler = 0; zaehler < 3; zaehler++) {
			raumNameList.add("Raum " + (zaehler + 1));
			raumIdList.add("Id " + (zaehler + 1));
			raumLinkList.add("Link " + (zaehler + 1));
			startList.add("Startzeit " + (zaehler + 1));
			endList.add("Endzeit " + (zaehler + 1));
		}
		freeRoomView.refreshFreeRooms(raumNameList, raumLinkList, raumIdList,
				startList, endList);

		AbstractLayout rootLayout = (AbstractLayout) freeRoomView.iterator()
				.next();

		// Check that all Components are added to rootLayout
		Iterator<Component> iterator = rootLayout.iterator();
		int zaehler = 0;
		while (iterator.hasNext()) {
			Label label1 = (Label) iterator.next();
			ExternalResource link1 = (ExternalResource) ((Link) iterator.next())
					.getResource();
			Assert.assertTrue(label1.getValue().contains(
					"Raum " + (zaehler + 1)));
			Assert.assertEquals("Link " + (++zaehler), link1.getURL());
		}
		Assert.assertEquals(3, zaehler);

	}
}
