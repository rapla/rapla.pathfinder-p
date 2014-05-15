package com.pathfinder.view;


/**
 * DetailSpec
 * 
 * @author alexh
 * 
 */
public interface DetailImageSpec extends ComponentSpec {
	void setImage(String stelePrefix, String roomName, String url);

	void removeImage();
}
