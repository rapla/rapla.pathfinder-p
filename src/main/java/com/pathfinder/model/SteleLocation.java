/**
 * 
 */
package com.pathfinder.model;

/**
 * @author tim
 * 
 */
public enum SteleLocation {
	LEFT("l", "l"), RIGHT("r", "r"), MIDDLE("m", "");

	private String urlPicturePrefix;
	private String urlParameterValue;
	public final static String STELE_LOCATION_URL_PARAMETER = "stele";

	private SteleLocation(String urlParameterValue, String urlPicturePrefix) {
		this.urlParameterValue = urlParameterValue;
		this.urlPicturePrefix = urlPicturePrefix;
	}

	public String getUrlPicturePrefix() {
		return urlPicturePrefix;
	}

	public String getUrlParameterValue() {
		return urlParameterValue;
	}

	public static SteleLocation getSteleLocation(String urlParameterValue) {
		SteleLocation result = MIDDLE;
		for (SteleLocation location : values()) {
			if (location.getUrlParameterValue().equals(urlParameterValue)) {
				result = location;
				break;
			}
		}
		return result;
	}

	public String toString() {
		return this.urlPicturePrefix;
	}
}
