package com.pathfinder.model;

import java.util.Arrays;

public class RoomModel {
	// Attention! Events were not yet been implemented

	private String name;
	private String link = "";
	private String id = "";
	private String[] searchTerms;

	private String roomNr;
	private String roomType;
	private String studyCourse;
	private String department;

	public RoomModel(String name, String link, String id, String[] searchTerms,
			String roomNr, String roomType, String studyCourse,
			String department) {
		super();
		this.name = name;
		this.link = link;
		this.id = id;
		this.searchTerms = searchTerms;
		this.roomNr = roomNr;
		this.roomType = roomType;
		this.studyCourse = studyCourse;
		this.department = department;
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

	public String getStudyCourse() {
		return studyCourse;
	}

	public void setStudyCourse(String studyCourse) {
		this.studyCourse = studyCourse;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "RoomModel [name=" + name + ", link=" + link + ", id=" + id
				+ ", searchTerms=" + Arrays.toString(searchTerms) + ", roomNr="
				+ roomNr + ", roomType=" + roomType + ", studyCourse="
				+ studyCourse + ", department=" + department + "]";
	}

}
