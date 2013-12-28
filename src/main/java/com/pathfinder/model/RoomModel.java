package com.pathfinder.model;

import java.util.Arrays;

public class RoomModel extends ResourceModel {
	public static final String PROPERTY_DEPARTMENT = "department";
	public static final String PROPERTY_COURSE = "course";
	public static final String PROPERTY_ROOMTYPE = "roomType";

	private String department;
	private String course;
	private String roomType;

	public RoomModel(String name, String link, String id, String[] searchTerms,
			String roomNr, String roomType, String course, String department) {
		super(name, link, id, searchTerms, id);
		this.roomType = roomType;
		this.course = course;
		this.department = department;
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

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	@Override
	public String toString() {
		return "RoomModel [name=" + super.getName() + ", link="
				+ super.getLink() + ", id=" + super.getId() + ", searchTerms="
				+ Arrays.toString(super.getSearchTerms()) + ", roomNr="
				+ super.getRoomNr() + ", roomType=" + roomType
				+ ", studyCourse=" + course + ", department=" + department
				+ "]";
	}
}