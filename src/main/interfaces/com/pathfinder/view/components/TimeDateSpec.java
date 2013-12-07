package com.pathfinder.view.components;

import com.pathfinder.view.listener.TimeDateViewListenerSpec;

/**
 * TimeDateSpec
 * 
 * @author alexh
 * 
 */
public interface TimeDateSpec extends ComponentSpec {
	void refreshTimeAndDate();

	void addTimeDateListener(TimeDateViewListenerSpec listener);
}
