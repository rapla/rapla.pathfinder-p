/**
 * 
 */
package com.pathfinder.view;

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
	private TextField searchField;
	private Button deleteAll;
	private TranslatorSpec translator = Translator.getInstance();

	@Before
	public void initialize() {
		searchFieldSpec = new SearchField();
		Iterator<Component> iterator = ((AbstractOrderedLayout) (((CustomComponent) searchFieldSpec)
				.iterator().next())).iterator();
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
				TranslationKeys.SEARCH_PROMPT, Locale.ENGLISH);
		String actualTranslation = searchField.getInputPrompt();
		Assert.assertEquals(expectedTranslation, actualTranslation);

		ui.setLocale(Locale.GERMAN);
		searchFieldSpec.updateTranslations();
		expectedTranslation = translator.translate(
				TranslationKeys.SEARCH_PROMPT, Locale.GERMAN);
		actualTranslation = searchField.getInputPrompt();
		Assert.assertEquals(expectedTranslation, actualTranslation);

	}

	@Test
	public void getSearchFieldTest() {
		Assert.assertEquals(searchField, searchFieldSpec.getSearchField());
	}

	@Test
	public void addSearchFieldListenerTest() {

		TextChangeListener textChangelistener = new TextChangeListener() {

			@Override
			public void textChange(TextChangeEvent event) {

			}
		};

		searchFieldSpec.addSearchFieldTextChangeListener(textChangelistener);

		searchField.getListeners(TextChangeEvent.class);

		Assert.assertEquals(textChangelistener,
				searchField.getListeners(TextChangeEvent.class).iterator()
						.next());
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