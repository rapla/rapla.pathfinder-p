/**
 * 
 */
package com.pathfinder.model;

/**
 * @author tim
 * 
 */
public enum Device {
	DESKTOP("desktop", ""), MOBILE("mobile", ""), STELE_LEFT("stelel", "l"), STELE_RIGHT(
			"steler", "r"), STELE_MIDDLE("stelem", ""), UNDEFINED("", "");

	private String urlPicturePrefix;
	private String urlParameterValue;
	public final static String DEVICE_URL_PARAMETER_NAME = "device";

	private Device(String urlParameterValue, String urlPicturePrefix) {
		this.urlParameterValue = urlParameterValue;
		this.urlPicturePrefix = urlPicturePrefix;
	}

	public String getUrlPicturePrefix() {
		return urlPicturePrefix;
	}

	public String getUrlParameterValue() {
		return urlParameterValue;
	}

	public static Device getDevice(String urlParameterValue) {
		Device result = Device.UNDEFINED;
		for (Device location : values()) {
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

	public boolean isStele() {
		return this == STELE_LEFT || this == STELE_MIDDLE
				|| this == STELE_RIGHT;
	}
}
