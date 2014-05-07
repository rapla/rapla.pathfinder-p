/**
 * 
 */
package com.pathfinder.view;

import com.pathfinder.AbstractComponentContract;
import com.vaadin.ui.CustomComponent;

import de.vksi.c4j.ClassInvariant;
import de.vksi.c4j.Target;

/**
 * @author tim
 * 
 */
public class ViewSpecContract extends AbstractComponentContract implements
		ViewSpec {

	@Target
	private ViewSpec target;

	@ClassInvariant
	public void classInvariant() {
		assert target instanceof CustomComponent : "Target is an instance of CustomComponent";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pathfinder.view.TranslatabelSpec#updateTranslations()
	 */
	@Override
	public void updateTranslations() {
		// Due to diversity of components, there's nothing to check here
	}

	@Override
	public void doCleanup() {
		// TODO Auto-generated method stub

	}
}