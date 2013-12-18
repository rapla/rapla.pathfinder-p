package com.pathfinder.model;

public class POIModel {
	// Attention! Events were not yet been implemented
	private String name;
	private String link = "";
	private String id = "";
	private String[] searchTerms;

	private String roomNr;
	private String picture;

	public POIModel(String name, String link, String id, String[] searchTerms,
			String roomNr, String picture) {
		super();
		this.name = name;
		this.link = link;
		this.id = id;
		this.searchTerms = searchTerms;
		this.roomNr = roomNr;
		this.picture = picture;
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

	public String getRoomNr() {
		return roomNr;
	}

	public void setRoomNr(String roomNr) {
		this.roomNr = roomNr;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
}
