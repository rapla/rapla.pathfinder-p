/**
 * 
 */
package com.pathfinder.view.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pathfinder.PathfinderUI;
import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.pathfinder.view.Keyboard;
import com.pathfinder.view.KeyboardId;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.UI;

/**
 * @author tim
 * 
 */
public class KeyboardTest {

	private final static Logger LOG = LogManager.getLogger(KeyboardTest.class);

	private Keyboard keyboard;

	private TranslatorSpec translator = Translator.getInstance();

	@Before
	public void initialize() {
		this.keyboard = new Keyboard();
	}

	@Test
	public void allButtonsAddedTest() {

		// Get root layout
		AbstractOrderedLayout rootLayout = (AbstractOrderedLayout) ((CustomComponent) keyboard)
				.iterator().next();

		// Get Ids of all Buttons
		List<KeyboardId> foundKeyboardIds = new ArrayList<KeyboardId>();
		getIdsOfAllChildren(rootLayout, foundKeyboardIds);

		// Compare all available Keyboard-Ids with found Keyboard-Ids
		List<KeyboardId> allKeyboardIds = new ArrayList<KeyboardId>();
		allKeyboardIds.addAll(Arrays.asList(KeyboardId.values()));
		allKeyboardIds.removeAll(foundKeyboardIds);

		// List all Buttons that are not used
		for (KeyboardId id : allKeyboardIds)
			LOG.debug("Not implemented Button: " + id);

		Assert.assertEquals(0, allKeyboardIds.size());

	}

	/**
	 * Helper method to find all button data recursively
	 * 
	 * @param component
	 *            Root component
	 * @param keyboardids
	 *            List of ids
	 */
	private void getIdsOfAllChildren(HasComponents component,
			List<KeyboardId> keyboardids) {
		Iterator<Component> iterator = component.iterator();
		while (iterator.hasNext()) {
			Component childComponent = iterator.next();
			if (childComponent instanceof HasComponents) {
				getIdsOfAllChildren((HasComponents) childComponent, keyboardids);
			} else {
				if (childComponent instanceof Button) {
					keyboardids.add((KeyboardId) ((Button) childComponent)
							.getData());
				}
			}
		}
	}

	private KeyboardId keyboardId = null;

	@Test
	public void updateTranslationsTest() {
		// Create UI and set English as language
		PathfinderUI ui = new PathfinderUI();
		ui.setLocale(Locale.ENGLISH);
		UI.setCurrent(ui);
		initialize();

		// Get root layout
		AbstractOrderedLayout rootLayout = (AbstractOrderedLayout) ((CustomComponent) keyboard)
				.iterator().next();

		// Get translatable Buttons
		Button deleteButton = getButtonWithDataId(rootLayout, KeyboardId.DELETE);
		Button spaceButton = getButtonWithDataId(rootLayout, KeyboardId.SPACE);

		// Check if deleteButton and spaceButton have the right translation
		String expectedTranslation = translator.translate(
				TranslationKeys.DELETE, Locale.ENGLISH);
		String actualTranslation = deleteButton.getCaption();
		Assert.assertEquals(expectedTranslation, actualTranslation);

		expectedTranslation = translator.translate(TranslationKeys.SPACE,
				Locale.ENGLISH);
		actualTranslation = spaceButton.getCaption();
		Assert.assertEquals(expectedTranslation, actualTranslation);

		// Change language to German
		ui.setLocale(Locale.GERMAN);
		keyboard.updateTranslations();

		// check if deleteButton and spaceButton have the righ translation
		expectedTranslation = translator.translate(TranslationKeys.DELETE,
				Locale.GERMAN);
		actualTranslation = deleteButton.getCaption();
		Assert.assertEquals(expectedTranslation, actualTranslation);

		expectedTranslation = translator.translate(TranslationKeys.SPACE,
				Locale.GERMAN);
		actualTranslation = spaceButton.getCaption();
		Assert.assertEquals(expectedTranslation, actualTranslation);
	}

	/**
	 * Finds Button with specified Id
	 * 
	 * @param component
	 *            Root Layout
	 * @param keyboardId
	 * @return
	 */
	private Button getButtonWithDataId(HasComponents component,
			KeyboardId keyboardId) {
		Iterator<Component> iterator = component.iterator();
		Button returnButton = null;
		while (iterator.hasNext()) {
			Component childComponent = iterator.next();
			if (childComponent instanceof HasComponents) {
				returnButton = getButtonWithDataId(
						(HasComponents) childComponent, keyboardId);
				if (returnButton != null)
					break;
			} else {
				if (childComponent instanceof Button
						&& ((Button) childComponent).getData() == keyboardId) {
					returnButton = (Button) childComponent;
				}
			}
		}
		return returnButton;
	}
}