package com.pathfinder.model;

public class CourseModel {
	// TODO Events have to be implemented in the future
	public static final String PROPERTY_ID = "id";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_LINK = "link";
	public static final String PROPERTY_SEARCHTERMS = "searchTerms";
	public static final String PROPERTY_VINTAGE = "vintage";
	public static final String PROPERTY_DEPARTMENT = "department";
	public static final String PROPERTY_COURSE = "course";
	public static final String PROPERTY_PICTURE = "picture";
	public static final String PROPERTY_ROOMNR = "roomNr";

	private String id = "";
	private String name;
	private String link = "";
	// TODO Do we need this?
	private String[] searchTerms;
	private String vintage;
	private String department;
	private String course;
	private String picture;
	private String roomNr;

	public CourseModel(String name, String link, String id,
			String[] searchTerms, String vintage, String department,
			String course, String picture, String roomNr) {
		super();
		this.name = name;
		this.link = link;
		this.id = id;
		this.searchTerms = searchTerms;
		this.vintage = vintage;
		this.department = department;
		this.course = course;
		this.picture = picture;
		this.roomNr = roomNr;
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

	public String getVintage() {
		return vintage;
	}

	public void setVintage(String vintage) {
		this.vintage = vintage;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
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