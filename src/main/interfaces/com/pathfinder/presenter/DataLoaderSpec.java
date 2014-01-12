package com.pathfinder.presenter;

import java.util.List;
import java.util.Locale;

import com.pathfinder.model.Attribut;
import com.pathfinder.model.FreeRoomModel;
import com.pathfinder.model.ResourceModel;
import com.vaadin.data.util.BeanItemContainer;

import de.vksi.c4j.ContractReference;
import de.vksi.c4j.Pure;

@ContractReference(DataLoaderSpecContract.class)
public interface DataLoaderSpec {
	void addDataListener(DataLoaderListenerSpec listener);

	void setDhbwEntryPoint(String dhbwEntryPoint);

	void reloadAllData();

	@Pure
	BeanItemContainer<ResourceModel> getRoomContainer();

	@Pure
	BeanItemContainer<ResourceModel> getCourseContainer();

	@Pure
	BeanItemContainer<ResourceModel> getPersonContainer();

	@Pure
	BeanItemContainer<ResourceModel> getPoiContainer();

	@Pure
	List<Attribut> getResourceDetails(String resourceId, Locale locale);

	BeanItemContainer<FreeRoomModel> getFreeResources();

	String getDhbwEntryPoint();
}