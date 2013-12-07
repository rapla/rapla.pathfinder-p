package com.pathfinder.view.components;

import com.pathfinder.view.listener.DetailViewListenerSpec;

/**
 * DetailSpec
 * 
 * @author alexh
 * 
 */
public interface DetailSpec extends ComponentSpec {
	void refreshDetails();

	void addDetailListener(DetailViewListenerSpec listener);
}
