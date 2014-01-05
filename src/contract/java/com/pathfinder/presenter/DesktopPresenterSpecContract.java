package com.pathfinder.presenter;

import static de.vksi.c4j.Condition.ignored;
import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;

import com.pathfinder.model.CourseModel;
import com.pathfinder.model.PersonModel;
import com.pathfinder.model.PoiModel;
import com.pathfinder.model.RoomModel;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

import de.vksi.c4j.ClassInvariant;
import de.vksi.c4j.Target;

public class DesktopPresenterSpecContract implements DesktopPresenterSpec {

	@Target
	private DesktopPresenterSpec target;

	@ClassInvariant
	public void classInvariant() {
		// TODO: write invariants if required
	}

	@Override
	public void setRoomContainer(BeanItemContainer<RoomModel> beanItemContainer) {
		if (preCondition()) {
			// TODO: write preconditions if required
		}
		if (postCondition()) {
			// TODO: write postconditions if required
		}
	}

	@Override
	public void setCourseContainer(
			BeanItemContainer<CourseModel> beanItemContainer) {
		if (preCondition()) {
			// TODO: write preconditions if required
		}
		if (postCondition()) {
			// TODO: write postconditions if required
		}
	}

	@Override
	public void setPersonContainer(
			BeanItemContainer<PersonModel> beanItemContainer) {
		if (preCondition()) {
			// TODO: write preconditions if required
		}
		if (postCondition()) {
			// TODO: write postconditions if required
		}
	}

	@Override
	public void setPoiContainer(BeanItemContainer<PoiModel> beanItemContainer) {
		if (preCondition()) {
			// TODO: write preconditions if required
		}
		if (postCondition()) {
			// TODO: write postconditions if required
		}
	}

	@Override
	public CustomComponent getDesktopLayoutView() {
		if (preCondition()) {
			// TODO: write preconditions if required
		}
		if (postCondition()) {
			// TODO: write postconditions if required
		}
		return ignored();
	}

}
