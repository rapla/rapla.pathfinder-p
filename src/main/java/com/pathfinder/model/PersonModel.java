package com.pathfinder.model;

@Deprecated
public class PersonModel extends ResourceModel {

	public static final String PROPERTY_DEPARTMENT = "department";
	public static final String PROPERTY_COURSE = "course";
	public static final String PROPERTY_EMAIL = "email";
	public static final String PROPERTY_PICTURE = "picture";
	public static final String PROPERTY_TELEPHONE = "telephone";
	public static final String PROPERTY_FACULTY = "faculty";

	private String department;
	private String course;
	private String email;
	private String picture;
	private String telephone;
	private String faculty;

	@Deprecated
	public PersonModel(String id, String name, String link,
			String[] searchTerms, String roomNr, String department,
			String course, String email, String picture, String telephone,
			String faculty) {
		super(id, name, link, searchTerms, roomNr);
		this.department = department;
		this.course = course;
		this.email = email;
		this.picture = picture;
		this.telephone = telephone;
		this.faculty = faculty;
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

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
}