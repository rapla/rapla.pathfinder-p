package com.pathfinder.model.gson;

import java.util.Arrays;
import java.util.Map;

public class GSON_GetResourceDetail_LEVEL_2 {

	Map<String, GSON_GetResourceDetail_LEVEL_3_1> attributeMap;
	GSON_GetResourceDetail_LEVEL_3_2[] events;

	public Map<String, GSON_GetResourceDetail_LEVEL_3_1> getAttributeMap() {
		return attributeMap;
	}

	public void setAttributeMap(Map<String, GSON_GetResourceDetail_LEVEL_3_1> attributeMap) {
		this.attributeMap = attributeMap;
	}

	@Override
	public String toString() {
		return "ResourseDetailRoomModel [attributeMap=" + attributeMap
				+ ", events=" + Arrays.toString(events) + "]";
	}

}
