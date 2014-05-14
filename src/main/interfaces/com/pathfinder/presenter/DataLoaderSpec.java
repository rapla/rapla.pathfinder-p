package com.pathfinder.presenter;

import java.util.Date;
import java.util.Locale;

import com.pathfinder.model.Attribute;
import com.pathfinder.model.EventModel;
import com.pathfinder.model.FreeRoomModel;
import com.pathfinder.model.ResourceModel;
import com.vaadin.data.util.BeanItemContainer;

import de.vksi.c4j.ContractReference;
import de.vksi.c4j.Pure;

@ContractReference(DataLoaderSpecContract.class)
public interface DataLoaderSpec {
	void addDataListener(DataLoaderListenerSpec listener);

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
	BeanItemContainer<Attribute> getResourceDetails(String resourceId,
			Locale locale);

	@Pure
	BeanItemContainer<FreeRoomModel> getFreeResources();

	@Pure
	BeanItemContainer<EventModel> getEvent(String resourceId, Date startDate,
			Date endDate, Locale locale);

	void sendLoggingInfoToRapla(String data);
}