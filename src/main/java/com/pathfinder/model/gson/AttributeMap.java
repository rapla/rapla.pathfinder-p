package com.pathfinder.model.gson;

import java.util.Arrays;
import java.util.Map;

public class AttributeMap {

	Map<String, Attribut> attributeMap;
	Event[] events;

	public Map<String, Attribut> getAttributeMap() {
		return attributeMap;
	}

	public void setAttributeMap(Map<String, Attribut> attributeMap) {
		this.attributeMap = attributeMap;
	}

	@Override
	public String toString() {
		return "ResourseDetailRoomModel [attributeMap=" + attributeMap
				+ ", events=" + Arrays.toString(events) + "]";
	}

}
