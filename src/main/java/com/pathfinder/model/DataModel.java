package com.pathfinder.model;

import java.util.List;

public class DataModel {

	private List<ResourceModel> result;
	private static List<ResourceModel> allRooms;
	private static List<ResourceModel> allCourses;
	private static List<ResourceModel> allPersons;

	public List<ResourceModel> getResult() {
		return result;
	}

	public void setResult(List<ResourceModel> result) {
		this.result = result;
	}

	public static List<ResourceModel> getAllRooms() {
		return allRooms;
	}

	public static void setAllRooms(List<ResourceModel> allRooms) {
		DataModel.allRooms = allRooms;
	}

	public static List<ResourceModel> getAllCourses() {
		return allCourses;
	}

	public static void setAllCourses(List<ResourceModel> allCourses) {
		DataModel.allCourses = allCourses;
	}

	public static List<ResourceModel> getAllPersons() {
		return allPersons;
	}

	public static void setAllPersons(List<ResourceModel> allPersons) {
		DataModel.allPersons = allPersons;
	}

}
