package com.pathfinder.view.components;

import com.pathfinder.model.RoomPojo;

public interface DetailContainerSpec extends ComponentSpec {
	void addRoomDetails(Class<RoomPojo> clazz);
	void addPersonDetails(Class<RoomPojo> clazz);
	void addCourseDetails(Class<RoomPojo> clazz);
	void addPoiDetails(Class<RoomPojo> clazz);
}
