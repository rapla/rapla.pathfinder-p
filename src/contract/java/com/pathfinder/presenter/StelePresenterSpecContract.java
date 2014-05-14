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
		// TODO: write invariants if required
	}

	@Override
	public AbstractLayout getSteleLayoutView() {
		if (preCondition()) {
			// TODO: write preconditions if required
		}
		if (postCondition()) {
			// TODO: write postconditions if required
		}
		return ignored();
	}

	@Override
	public void refreshFreeRooms() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addKeybordKeyToSearchString(String key) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteKeyFromSearchString() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearSearchString() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSearchString(String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getSearchString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCursorPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void switchToSearchView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void switchToDetailView() {
		// TODO Auto-generated method stub

	}

	@Override
	public Listener getUiListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeToWheelChairView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeToNonWheelChairView() {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void setSessionLoggingModel(SessionLoggingModel sessionLoggingModel) {
		// TODO Auto-generated method stub

	}

}