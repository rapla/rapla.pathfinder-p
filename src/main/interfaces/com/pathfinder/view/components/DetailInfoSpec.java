package com.pathfinder.view.components;

import com.pathfinder.view.listener.DetailViewListenerSpec;

/**
 * DetailSpec
 * 
 * @author alexh
 * 
 */
public interface DetailInfoSpec extends ComponentSpec {
	void addDetailListener(DetailViewListenerSpec listener);
}
