package com.pathfinder.presenter;

import static de.vksi.c4j.Condition.ignored;
import static de.vksi.c4j.Condition.old;
import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;
import de.vksi.c4j.ClassInvariant;
import de.vksi.c4j.Target;

public class SearchPanelPresenterSpecContract implements
		SearchPanelPresenterSpec {

	@Target
	private SearchPanelPresenterSpec target;

	@ClassInvariant
	public void classInvariant() {
		// TODO: write invariants if required
	}

	@Override
	public void addKeybordKeyToSearchString(String key) {
		if (preCondition()) {
		}
		if (postCondition()) {
			assert target.getSearchString().equals(
					old(target.getSearchString()) + key) : "String added a new Char";
		}
	}

	@Override
	public void deleteKeyFromSearchString() {
		if (preCondition()) {
		}
		if (postCondition()) {
			String oldString = old(target.getSearchString());
			assert target.getSearchString().equals(
					oldString.substring(0, oldString.length() - 1)) : "String deleted a Char";
		}
	}

	@Override
	public void clearSearchString() {
		if (preCondition()) {
		}
		if (postCondition()) {
			assert target.getSearchString() == "" : "String have to be clean";
		}
	}

	@Override
	public String getSearchString() {
		if (preCondition()) {
			assert target.getSearchString() != null : "String have to be initiated";
		}
		if (postCondition()) {
		}
		return ignored();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pathfinder.presenter.SearchPanelPresenterSpec#setSearchString(java
	 * .lang.String)
	 */
	@Override
	public void setSearchString(String value) {
		if (preCondition()) {
			assert value != null : "Value mustn't be null";
		}
		if (postCondition()) {
			assert target.getSearchString().equals(value) : "Value wasn't updated";
		}
	}

}
