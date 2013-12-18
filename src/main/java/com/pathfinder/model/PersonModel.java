package com.pathfinder.model;

public class PersonModel {
	// Attention! Events were not yet been implemented
	private String name;
	private String link = "";
	private String id = "";
	private String[] searchTerms;

	private String department;
	private String studyCourse;
	private String email;
	private String picture;
	private String telefonNr;
	private String roomNr;

	public PersonModel(String name, String link, String id,
			String[] searchTerms, String department, String studyCourse,
			String email, String picture, String telefonNr, String roomNr) {
		super();
		this.name = name;
		this.link = link;
		this.id = id;
		this.searchTerms = searchTerms;
		this.department = department;
		this.studyCourse = studyCourse;
		this.email = email;
		this.picture = picture;
		this.telefonNr = telefonNr;
		this.roomNr = roomNr;
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getStudyCourse() {
		return studyCourse;
	}

	public void setStudyCourse(String studyCourse) {
		this.studyCourse = studyCourse;
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

	public String getTelefonNr() {
		return telefonNr;
	}

	public void setTelefonNr(String telefonNr) {
		this.telefonNr = telefonNr;
	}

	public String getRoomNr() {
		return roomNr;
	}

	public void setRoomNr(String roomNr) {
		this.roomNr = roomNr;
	}

}
