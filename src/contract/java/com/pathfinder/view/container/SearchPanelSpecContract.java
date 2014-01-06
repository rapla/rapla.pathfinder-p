/**
 * 
 */
package com.pathfinder.view.container;

import static de.vksi.c4j.Condition.postCondition;

import com.pathfinder.view.ViewSpecContract;

import de.vksi.c4j.Target;

/**
 * @author tim
 * 
 */
public class SearchPanelSpecContract extends ViewSpecContract implements
		SearchPanelSpec {

	@Target
	private SearchPanelSpec target;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pathfinder.view.container.SearchPanelSpec#hideSearchPanel()
	 */
	@Override
	public void hideSearchPanel() {
		if (postCondition()) {
			assert !target.isVisible() : "SearchPanel not visible";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pathfinder.view.container.SearchPanelSpec#showSearchPanel()
	 */
	@Override
	public void showSearchPanel() {
		if (postCondition()) {
			assert target.isVisible() : "SearchPanel visible";
		}
	}

}
