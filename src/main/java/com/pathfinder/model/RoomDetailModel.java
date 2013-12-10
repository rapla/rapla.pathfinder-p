package com.pathfinder.model;

public class RoomDetailModel {
	// Attention! Events have not yet been incorporated
	private String roomName;
	private String roomNr;
	private String roomType;
	private String studyCourse;
	private String department;

	public RoomDetailModel(String roomName, String roomNr, String roomType,
			String studyCourse, String department) {
		super();
		this.roomName = roomName;
		this.roomNr = roomNr;
		this.roomType = roomType;
		this.studyCourse = studyCourse;
		this.department = department;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
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

}
