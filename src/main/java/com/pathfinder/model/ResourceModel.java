package com.pathfinder.model;

/**
 * Model for all resources
 * 
 * @author alexh
 * 
 */
public class ResourceModel {
	public static final String PROPERTY_ID = "id";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_LINK = "link";
	public static final String PROPERTY_SEARCHTERMS = "searchTerms";
	@Deprecated
	public static final String PROPERTY_ROOMNR = "roomNr";
	public static final String PROPERTY_TYPE = "type";

	private String id = "";
	private String name = "";
	private String link = "";
	private String[] searchTerms;
	private String roomNr = "";
	private String type = "";

	public ResourceModel(String id, String name, String link,
			String[] searchTerms, String roomNr) {
		this.setId(id);
		this.setName(name);
		this.setLink(link);
		this.setSearchTerms(searchTerms);
		this.setRoomNr(roomNr);
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

	@Deprecated
	public String getRoomNr() {
		return roomNr;
	}

	@Deprecated
	public void setRoomNr(String roomNr) {
		this.roomNr = roomNr;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}