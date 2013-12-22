package com.pathfinder.model;

public class PersonModel {
	// TODO Events have to be implemented in the future
	public static final String PROPERTY_ID = "id";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_LINK = "link";
	public static final String PROPERTY_SEARCHTERMS = "searchTerms";
	public static final String PROPERTY_DEPARTMENT = "department";
	public static final String PROPERTY_COURSE = "course";
	public static final String PROPERTY_EMAIL = "email";
	public static final String PROPERTY_PICTURE = "picture";
	public static final String PROPERTY_TELEPHONE = "telephone";
	public static final String PROPERTY_ROOMNR = "roomNr";

	private String id = "";
	private String name;
	private String link = "";
	// TODO Do we need this?
	private String[] searchTerms;
	private String department;
	private String course;
	private String email;
	private String picture;
	private String telephone;
	private String roomNr;

	public PersonModel(String name, String link, String id,
			String[] searchTerms, String department, String course,
			String email, String picture, String telephone, String roomNr) {
		super();
		this.name = name;
		this.link = link;
		this.id = id;
		this.searchTerms = searchTerms;
		this.department = department;
		this.course = course;
		this.email = email;
		this.picture = picture;
		this.telephone = telephone;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRoomNr() {
		return roomNr;
	}

	public void setRoomNr(String roomNr) {
		this.roomNr = roomNr;
	}
}