package com.pathfinder.presenter;

import java.util.Locale;

import com.pathfinder.model.Device;
import com.pathfinder.model.SessionLoggingModel;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Component.Listener;

import de.vksi.c4j.ContractReference;
import de.vksi.c4j.Pure;

@ContractReference(StelePresenterSpecContract.class)
public interface MainPresenterSpec {

	void setDevice(Device steleLocation);

	void switchToSearchView();

	void switchToDetailView();

	void changeToWheelChairView();

	void changeToNonWheelChairView();

	void refreshFreeRooms();

	AbstractLayout getSteleLayoutView();

	void addKeybordKeyToSearchString(String key);

	void deleteKeyFromSearchString();

	void clearSearchString();

	void setSearchString(String value);

	void languageChanged(Locale locale);

	@Pure
	String getSearchString();

	@Pure
	int getCursorPosition();

	@Pure
	Listener getUiListener();

	void setSessionLoggingModel(SessionLoggingModel sessionLoggingModel);
}
