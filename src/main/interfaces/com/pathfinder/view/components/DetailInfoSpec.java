package com.pathfinder.view.components;

import com.pathfinder.model.ResourceModel;

/**
 * DetailSpec
 * 
 * @author alexh
 * 
 */
public interface DetailInfoSpec extends ComponentSpec {
	void addDetails(ResourceModel resource);

	void removeDetails();
}
