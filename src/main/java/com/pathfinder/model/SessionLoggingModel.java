/**
 * 
 */
package com.pathfinder.model;

import java.util.Date;

import com.pathfinder.presenter.DataLoader;

/**
 * @author tim
 * 
 */
public class SessionLoggingModel {

	private static int clientCount = 1000000;

	private String SEPARATOR = ";";

	private static DataLoader dataLoader = DataLoader.getInstance();

	public enum ClickOrigin {
		FREE_ROOMS("FreeRooms"), SEARCH_RESULTS("SearchResults"), CALENDAR(
				"Calendar"), DETAILS("Details");

		private ClickOrigin(String nameInLog) {
			this.nameInLog = nameInLog;
		}

		private String nameInLog;

		public String getNameInLog() {
			return this.nameInLog;
		}
	}

	private String sessionId;
	private ResourceType resourceType;
	private String resourceName;
	private ClickOrigin clickOrigin;
	private String searchString;
	private Device device;

	public SessionLoggingModel(Device device) {
		this.sessionId = "" + new Date().getTime() + ++clientCount;
		this.device = device;
	}

	public void sendLoggingInfoToRapla(ResourceModel resource,
			ClickOrigin clickOrigin, String searchString) {
		if (resource != null) {
			this.resourceType = resource.getType();
			this.resourceName = resource.getName();
			this.clickOrigin = clickOrigin;
			this.searchString = searchString;

			dataLoader.sendLoggingInfoToRapla(getLoggingString());
		}
	}

	private String getLoggingString() {
		StringBuilder result = new StringBuilder();
		result.append(sessionId + SEPARATOR);
		result.append(device.getNameInLog() + SEPARATOR);
		result.append(resourceType.getNameInLog() + SEPARATOR);
		result.append(resourceName + SEPARATOR);
		result.append(clickOrigin.getNameInLog() + SEPARATOR);
		result.append(searchString);
		return removeWhiteSpaces(result.toString());
	}

	private String removeWhiteSpaces(String string) {
		return string.replace(" ", "%20");
	}
}
