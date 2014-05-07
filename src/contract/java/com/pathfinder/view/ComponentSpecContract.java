/**
 * 
 */
package com.pathfinder.view;

import com.pathfinder.AbstractComponentContract;

import de.vksi.c4j.Target;

/**
 * @author tim
 * 
 */
public class ComponentSpecContract extends AbstractComponentContract implements
		ComponentSpec {

	@Target
	private ComponentSpec target;

	@Override
	public void updateTranslations() {
		// Nothing to do here due to diversity of subcontracts
	}

	@Override
	public void doCleanup() {
		// TODO Auto-generated method stub

	}

}
