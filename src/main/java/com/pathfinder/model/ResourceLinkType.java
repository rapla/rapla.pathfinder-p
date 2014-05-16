package com.pathfinder.model;

import com.pathfinder.util.translation.TranslationKeys;

public enum ResourceLinkType {
	UNKNOWN("unknown", TranslationKeys.UNKNOWN_RESOURCE_LABEL), ROOM("raum",
			TranslationKeys.ROOM);

	private final String stringValue;
	private TranslationKeys translationKey;

	private ResourceLinkType(final String text,
			final TranslationKeys translationKey) {
		this.stringValue = text;
		this.translationKey = translationKey;
	}

	@Override
	public String toString() {
		return stringValue;
	}

	public boolean equals(String name) {
		boolean result = false;
		if (name != null) {
			result = this.stringValue.toLowerCase().equals(name.toLowerCase());
		}
		return result;
	}

	public TranslationKeys getTranslationKey() {
		return this.translationKey;
	}

	public static ResourceLinkType getTypeByString(String value) {
		ResourceLinkType result = UNKNOWN;
		for (ResourceLinkType typ : values()) {
			if (typ.equals(value)) {
				result = typ;
				break;
			}
		}
		return result;
	}
}
