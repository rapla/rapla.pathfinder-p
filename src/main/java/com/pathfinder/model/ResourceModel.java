package com.pathfinder.model;

import java.util.Arrays;

public class ResourceModel {

	private String name = "";
	private String link = "";
	private String id = "";
	private String[] searchTerms;

	// TODO Only for testing - can be removed
	public ResourceModel(String all) {
		name = all;
		link = all;
		id = all;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getSearchTerms() {
		return searchTerms;
	}

	public void setSearchTerms(String[] searchTerms) {
		this.searchTerms = searchTerms;
	}

	@Override
	public String toString() {
		return "RoomModel [name=" + name + ", link=" + link + ", id=" + id
				+ ", searchTerms=" + Arrays.toString(searchTerms) + "]";
	}

}
