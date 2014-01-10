/**
 * 
 */
package com.pathfinder.presenter;

import static de.vksi.c4j.Condition.ignored;
import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;

import java.util.List;
import java.util.Locale;

import com.pathfinder.model.Attribut;
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
	public List<Attribut> getResourceDetails(String modelLink, Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BeanItemContainer<FreeRoomModel> getFreeResources() {
		// TODO Auto-generated method stub
		return null;
	}
}