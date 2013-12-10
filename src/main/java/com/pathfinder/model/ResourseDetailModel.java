package com.pathfinder.model;

import java.util.Arrays;
import java.util.Map;

public class ResourseDetailModel {

	Map<String, ResourceDetailRowModel> attributeMap;
	EventModel[] events;

	public Map<String, ResourceDetailRowModel> getAttributeMap() {
		return attributeMap;
	}

	public void setAttributeMap(Map<String, ResourceDetailRowModel> attributeMap) {
		this.attributeMap = attributeMap;
	}

	@Override
	public String toString() {
		return "ResourseDetailRoomModel [attributeMap=" + attributeMap
				+ ", events=" + Arrays.toString(events) + "]";
	}

}
