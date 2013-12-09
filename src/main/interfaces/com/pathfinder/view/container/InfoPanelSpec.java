package com.pathfinder.view.container;

import com.pathfinder.view.ViewSpec;
import com.pathfinder.view.listener.InfoPanelViewListenerSpec;

/**
 * InfoPanelSpec
 * 
 * @author alexh
 * 
 */
public interface InfoPanelSpec extends ViewSpec {
	void addInfoPanelViewListener(InfoPanelViewListenerSpec listener);
}
