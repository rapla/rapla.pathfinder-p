/**
 * 
 */
package com.pathfinder.translation;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;

/**
 * @author tim
 * 
 */
public class TranslatorTest {

	private TranslatorSpec translator;

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
		assertEquals("Course", translation);
	}

	@Test
	public void translateTestForCurrentLocale() {
		String translation = translator.translate(TranslationKeys.ROOM);

		assertEquals("Raum", translation);

		translation = translator.translate(TranslationKeys.PERSON);
		assertEquals("Person", translation);
	}

	@Test
	public void getDefaultLocaleTest() {
		assertEquals(Locale.GERMAN, translator.getFallbackLocale());
	}

	@Test
	public void translateNotSupportedLanguageTest() {
		String translation = translator.translate(TranslationKeys.COURSE,
				Locale.CHINESE);
		assertEquals("", translation);
	}

	@Test
	public void localeSupportedTest() {
		assertEquals(false, translator.isLocaleSupported(Locale.CHINESE));
		assertEquals(true, translator.isLocaleSupported(Locale.ENGLISH));
	}

	public void tranlateMissingKey() {
		assertEquals("", translator.translate(TranslationKeys.INVALID_KEY));
	}

	public void tranlateMissingKeyWhenLocaleSpecified() {
		assertEquals("", translator.translate(TranslationKeys.INVALID_KEY,
				Locale.ENGLISH));
	}
}
