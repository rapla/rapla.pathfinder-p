package com.pathfinder.view.components;

import com.pathfinder.model.RoomModel;

public interface DetailContainerSpec extends ComponentSpec {
	void addRoomDetails(Class<RoomModel> clazz);
	void addPersonDetails(Class<RoomModel> clazz);
	void addCourseDetails(Class<RoomModel> clazz);
	void addPoiDetails(Class<RoomModel> clazz);
}
