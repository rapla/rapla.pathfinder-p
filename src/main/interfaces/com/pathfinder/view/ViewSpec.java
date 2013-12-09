package com.pathfinder.view;


/**
 * ViewSpec - Defines methods for all layout classes
 * 
 * @author alexh
 * 
 */
public interface ViewSpec extends TranslatabelSpec {
	void buildLayout();

	void destroyLayout();
}
