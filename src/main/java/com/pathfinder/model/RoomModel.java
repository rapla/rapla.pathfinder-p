package com.pathfinder.model;

import java.util.Arrays;

public class RoomModel {
	// TODO Events have to be implemented in the future
	public static final String PROPERTY_ID = "id";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_LINK = "link";
	public static final String PROPERTY_SEARCHTERMS = "searchTerms";
	public static final String PROPERTY_DEPARTMENT = "department";
	public static final String PROPERTY_COURSE = "course";
	public static final String PROPERTY_ROOMNR = "roomNr";
	public static final String PROPERTY_ROOMTYPE = "roomType";

	private String id = "";
	private String name;
	private String link = "";
	// TODO Do we need this?
	private String[] searchTerms;
	private String department;
	private String course;
	private String roomNr;
	private String roomType;

	public RoomModel(String name, String link, String id, String[] searchTerms,
			String roomNr, String roomType, String course, String department) {
		super();
		this.name = name;
		this.link = link;
		this.id = id;
		this.searchTerms = searchTerms;
		this.roomNr = roomNr;
		this.roomType = roomType;
		this.course = course;
		this.department = department;
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

	public String getRoomNr() {
		return roomNr;
	}

	public void setRoomNr(String roomNr) {
		this.roomNr = roomNr;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	@Override
	public String toString() {
		return "RoomModel [name=" + name + ", link=" + link + ", id=" + id
				+ ", searchTerms=" + Arrays.toString(searchTerms) + ", roomNr="
				+ roomNr + ", roomType=" + roomType + ", studyCourse=" + course
				+ ", department=" + department + "]";
	}
}