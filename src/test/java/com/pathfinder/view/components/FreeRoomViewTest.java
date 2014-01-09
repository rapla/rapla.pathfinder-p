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
		freeRoomView.hideFreeRoomView();
		Assert.assertFalse(freeRoomView.isVisible());
		freeRoomView.showFreeRoomView();
		Assert.assertTrue(freeRoomView.isVisible());
	}

	@Test
	public void updateTranslationTest() {
		PathfinderUI ui = new PathfinderUI();
		ui.setLocale(Locale.ENGLISH);
		UI.setCurrent(ui);
		initialize();

		// TODO
		// String expectedTranslation = translator.translate(
		// TranslationKeys.CURRENTLY_FREE_ROOMS, Locale.ENGLISH);
		// String actualTranslation =
		// freeRoomView.getFreeRoomLabel().getCaption();
		// Assert.assertEquals(expectedTranslation, actualTranslation);
		//
		// ui.setLocale(Locale.GERMAN);
		// freeRoomView.updateTranslations();
		// expectedTranslation = translator.translate(
		// TranslationKeys.CURRENTLY_FREE_ROOMS, Locale.GERMAN);
		// actualTranslation = freeRoomView.getFreeRoomLabel().getCaption();
		// Assert.assertEquals(expectedTranslation, actualTranslation);
	}

	@Test
	public void refreshRoomListTest() {
		// TODO
		// List<String> raumNameList = new ArrayList<String>();
		// List<String> raumIdList = new ArrayList<String>();
		// List<String> raumLinkList = new ArrayList<String>();
		// List<String> startList = new ArrayList<String>();
		// List<String> endList = new ArrayList<String>();
		// for (int zaehler = 0; zaehler < 3; zaehler++) {
		// raumNameList.add("Raum " + (zaehler + 1));
		// raumIdList.add("Id " + (zaehler + 1));
		// raumLinkList.add("Link " + (zaehler + 1));
		// startList.add("Startzeit " + (zaehler + 1));
		// endList.add("Endzeit " + (zaehler + 1));
		// }
		// freeRoomView.refreshFreeRooms(raumNameList, raumLinkList, raumIdList,
		// startList, endList);
		//
		// AbstractLayout rootLayout = (AbstractLayout) freeRoomView.iterator()
		// .next();
		// Iterator<Component> rootLayoutIterator = rootLayout.iterator();
		// rootLayoutIterator.next();
		// GridLayout gridLayout = (GridLayout) rootLayoutIterator.next();
		//
		// // Check that all Components are added to rootLayout
		// Iterator<Component> iterator = gridLayout.iterator();
		// int zaehler = 0;
		// Label label1 = (Label) iterator.next();
		// ExternalResource link1 = (ExternalResource) ((Link) iterator.next())
		// .getResource();
		// Assert.assertTrue(label1.getValue().contains("Raum " + (zaehler +
		// 1)));
		// Assert.assertEquals("Link " + (++zaehler), link1.getURL());
		//
		// Label label2 = (Label) iterator.next();
		// ExternalResource link2 = (ExternalResource) ((Link) iterator.next())
		// .getResource();
		// Assert.assertTrue(label2.getValue().contains("Raum " + (zaehler +
		// 1)));
		// Assert.assertEquals("Link " + (++zaehler), link2.getURL());
		//
		// Label label3 = (Label) iterator.next();
		// ExternalResource link3 = (ExternalResource) ((Link) iterator.next())
		// .getResource();
		// Assert.assertTrue(label3.getValue().contains("Raum " + (zaehler +
		// 1)));
		// Assert.assertEquals("Link " + (++zaehler), link3.getURL());
		//
		// Assert.assertEquals(3, zaehler);

	}
}
