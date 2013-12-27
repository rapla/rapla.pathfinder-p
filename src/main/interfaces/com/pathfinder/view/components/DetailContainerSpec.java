package com.pathfinder.view.components;

import com.pathfinder.model.gson.GsonGetResourcesLevel2;
import com.pathfinder.view.listener.DetailViewListenerSpec;

public interface DetailContainerSpec extends ComponentSpec {
	void addRoomDetails(Class<GsonGetResourcesLevel2> clazz);

	void addPersonDetails(Class<GsonGetResourcesLevel2> clazz);

	void addCourseDetails(Class<GsonGetResourcesLevel2> clazz);

	void addPoiDetails(Class<GsonGetResourcesLevel2> clazz);

	void addDetailContainerViewListener(DetailViewListenerSpec listener);
}
