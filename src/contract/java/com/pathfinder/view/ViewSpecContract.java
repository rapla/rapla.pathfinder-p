/**
 * 
 */
package com.pathfinder.view;

import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;

import com.pathfinder.AbstractComponentContract;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;
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
	 * @see com.pathfinder.view.ViewSpec#buildLayout()
	 */
	@Override
	public void buildLayout() {
		if (preCondition()) {
			assert ((CustomComponent) target).iterator().hasNext() : "Root Composition of Target was set";
		}
		if (postCondition()) {
			Component rootLayout = ((CustomComponent) target).iterator().next();
			assert ((AbstractOrderedLayout) rootLayout).getComponentCount() > 0 : "At least 1 Component added to root composition";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pathfinder.view.ViewSpec#destroyLayout()
	 */
	@Override
	public void destroyLayout() {
		if (preCondition()) {
			assert ((CustomComponent) target).iterator().hasNext() : "Root Composition of Target was set";
		}
		if (postCondition()) {
			Component rootLayout = ((CustomComponent) target).iterator().next();
			assert ((AbstractOrderedLayout) rootLayout).getComponentCount() == 0 : "All Components were removed from root composition";
		}
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

}
