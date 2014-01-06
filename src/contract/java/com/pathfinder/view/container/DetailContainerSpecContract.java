/**
 * 
 */
package com.pathfinder.view.container;

import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;

import com.pathfinder.model.CourseModel;
import com.pathfinder.model.PersonModel;
import com.pathfinder.model.PoiModel;
import com.pathfinder.model.RoomModel;
import com.pathfinder.view.components.ComponentSpecContract;

import de.vksi.c4j.ClassInvariant;
import de.vksi.c4j.Target;

/**
 * @author tim
 * 
 */
public class DetailContainerSpecContract extends ComponentSpecContract
		implements DetailContainerSpec {

	@Target
	private DetailContainerSpec target;

	@ClassInvariant
	public void classInvariant() {
		// TODO: write invariants if required
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pathfinder.view.container.DetailContainerSpec#hideDetailContainer()
	 */
	@Override
	public void hideDetailContainer() {
		if (postCondition()) {
			assert !target.isVisible() : "Container not visible";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pathfinder.view.container.DetailContainerSpec#showDetailContainer()
	 */
	@Override
	public void showDetailContainer() {
		if (postCondition()) {
			assert target.isVisible() : "Container is visible";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pathfinder.view.container.DetailContainerSpec#addRoomDetails(java
	 * .lang.Class)
	 */
	@Override
	public void addRoomDetails(Class<RoomModel> clazz) {
		if (preCondition()) {
			// TODO: write preconditions if required
		}
		if (postCondition()) {
			// TODO: write postconditions if required
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pathfinder.view.container.DetailContainerSpec#addCourseDetails(java
	 * .lang.Class)
	 */
	@Override
	public void addCourseDetails(Class<CourseModel> clazz) {
		if (preCondition()) {
			// TODO: write preconditions if required
		}
		if (postCondition()) {
			// TODO: write postconditions if required
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pathfinder.view.container.DetailContainerSpec#addPersonDetails(java
	 * .lang.Class)
	 */
	@Override
	public void addPersonDetails(Class<PersonModel> clazz) {
		if (preCondition()) {
			// TODO: write preconditions if required
		}
		if (postCondition()) {
			// TODO: write postconditions if required
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pathfinder.view.container.DetailContainerSpec#addPoiDetails(java.
	 * lang.Class)
	 */
	@Override
	public void addPoiDetails(Class<PoiModel> clazz) {
		if (preCondition()) {
			// TODO: write preconditions if required
		}
		if (postCondition()) {
			// TODO: write postconditions if required
		}
	}

}
