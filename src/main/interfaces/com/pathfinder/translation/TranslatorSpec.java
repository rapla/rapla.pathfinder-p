/**
 * 
 */
package com.pathfinder.translation;

import java.util.Locale;

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
	public abstract String translate(TranslationKeys key, Locale locale);

	/**
	 * Looks up translation for specified key; uses current locale of UI. If UI
	 * not yet instantiated, default locale will be taken instead.
	 * 
	 * @param key
	 *            Key for looking up translations
	 * @return translation or empty String, if language not supported
	 */
	@Pure
	public abstract String translate(TranslationKeys key);

	/**
	 * Returns hard coded default locale; notice: the default locale will not be
	 * updated during runtime
	 * 
	 * @return hard coded default locale
	 */
	@Pure
	public abstract Locale getDefaultLocale();

}