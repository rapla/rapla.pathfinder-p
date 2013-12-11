package com.pathfinder.view.components;

import com.pathfinder.model.GSON_GetResources_LEVEL_2;
import com.pathfinder.view.listener.DetailViewListenerSpec;

public interface DetailContainerSpec extends ComponentSpec {
	void addRoomDetails(Class<GSON_GetResources_LEVEL_2> clazz);

	void addPersonDetails(Class<GSON_GetResources_LEVEL_2> clazz);

	void addCourseDetails(Class<GSON_GetResources_LEVEL_2> clazz);

	void addPoiDetails(Class<GSON_GetResources_LEVEL_2> clazz);

	void addDetailContainerViewListener(DetailViewListenerSpec listener);
}
