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
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

/**
 * @author tim
 * 
 */
public class SearchFieldTest {

	private SearchFieldSpec searchFieldSpec;
	private Button magnifier;
	private TextField searchField;
	private Button deleteAll;
	private TranslatorSpec translator = Translator.getInstance();

	@Before
	public void initialize() {
		searchFieldSpec = new SearchField();
		Iterator<Component> iterator = ((AbstractOrderedLayout) (((CustomComponent) searchFieldSpec)
				.iterator().next())).iterator();
		magnifier = (Button) iterator.next();
		searchField = (TextField) iterator.next();
		deleteAll = (Button) iterator.next();
	}

	@Test
	public void updateTranslationsTest() {
		PathfinderUI ui = new PathfinderUI();
		ui.setLocale(Locale.ENGLISH);
		UI.setCurrent(ui);
		initialize();

		String expectedTranslation = translator.translate(
				TranslationKeys.SEARCH_PROMP, Locale.ENGLISH);
		String actualTranslation = searchField.getInputPrompt();
		Assert.assertEquals(expectedTranslation, actualTranslation);

		ui.setLocale(Locale.GERMAN);
		searchFieldSpec.updateTranslations();
		expectedTranslation = translator.translate(
				TranslationKeys.SEARCH_PROMP, Locale.GERMAN);
		actualTranslation = searchField.getInputPrompt();
		Assert.assertEquals(expectedTranslation, actualTranslation);

	}

	@Test
	public void getSearchFieldTest() {
		Assert.assertEquals(searchField, searchFieldSpec.getSearchField());
	}

	@Test
	public void addSearchFieldListenerTest() {

		TextChangeListener listener = new TextChangeListener() {

			@Override
			public void textChange(TextChangeEvent event) {
				// dummy event
			}
		};

		searchFieldSpec.addSearchFieldListener(listener);

		searchField.getListeners(TextChangeEvent.class);

		Assert.assertEquals(listener,
				searchField.getListeners(TextChangeEvent.class).iterator()
						.next());
	}

	@Test
	public void addMagnifierClickListenerTest() {

		ClickListener listener = new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// dummy event
			}
		};

		searchFieldSpec.addMagnifierClickListener(listener);

		Assert.assertEquals(listener, magnifier.getListeners(ClickEvent.class)
				.iterator().next());

	}

	public void addDeleteAllClickListener() {

		ClickListener listener = new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// dummy event

			}
		};

		searchFieldSpec.addDeleteAllClickListener(listener);

		Assert.assertEquals(listener, deleteAll.getListeners(ClickEvent.class)
				.iterator().next());
	}
}
