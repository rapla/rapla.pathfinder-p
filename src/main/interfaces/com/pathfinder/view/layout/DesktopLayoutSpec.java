package com.pathfinder.view.layout;

import com.pathfinder.view.ViewSpec;

/**
 * MainLayoutSpec
 * 
 * @author alexh
 * 
 */
public interface DesktopLayoutSpec extends ViewSpec {
	void switchToSearchView();

	<T> void switchToDetailView();

	void switchToAppointmentView();

	void setAppointmentUrl(String url);
}
