package com.pathfinder.model;

public enum AttributeKey {
	UNKNOWN("unkown"), NAME_KEY("name"), RESOURCE_URL_KEY("resourceURL"), ROOM_NR_KEY(
			"raumnr"), ROOM_TYPE_KEY("raumart"), YEAR_KEY("jahrgang"), PICTURE_NAME_KEY(
			"bild"), COURSE_OF_STUDIES_KEY("abteilung"), EMAIL_KEY("email"), PHONE_KEY(
			"telefon"), INFO_KEY("info"), LINE_KEY("zeile"), LOCATION(
			"location");

	private String key;

	private AttributeKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return this.key;
	}

	public String getKeyLowerCase() {
		return this.key.toLowerCase();
	}

	public boolean equals(String value) {
		if (value != null && value.toLowerCase().equals(key.toLowerCase())) {
			return true;
		}
		return false;
	}

	public static AttributeKey getKey(String value) {
		AttributeKey result = UNKNOWN;
		for (AttributeKey key : values()) {
			if (key.equals(value)) {
				result = key;
				break;
			}
		}
		return result;
	}
}
