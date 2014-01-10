/**
 * 
 */
package com.pathfinder.util.translation;

import java.util.List;
import java.util.Locale;

import com.pathfinder.translation.TranslatorSpecContract;

import de.vksi.c4j.ContractReference;
import de.vksi.c4j.Pure;

/**
 * Specifies methods a translation-utility-class should have
 * 
 * @author tim
 * 
 */
@ContractReference(TranslatorSpecContract.class)
public interface TranslatorSpec {

	/**
	 * Looks up translation for specified key and specified locale
	 * 
	 * @param key
	 *            Key for looking up translations
	 * @param locale
	 *            Target language
	 * @return translation or empty String, if language not supported
	 */
	@Pure
	String translate(TranslationKeys key, Locale locale);

	/**
	 * Looks up translation for specified key; uses current locale of UI. If UI
	 * not yet instantiated, default locale will be taken instead.
	 * 
	 * @param key
	 *            Key for looking up translations
	 * @return translation or empty String, if language not supported
	 */
	@Pure
	String translate(TranslationKeys key);

	/**
	 * Returns hard coded fallback locale; notice: the fallback locale will not
	 * be updated during runtime
	 * 
	 * @return hard coded default locale
	 */
	@Pure
	Locale getFallbackLocale();

	/**
	 * Checks, if specified locale is among the list of supported locales
	 * 
	 * @param locale
	 *            locale to be tested
	 * @return true, if locale is supported, false, if not or if given locale is
	 *         null
	 */
	@Pure
	boolean isLocaleSupported(Locale locale);

	/**
	 * 
	 * @return list of all supported locales
	 */
	@Pure
	List<Locale> getSupportedLocales();

}