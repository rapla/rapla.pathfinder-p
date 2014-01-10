package com.pathfinder.view.components;

import com.pathfinder.model.ResourceModel;

/**
 * DetailSpec
 * 
 * @author alexh
 * 
 */
public interface DetailImageSpec extends ComponentSpec {
	void setImage(ResourceModel resource);

	void removeImage();
}
