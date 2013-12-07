/**
 * Model for the SearchString
 * @author Myracle
 */

package com.pathfinder.model;

public class KeyboardModel {
	
	public static final String PROPERTY_SEARCHSTRING = "searchString";

	private String searchString = "";

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

}
