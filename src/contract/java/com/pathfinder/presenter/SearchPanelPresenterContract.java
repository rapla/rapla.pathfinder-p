package com.pathfinder.presenter;

import static de.vksi.c4j.Condition.ignored;
import static de.vksi.c4j.Condition.old;
import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;

import com.pathfinder.model.CourseModel;
import com.pathfinder.model.PersonModel;
import com.pathfinder.model.PoiModel;
import com.pathfinder.model.RoomModel;
import com.pathfinder.view.container.SearchPanel;
import com.vaadin.data.util.BeanItemContainer;

import de.vksi.c4j.ClassInvariant;
import de.vksi.c4j.Target;

public class SearchPanelPresenterContract implements SearchPanelPresenterSpec {

	@Target
	private SearchPanelPresenterSpec target;

	@ClassInvariant
	public void classInvariant() {
		// TODO: write invariants if required
	}

	@Override
	public void addKeybordKeyToSearchString(String key) {
		if (preCondition()) {
			assert key != null : "key not null";
			assert key.length() == 1 : "key is exactly one letter";
		}
		if (postCondition()) {
			assert target.getSearchString().length() == old(
					target.getSearchString()).length() + 1 : "One letter added to search string";
		}
	}

	@Override
	public void deleteKeyFromSearchString() {
		if (preCondition()) {
		}
		if (postCondition()) {
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

	@Override
	public void setRoomContainer(BeanItemContainer<RoomModel> beanItemContainer) {
		if (preCondition()) {
		}
		if (postCondition()) {
		}
	}

	@Override
	public void setCourseContainer(
			BeanItemContainer<CourseModel> beanItemContainer) {
		if (preCondition()) {
		}
		if (postCondition()) {
		}
	}

	@Override
	public void setPersonContainer(
			BeanItemContainer<PersonModel> beanItemContainer) {
		if (preCondition()) {
		}
		if (postCondition()) {
		}
	}

	@Override
	public void setPoiContainer(BeanItemContainer<PoiModel> beanItemContainer) {
		if (preCondition()) {
		}
		if (postCondition()) {
		}
	}

	@Override
	public SearchPanel getSearchPanel() {
		if (preCondition()) {
		}
		if (postCondition()) {
		}
		return null;
	}

	@Override
	public void updateTranslations() {
		// TODO Auto-generated method stub
		
	}
}
