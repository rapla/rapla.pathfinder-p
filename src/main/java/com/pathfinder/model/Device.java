/**
 * 
 */
package com.pathfinder.model;

/**
 * @author tim
 * 
 */
public enum Device {
	DESKTOP("desktop", "", "Desktop"), MOBILE("mobile", "", "Mobile"), STELE_LEFT(
			"stelel", "L", "SteleL"), STELE_RIGHT("steler", "R", "SteleR"), STELE_MIDDLE(
			"stelem", "", "SteleM"), UNDEFINED("", "", "Unknown");

	private String urlPicturePrefix;
	private String urlParameterValue;
	private String nameInLog;
	public final static String DEVICE_URL_PARAMETER_NAME = "device";

	private Device(String urlParameterValue, String urlPicturePrefix,
			String nameInLog) {
		this.urlParameterValue = urlParameterValue;
		this.urlPicturePrefix = urlPicturePrefix;
		this.nameInLog = nameInLog;
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

	public String getNameInLog() {
		return this.nameInLog;
	}

	public boolean isStele() {
		return this == STELE_LEFT || this == STELE_MIDDLE
				|| this == STELE_RIGHT;
	}
}
