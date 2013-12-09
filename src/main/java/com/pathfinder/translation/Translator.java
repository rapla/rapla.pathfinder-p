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
 * Utility class for translating; translations are kept in
 * translations_xx.properties File where xx is the ISO-presentation of a locale:
 * e.g. de for German
 * 
 * @author tim
 * 
 */
public class Translator implements TranslatorSpec {

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
			translation = bundle.getString(key.toString());
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
