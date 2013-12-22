package com.pathfinder.model;

public class PoiModel {
	// TODO Events have to be implemented in the future
	public static final String PROPERTY_ID = "id";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_LINK = "link";
	public static final String PROPERTY_SEARCHTERMS = "searchTerms";
	public static final String PROPERTY_PICTURE = "picture";
	public static final String PROPERTY_ROOMNR = "roomNr";

	private String id = "";
	private String name;
	private String link = "";
	// TODO Do we need this?
	private String[] searchTerms;
	private String picture;
	private String roomNr;

	public PoiModel(String name, String link, String id, String[] searchTerms,
			String roomNr, String picture) {
		super();
		this.name = name;
		this.link = link;
		this.id = id;
		this.searchTerms = searchTerms;
		this.roomNr = roomNr;
		this.picture = picture;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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


	public String[] getSearchTerms() {
		return searchTerms;
	}

	public void setSearchTerms(String[] searchTerms) {
		this.searchTerms = searchTerms;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getRoomNr() {
		return roomNr;
	}

	public void setRoomNr(String roomNr) {
		this.roomNr = roomNr;
	}

}