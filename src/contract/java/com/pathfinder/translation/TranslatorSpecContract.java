/**
 * 
 */
package com.pathfinder.translation;

import static de.vksi.c4j.Condition.ignored;
import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;
import static de.vksi.c4j.Condition.unchanged;

import java.util.Locale;

import com.pathfinder.util.translation.TranslationKeys;
import com.vaadin.ui.UI;

import de.vksi.c4j.ClassInvariant;
import de.vksi.c4j.Condition;
import de.vksi.c4j.Target;

/**
 * 
 * 
 * @author tim
 * 
 */
public class TranslatorSpecContract implements TranslatorSpec {

	@Target
	private TranslatorSpec target;

	@ClassInvariant
	public void classInvariant() {
		assert unchanged(target);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pathfinder.translation.TranslatorSpec#translate(com.pathfinder.
	 * translation.TranslationKeys, java.util.Locale)
	 */
	@Override
	public String translate(TranslationKeys key, Locale locale) {
		if (preCondition()) {
			assert key != null : "key not null";
			assert locale != null : "locale not null";
		}
		if (postCondition()) {
			String result = Condition.result();
			assert result != null : "result not null";
		}
		return ignored();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pathfinder.translation.TranslatorSpec#translate(com.pathfinder.
	 * translation.TranslationKeys)
	 */
	@Override
	public String translate(TranslationKeys key) {
		if (preCondition()) {
			assert key != null : "key not null";
		}
		if (postCondition()) {
			String result = Condition.result();
			assert result != null : "result not null";
			if (UI.getCurrent() != null) {
				assert result.equals(target.translate(key, UI.getCurrent()
						.getLocale())) : "if locale specified in UI, result must equal the translation for that locale";
			} else {
				assert result.equals(target.translate(key,
						target.getFallbackLocale())) : "if locale not specified in UI, result must equal the translation for the default locale";
			}
		}
		return ignored();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pathfinder.translation.TranslatorSpec#getDefaultLocale()
	 */
	@Override
	public Locale getFallbackLocale() {
		if (postCondition()) {
			Locale result = Condition.result();
			assert result != null : "result not null";
		}
		return ignored();
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
		return ignored();
	}

}
