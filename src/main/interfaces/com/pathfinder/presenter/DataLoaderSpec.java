package com.pathfinder.presenter;

import com.pathfinder.model.CourseDetailModel;
import com.pathfinder.model.PersonDetailModel;
import com.pathfinder.model.RoomDetailModel;

public interface DataLoaderSpec {

	void loadAllResources();

	CourseDetailModel getCourseDetail(String id);

	RoomDetailModel getRoomDetail(String id);

	PersonDetailModel getPersonDetail(String id);

}