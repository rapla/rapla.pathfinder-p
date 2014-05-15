package com.pathfinder.presenter;

import static de.vksi.c4j.Condition.ignored;
import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;

import java.util.Locale;

import com.pathfinder.model.Device;
import com.pathfinder.model.SessionLoggingModel;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Component.Listener;

import de.vksi.c4j.ClassInvariant;
import de.vksi.c4j.Target;

public class StelePresenterSpecContract implements MainPresenterSpec {

	@Target
	private MainPresenterSpec target;

	@ClassInvariant
	public void classInvariant() {
	}

	@Override
	public AbstractLayout getSteleLayoutView() {
		if (preCondition()) {
		}
		if (postCondition()) {
		}
		return ignored();
	}

	@Override
	public void refreshFreeRooms() {

	}

	@Override
	public void addKeybordKeyToSearchString(String key) {
	}

	@Override
	public void deleteKeyFromSearchString() {

	}

	@Override
	public void clearSearchString() {

	}

	@Override
	public void setSearchString(String value) {

	}

	@Override
	public String getSearchString() {
		return null;
	}

	@Override
	public int getCursorPosition() {
		return 0;
	}

	@Override
	public void switchToSearchView() {

	}

	@Override
	public void switchToDetailView() {

	}

	@Override
	public Listener getUiListener() {
		return null;
	}

	@Override
	public void changeToWheelChairView() {

	}

	@Override
	public void changeToNonWheelChairView() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pathfinder.presenter.StelePresenterSpec#languageChanged(java.util
	 * .Locale)
	 */
	@Override
	public void languageChanged(Locale locale) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pathfinder.presenter.StelePresenterSpec#setSteleLocation(com.pathfinder
	 * .model.SteleLocation)
	 */
	@Override
	public void setDevice(Device steleLocation) {

	}

	@Override
	public void setSessionLoggingModel(SessionLoggingModel sessionLoggingModel) {

	}

}