package com.pathfinder.presenter;

import com.pathfinder.model.CourseModel;
import com.pathfinder.model.POIModel;
import com.pathfinder.model.PersonModel;
import com.pathfinder.model.RoomModel;

public interface DataLoaderSpec {

	void loadAllResources();

	RoomModel[] getAllRooms();

	CourseModel[] getAllCourses();

	PersonModel[] getAllPersons();
	
	POIModel[] getAllPois();

}