/**
 * 
 */
package com.pathfinder.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author tim
 * 
 */
public class SessionLoggingModel {
	private boolean beginningOfSession = true;
	private List<String> searchStrings = new ArrayList<>();
	private List<ResourceModel> clickedResources = new ArrayList<>();
	private String device = "";
	private Date startTime = new Date();
	private Date endTime = new Date();

	public List<String> getSearchStrings() {
		return searchStrings;
	}

	public List<ResourceModel> getClickedResources() {
		return clickedResources;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
		this.beginningOfSession = false;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
		this.beginningOfSession = true;
	}

	public boolean isBeginningOfSession() {
		return beginningOfSession;
	}

	public void clearValues() {
		this.searchStrings.clear();
		this.clickedResources.clear();
	}

	public String toString() {
		// TODO: Remove this ugly toString method if 'real' logging is
		// implemented ;)
		StringBuilder stringBuilder = new StringBuilder("\n");
		stringBuilder
				.append("===================================================\n");
		stringBuilder.append("Duration of Session: "
				+ (endTime.getTime() - startTime.getTime()) + "ms \n");
		stringBuilder.append("User agent: " + device + "\n");
		for (String searchString : searchStrings) {
			stringBuilder.append("Search String: " + searchString + "\n");
		}
		for (ResourceModel resourceModel : clickedResources) {
			if (resourceModel != null) {
				stringBuilder.append("Clicked Resource: "
						+ resourceModel.getName() + "\n");
			}
		}
		stringBuilder
				.append("===================================================\n");
		return stringBuilder.toString();
	}
}
