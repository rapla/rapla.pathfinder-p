package com.pathfinder.model;

public class CourseModel {
	// Attention! Events were not yet been implemented
	private String name;
	private String link = "";
	private String id = "";
	private String[] searchTerms;

	private String vintage;
	private String department;
	private String studyCourse;
	private String picture;
	private String roomNr;

	public CourseModel(String name, String link, String id,
			String[] searchTerms, String vintage, String department,
			String studyCourse, String picture, String roomNr) {
		super();
		this.name = name;
		this.link = link;
		this.id = id;
		this.searchTerms = searchTerms;
		this.vintage = vintage;
		this.department = department;
		this.studyCourse = studyCourse;
		this.picture = picture;
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

	public String getStudyCourse() {
		return studyCourse;
	}

	public void setStudyCourse(String studyCourse) {
		this.studyCourse = studyCourse;
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
