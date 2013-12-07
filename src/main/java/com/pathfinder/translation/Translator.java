/**
 * 
 */
package com.pathfinder.translation;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import com.vaadin.ui.UI;

/**
 * @author tim
 * 
 */
public class Translator {

	/**
	 * Single instance of this class
	 */
	private static Translator instance;

	/**
	 * Prefix of all filenames which contain translation pairs
	 */
	private final static String TRANSLATION_FILE_PREFIX = "translations";

	/**
	 * Contains the ResourceBundle for each language
	 */
	private Map<String, ResourceBundle> bundles = new HashMap<String, ResourceBundle>();

	private Translator() {
		bundles.put(Locale.GERMAN.getLanguage(), ResourceBundle.getBundle(
				TRANSLATION_FILE_PREFIX, Locale.GERMAN));
		bundles.put(Locale.ENGLISH.getLanguage(), ResourceBundle.getBundle(
				TRANSLATION_FILE_PREFIX, Locale.ENGLISH));
	}

	/**
	 * Returns the single instance of this class
	 * 
	 * @return single instance
	 */
	public static Translator getInstance() {
		if (instance == null) {
			instance = new Translator();
		}
		return instance;
	}

	/**
	 * Looks up translation for specified key and specified locale
	 * 
	 * @param key
	 *            Key for looking up translations
	 * @param locale
	 *            Target language
	 * @return translation or empty String, if language not supported
	 */
	public String translate(TranslationKeys key, Locale locale) {
		String translation = "";
		ResourceBundle bundle = bundles.get(locale.getLanguage());
		if (bundle != null) {
			translation = bundle.getString(key.toString());
		}
		return translation;
	}

	public String translate(TranslationKeys key) {
		return translate(key, UI.getCurrent().getLocale());
	}

}
