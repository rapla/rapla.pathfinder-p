package com.pathfinder.view.container;

import com.pathfinder.model.ResourceModel;
import com.pathfinder.view.components.ComponentSpec;
import com.pathfinder.view.listener.DetailViewListenerSpec;

public interface DetailContainerSpec extends ComponentSpec {
	void addRoomDetails(Class<ResourceModel> clazz);

	void addPersonDetails(Class<ResourceModel> clazz);

	void addCourseDetails(Class<ResourceModel> clazz);

	void addPoiDetails(Class<ResourceModel> clazz);

	void addDetailContainerViewListener(DetailViewListenerSpec listener);
}