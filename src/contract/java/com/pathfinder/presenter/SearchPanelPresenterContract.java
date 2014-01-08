package com.pathfinder.presenter;

import static de.vksi.c4j.Condition.*;
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
		// Cursor position between min and max search string length
		assert target.getCursorPosition() >=0 : "Cursor position >= 0";
		assert target.getCursorPosition() <= target.getSearchString().length();
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
			// ToDO
		}
	}

	@Override
	public void clearSearchString() {
		if (preCondition()) {
		}
		if (postCondition()) {
			assert "".equals(target.getSearchString()) : "String have to be clean";
		}
	}

	@Override
	public String getSearchString() {
		if (preCondition()) {
		}
		if (postCondition()) {
			String result = result();
			assert result != null : "String have to be initiated";
		}
		return ignored();
	}

	@Override
	public void setSearchString(String value) {
		if (preCondition()) {
			assert value != null : "Value mustn't be null";
		}
		if (postCondition()) {
			assert target.getSearchString().equals(value) : "value set as search string";
		}
	}

	@Override
	public void setRoomContainer(BeanItemContainer<RoomModel> beanItemContainer) {
		if (preCondition()) {
			assert beanItemContainer != null : "Input not null";
		}
	}

	@Override
	public void setCourseContainer(
			BeanItemContainer<CourseModel> beanItemContainer) {
		if (preCondition()) {
			assert beanItemContainer != null : "Input not null";
		}
	}

	@Override
	public void setPersonContainer(
			BeanItemContainer<PersonModel> beanItemContainer) {
		if (preCondition()) {
			assert beanItemContainer != null : "Input not null";
		}
	}

	@Override
	public void setPoiContainer(BeanItemContainer<PoiModel> beanItemContainer) {
		if (preCondition()) {
			assert beanItemContainer != null : "Input not null";
		}
	}

	@Override
	public SearchPanel getSearchPanel() {
		if (preCondition()) {
		}
		if (postCondition()) {
			SearchPanel result = result();
			assert result != null : "result not null";
		}
		return ignored();
	}

	@Override
	public void updateTranslations() {		
	}

	@Override
	public int getCursorPosition() {
		return ignored();
	}
}
