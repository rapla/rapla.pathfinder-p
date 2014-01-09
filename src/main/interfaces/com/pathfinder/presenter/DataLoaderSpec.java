package com.pathfinder.presenter;

import com.pathfinder.model.CourseModel;
import com.pathfinder.model.PersonModel;
import com.pathfinder.model.PoiModel;
import com.pathfinder.model.RoomModel;
import com.vaadin.data.util.BeanItemContainer;

import de.vksi.c4j.ContractReference;
import de.vksi.c4j.Pure;

@ContractReference(DataLoaderSpecContract.class)
public interface DataLoaderSpec {
	@Pure
	BeanItemContainer<RoomModel> getRoomContainer();

	@Pure
	BeanItemContainer<CourseModel> getCourseContainer();

	@Pure
	BeanItemContainer<PersonModel> getPersonContainer();

	@Pure
	BeanItemContainer<PoiModel> getPoiContainer();

	void reloadAllData();

	void addDataListener(DataLoaderListenerSpec listener);
}