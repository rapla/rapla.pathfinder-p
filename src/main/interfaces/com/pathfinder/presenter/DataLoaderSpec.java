package com.pathfinder.presenter;

import com.pathfinder.model.ResourceModel;
import com.vaadin.data.util.BeanItemContainer;

import de.vksi.c4j.ContractReference;
import de.vksi.c4j.Pure;

@ContractReference(DataLoaderSpecContract.class)
public interface DataLoaderSpec {
	@Pure
	BeanItemContainer<ResourceModel> getRoomContainer();

	@Pure
	BeanItemContainer<ResourceModel> getCourseContainer();

	@Pure
	BeanItemContainer<ResourceModel> getPersonContainer();

	@Pure
	BeanItemContainer<ResourceModel> getPoiContainer();

	void reloadAllData();

	void addDataListener(DataLoaderListenerSpec listener);
}