package com.pathfinder.view.container;

import com.pathfinder.model.CourseModel;
import com.pathfinder.model.PersonModel;
import com.pathfinder.model.PoiModel;
import com.pathfinder.model.RoomModel;
import com.pathfinder.view.components.ComponentSpec;

import de.vksi.c4j.ContractReference;

@ContractReference(DetailContainerSpecContract.class)
public interface DetailContainerSpec extends ComponentSpec {
	void hideDetailContainer();

	void showDetailContainer();

	void addRoomDetails(Class<RoomModel> clazz);

	void addCourseDetails(Class<CourseModel> clazz);

	void addPersonDetails(Class<PersonModel> clazz);

	void addPoiDetails(Class<PoiModel> clazz);
}
