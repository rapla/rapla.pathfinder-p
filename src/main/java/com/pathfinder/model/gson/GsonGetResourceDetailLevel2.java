package com.pathfinder.model.gson;

import java.util.Arrays;
import java.util.Map;

public class GsonGetResourceDetailLevel2 {

	Map<String, GsonGetResourceDetailLevel31> attributeMap;
	GsonGetResourceDetailLevel32[] events;

	public Map<String, GsonGetResourceDetailLevel31> getAttributeMap() {
		return attributeMap;
	}

	public void setAttributeMap(Map<String, GsonGetResourceDetailLevel31> attributeMap) {
		this.attributeMap = attributeMap;
	}

	@Override
	public String toString() {
		return "ResourseDetailRoomModel [attributeMap=" + attributeMap
				+ ", events=" + Arrays.toString(events) + "]";
	}

}
