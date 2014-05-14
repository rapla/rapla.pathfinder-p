package com.pathfinder.model;

public enum ResourceType {
	UNKNOWN("unknown", "Unknown"), ROOM("room", "Room"), COURSE("course",
			"Course"), PERSON("person", "Person"), POI("poi", "Poi");

	private final String stringValue;
	private String nameInLog;

	private ResourceType(final String text, final String nameInLog) {
		this.stringValue = text;
		this.nameInLog = nameInLog;
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

	public String getNameInLog() {
		return this.nameInLog;
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
