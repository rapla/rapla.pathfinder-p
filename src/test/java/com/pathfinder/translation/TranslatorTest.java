/**
 * 
 */
package com.pathfinder.translation;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

/**
 * @author tim
 * 
 */
public class TranslatorTest {

	private Translator translator;

	@Before
	public void initialize() {
		this.translator = Translator.getInstance();
	}

	@Test
	public void translateTest() {
		String translation = translator.translate(TranslationKeys.ROOM,
				Locale.GERMAN);

		assertEquals("Raum", translation);

		translation = translator.translate(TranslationKeys.PERSON,
				Locale.GERMAN);
		assertEquals("Person", translation);

		translation = translator.translate(TranslationKeys.COURSE,
				Locale.ENGLISH);
		assertEquals("Class", translation);
	}

	@Test
	public void translateNotSupportedLanguageTest() {

	}
}
