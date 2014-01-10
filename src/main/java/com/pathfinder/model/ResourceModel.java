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
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_FACULTY = "faculty";

	private String id = "";
	private String name = "";
	private String link = "";
	private String[] searchTerms;
	private String type = "";
	private String faculty = "";

	public ResourceModel(String id, String name, String link,
			String[] searchTerms) {
		this.setId(id);
		this.setName(name);
		this.setLink(link);
		this.setSearchTerms(searchTerms);
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
}