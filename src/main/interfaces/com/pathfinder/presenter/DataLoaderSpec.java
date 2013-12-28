package com.pathfinder.presenter;

import com.pathfinder.model.CourseModel;
import com.pathfinder.model.PoiModel;
import com.pathfinder.model.PersonModel;
import com.pathfinder.model.RoomModel;
import com.vaadin.data.util.BeanItemContainer;

public interface DataLoaderSpec {

	void loadAllResources();

	BeanItemContainer<RoomModel> getRoomContainer();

	BeanItemContainer<CourseModel> getCourseContainer();

	BeanItemContainer<PersonModel> getPersonContainer();

	BeanItemContainer<PoiModel> getPoiContainer();

}