/**
 * 
 */
package com.pathfinder.view.container;

import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;

import com.pathfinder.model.ResourceModel;
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

	@Override
	public void showDetailContainer() {
		if (postCondition()) {
			assert target.isVisible() : "Container is visible";
		}
	}

	@Override
	public void addDetails(ResourceModel resourceModel) {
		if (preCondition()) {
			// TODO: write preconditions if required
		}
		if (postCondition()) {
			// TODO: write postconditions if required
		}
	}

	@Override
	public void removeDetails() {
		// TODO Auto-generated method stub
		
	}

}