/**
 * 
 */
package com.pathfinder.presenter;

import static de.vksi.c4j.Condition.ignored;
import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;

import com.pathfinder.model.CourseModel;
import com.pathfinder.model.PersonModel;
import com.pathfinder.model.PoiModel;
import com.pathfinder.model.RoomModel;
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
	public BeanItemContainer<RoomModel> getRoomContainer() {
		if (postCondition()) {
			BeanItemContainer<RoomModel> result = Condition.result();
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
	public BeanItemContainer<CourseModel> getCourseContainer() {
		if (postCondition()) {
			BeanItemContainer<CourseModel> result = Condition.result();
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
	public BeanItemContainer<PersonModel> getPersonContainer() {
		if (postCondition()) {
			BeanItemContainer<PersonModel> result = Condition.result();
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
	public BeanItemContainer<PoiModel> getPoiContainer() {
		if (postCondition()) {
			BeanItemContainer<PoiModel> result = Condition.result();
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

}
