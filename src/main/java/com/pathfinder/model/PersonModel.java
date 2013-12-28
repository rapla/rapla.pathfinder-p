package com.pathfinder.model;

public class PersonModel extends ResourceModel {

	public static final String PROPERTY_DEPARTMENT = "department";
	public static final String PROPERTY_COURSE = "course";
	public static final String PROPERTY_EMAIL = "email";
	public static final String PROPERTY_PICTURE = "picture";
	public static final String PROPERTY_TELEPHONE = "telephone";

	private String department;
	private String course;
	private String email;
	private String picture;
	private String telephone;

	public PersonModel(String name, String link, String id,
			String[] searchTerms, String department, String course,
			String email, String picture, String telephone, String roomNr) {
		super(name, link, id, searchTerms, id);
		this.department = department;
		this.course = course;
		this.email = email;
		this.picture = picture;
		this.telephone = telephone;
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
}