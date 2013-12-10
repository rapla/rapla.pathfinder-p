package com.pathfinder.model;

public class CourseDetailModel {
	// Attention! Events have not yet been incorporated
	private String name;
	private String vintage;
	private String department;
	private String studyCourse;
	private String picture;
	private String roomNr;

	public CourseDetailModel(String name, String vintage, String department,
			String studyCourse, String picture, String roomNr) {
		super();
		this.name = name;
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
