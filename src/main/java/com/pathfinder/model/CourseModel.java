package com.pathfinder.model;

@Deprecated
public class CourseModel extends ResourceModel {
	public static final String PROPERTY_VINTAGE = "vintage";
	public static final String PROPERTY_DEPARTMENT = "department";
	public static final String PROPERTY_COURSE = "course";
	public static final String PROPERTY_PICTURE = "picture";
	public static final String PROPERTY_FACULTY = "faculty";

	private String vintage;
	private String department;
	private String course;
	private String picture;
	private String faculty;

	@Deprecated
	public CourseModel(String id, String name, String link,
			String[] searchTerms, String roomNr, String vintage,
			String department, String course, String picture, String faculty) {
		super(id, name, link, searchTerms, roomNr);
		this.vintage = vintage;
		this.department = department;
		this.course = course;
		this.picture = picture;
		this.faculty = faculty;
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

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
}