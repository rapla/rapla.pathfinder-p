package com.pathfinder.model;

public class CourseModel extends ResourceModel {
	public static final String PROPERTY_VINTAGE = "vintage";
	public static final String PROPERTY_DEPARTMENT = "department";
	public static final String PROPERTY_COURSE = "course";
	public static final String PROPERTY_PICTURE = "picture";

	private String vintage;
	private String department;
	private String course;
	private String picture;

	public CourseModel(String name, String link, String id,
			String[] searchTerms, String vintage, String department,
			String course, String picture, String roomNr) {
		super(name, link, id, searchTerms, id);
		this.vintage = vintage;
		this.department = department;
		this.course = course;
		this.picture = picture;
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
}