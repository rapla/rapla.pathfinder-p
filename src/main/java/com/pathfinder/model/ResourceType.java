package com.pathfinder.model;

public enum ResourceType {
	UNKNOWN("unknown"), ROOM("room"), COURSE("course"), PERSON("person"), POI(
			"poi");

	private final String stringValue;

	private ResourceType(final String text) {
		this.stringValue = text;
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

	public static ResourceType getResourceByString(String value) {
		ResourceType result = UNKNOWN;
		for (ResourceType typ : values()) {
			if (typ.equals(value)) {
				result = typ;
				break;
			}
		}
		return result;
	}
}
