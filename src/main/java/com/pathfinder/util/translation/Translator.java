/**
 * 
 */
package com.pathfinder.util.translation;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.ui.UI;

/**
 * Utility class for translating; translations are kept in
 * translations_xx.properties File where xx is the ISO-presentation of a locale:
 * e.g. de for German
 * 
 * @author tim
 * 
 */
public class Translator implements TranslatorSpec {

	/**
	 * Logging instance of this class
	 */
	private final static Logger LOG = LogManager.getLogger(Translator.class);

	/**
	 * Locale which will be taken, if no other locale specified
	 */
	private static final Locale FALLBACK_LOCALE = Locale.GERMAN;

	/**
	 * Single instance of this class
	 */
	private static TranslatorSpec instance;

	/**
	 * Prefix of all filenames which contain translation pairs
	 */
	private final static String TRANSLATION_FILE_PREFIX = "translations";

	/**
	 * Contains the ResourceBundle for each language
	 */
	private Map<String, ResourceBundle> bundles = new HashMap<String, ResourceBundle>();

	/**
	 * Private constructor; when singleton is instantiated, all the resource
	 * bundles will be loaded once
	 */
	private Translator() {
		bundles.put(Locale.GERMAN.getLanguage(), ResourceBundle.getBundle(
				TRANSLATION_FILE_PREFIX, Locale.GERMAN));
		bundles.put(Locale.ENGLISH.getLanguage(), ResourceBundle.getBundle(
				TRANSLATION_FILE_PREFIX, Locale.ENGLISH));
		bundles.put(Locale.FRENCH.getLanguage(), ResourceBundle.getBundle(
				TRANSLATION_FILE_PREFIX, Locale.FRENCH));
		bundles.put("es", ResourceBundle.getBundle(TRANSLATION_FILE_PREFIX,
				new Locale("es")));
	}

	/**
	 * Returns the single instance of this class
	 * 
	 * @return single instance
	 */
	public static TranslatorSpec getInstance() {
		if (instance == null) {
			instance = new Translator();
		}
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pathfinder.translation.TranslatorSpec#translate(com.pathfinder.
	 * translation.TranslationKeys, java.util.Locale)
	 */
	@Override
	public String translate(TranslationKeys key, Locale locale) {
		String translation = "";
		ResourceBundle bundle = bundles.get(locale.getLanguage());
		if (bundle != null) {
			try {
				translation = bundle.getString(key.toString());
			} catch (MissingResourceException mre) {
				LOG.error("No translation key found! Key: " + key
						+ "; Resource-File: " + TRANSLATION_FILE_PREFIX + "_"
						+ locale.getLanguage() + ".properties");
			}
		}
		return translation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pathfinder.translation.TranslatorSpec#translate(com.pathfinder.
	 * translation.TranslationKeys)
	 */
	@Override
	public String translate(TranslationKeys key) {
		Locale locale;
		if (UI.getCurrent() != null) {
			locale = UI.getCurrent().getLocale();
		} else {
			// UI not yet instantiated (e.g. when testing)
			locale = FALLBACK_LOCALE;
		}
		return translate(key, locale);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pathfinder.translation.TranslatorSpec#getDefaultLocale()
	 */
	@Override
	public Locale getFallbackLocale() {
		return FALLBACK_LOCALE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pathfinder.translation.TranslatorSpec#isLocaleSupported(java.util
	 * .Locale)
	 */
	@Override
	public boolean isLocaleSupported(Locale locale) {
		return locale != null && bundles.containsKey(locale.getLanguage());
	}

}
