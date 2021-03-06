/**
 * 
 */
package com.pathfinder.presenter;

import static de.vksi.c4j.Condition.ignored;
import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;

import java.util.Date;
import java.util.Locale;

import com.pathfinder.model.Attribute;
import com.pathfinder.model.EventModel;
import com.pathfinder.model.FreeRoomModel;
import com.pathfinder.model.ResourceModel;
import com.vaadin.data.util.BeanItemContainer;

import de.vksi.c4j.Condition;
import de.vksi.c4j.Target;

/**
 * @author tim
 * 
 */
public class DataLoaderSpecContract implements DataLoaderSpec {

	@Target
	private DataLoaderSpec target;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pathfinder.presenter.DataLoaderSpec#getRoomContainer()
	 */
	@Override
	public BeanItemContainer<ResourceModel> getRoomContainer() {
		if (postCondition()) {
			BeanItemContainer<ResourceModel> result = Condition.result();
			assert result != null : "Result not null";
		}
		return ignored();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pathfinder.presenter.DataLoaderSpec#getCourseContainer()
	 */
	@Override
	public BeanItemContainer<ResourceModel> getCourseContainer() {
		if (postCondition()) {
			BeanItemContainer<ResourceModel> result = Condition.result();
			assert result != null : "Result not null";
		}
		return ignored();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pathfinder.presenter.DataLoaderSpec#getPersonContainer()
	 */
	@Override
	public BeanItemContainer<ResourceModel> getPersonContainer() {
		if (postCondition()) {
			BeanItemContainer<ResourceModel> result = Condition.result();
			assert result != null : "Result not null";
		}
		return ignored();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pathfinder.presenter.DataLoaderSpec#getPoiContainer()
	 */
	@Override
	public BeanItemContainer<ResourceModel> getPoiContainer() {
		if (postCondition()) {
			BeanItemContainer<ResourceModel> result = Condition.result();
			assert result != null : "Result not null";
		}
		return ignored();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pathfinder.presenter.DataLoaderSpec#addDataListener(com.pathfinder
	 * .presenter.DataLoaderListenerSpec)
	 */
	@Override
	public void addDataListener(DataLoaderListenerSpec listener) {
		if (preCondition()) {
			assert listener != null : "listener not null";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pathfinder.presenter.DataLoaderSpec#reloadAllData()
	 */
	@Override
	public void reloadAllData() {
		// Can be called at any time
	}

	@Override
	public BeanItemContainer<Attribute> getResourceDetails(String modelLink,
			Locale locale) {
		return ignored();
	}

	@Override
	public BeanItemContainer<FreeRoomModel> getFreeResources() {
		return ignored();
	}

	@Override
	public BeanItemContainer<EventModel> getEvent(String resourceId,
			Date startDate, Date endDate, Locale locale) {
		return ignored();
	}

	@Override
	public void sendLoggingInfoToRapla(String data) {

	}
}