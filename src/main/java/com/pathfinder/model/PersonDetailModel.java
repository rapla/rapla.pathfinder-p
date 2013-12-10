package com.pathfinder.model;

public class PersonDetailModel {
	// Attention! Events have not yet been incorporated
	private String name;
	private String department;
	private String studyCourse;
	private String email;
	private String picture;
	private String telefonNr;
	private String roomNr;

	public PersonDetailModel(String name, String department,
			String studyCourse, String email, String picture, String telefonNr,
			String roomNr) {
		super();
		this.name = name;
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
