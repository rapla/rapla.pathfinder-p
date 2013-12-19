package com.pathfinder.presenter;

import com.pathfinder.view.layout.MobileLayout;
import com.vaadin.ui.CustomComponent;

/**
 * Presenter to define the behavior on a mobile client
 * @author alexh
 *
 */
public class MobilePresenter implements MobilePresenterSpec {
	private final MobileLayout mobileLayout = new MobileLayout();

	public CustomComponent getMobileLayoutView() {
		return mobileLayout;
	}
}
