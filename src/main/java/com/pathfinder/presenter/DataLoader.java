package com.pathfinder.presenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import com.google.gson.Gson;
import com.pathfinder.model.CourseModel;
import com.pathfinder.model.GSON_GetResourceDetail_LEVEL_1;
import com.pathfinder.model.GSON_GetResourceDetail_LEVEL_3_1;
import com.pathfinder.model.GSON_GetResources_LEVEL_1;
import com.pathfinder.model.GSON_GetResources_LEVEL_2;
import com.pathfinder.model.PersonModel;
import com.pathfinder.model.RoomModel;

public class DataLoader implements DataLoaderSpec {
	private static final String URL_RESOURSE = "http://localhost:8051/rapla/json/RaplaJsonService?method=getResources&jsonrpc=2.0&params=";
	private static final String URL_RESOURSE_DETAIL = "http://localhost:8051/rapla/json/RaplaJsonService?method=getResource&jsonrpc=2.0&params=";

	private BufferedReader br;
	private static RoomModel[] allRooms;
	private static CourseModel[] allCourses;
	private static PersonModel[] allPersons;

	public void loadAllResources() {
		GSON_GetResources_LEVEL_2[] ResourceData;
		// get all Rooms, transform from ResourceData to RoomModel
		// and get the Detail Information
		ResourceData = gsonGetResources("rooms").getResult();
		allRooms = new RoomModel[ResourceData.length];
		for (int i = 0; i < ResourceData.length; i++) {
			allRooms[i] = new RoomModel(ResourceData[i].getName(),
					ResourceData[i].getLink(), ResourceData[i].getId(),
					ResourceData[i].getSearchTerms(), null, null, null, null);
			loadRoomDetail(allRooms[i]);
		}

		// get all Courses, transform from ResourceData to CourseModel
		// and get the Detail Information
		ResourceData = gsonGetResources("courses").getResult();

		allCourses = new CourseModel[ResourceData.length];
		for (int i = 0; i < ResourceData.length; i++) {
			allCourses[i] = new CourseModel(ResourceData[i].getName(),
					ResourceData[i].getLink(), ResourceData[i].getId(),
					ResourceData[i].getSearchTerms(), null, null, null, null,
					null);
			loadCourseDetail(allCourses[i]);
		}

		// get all Persons, transform from ResourceData to PersoneModel
		// and get the Detail Information
		ResourceData = gsonGetResources("persons").getResult();
		allPersons = new PersonModel[ResourceData.length];
		for (int i = 0; i < ResourceData.length; i++) {
			allPersons[i] = new PersonModel(ResourceData[i].getName(),
					ResourceData[i].getLink(), ResourceData[i].getId(),
					ResourceData[i].getSearchTerms(), null, null, null, null,
					null, null);
			loadPersonDetail(allPersons[i]);
		}

	}

	// this Class should be GENERIC TYPE

	// save the Detail Informations in RoomModel
	private void loadRoomDetail(RoomModel room) {
		GSON_GetResourceDetail_LEVEL_1 dataDetail = gsonGetResourceDetail(room
				.getId());
		Map<String, GSON_GetResourceDetail_LEVEL_3_1> atribute = dataDetail
				.getResult().getAttributeMap();

		if (atribute.get("abteilung") != null)
			room.setDepartment(atribute.get("abteilung").getValue());

		if (atribute.get("studiengang") != null)
			room.setStudyCourse(atribute.get("studiengang").getValue());

		if (atribute.get("raumart") != null)
			room.setRoomType(atribute.get("raumart").getValue());

		if (atribute.get("raumnr") != null)
			room.setRoomNr(atribute.get("raumnr").getValue());
	}

	// should be replaced with the GENERIC TYPE
	// save the Detail Informations in RoomModel
	private void loadCourseDetail(CourseModel course) {
		GSON_GetResourceDetail_LEVEL_1 dataDetail = gsonGetResourceDetail(course
				.getId());
		Map<String, GSON_GetResourceDetail_LEVEL_3_1> atribute = dataDetail
				.getResult().getAttributeMap();

		if (atribute.get("jahrgang") != null)
			course.setVintage(atribute.get("jahrgang").getValue());

		if (atribute.get("abteilung") != null)
			course.setDepartment(atribute.get("abteilung").getValue());

		if (atribute.get("studiengang") != null)
			course.setStudyCourse(atribute.get("studiengang").getValue());

		if (atribute.get("bild") != null)
			course.setPicture(atribute.get("bild").getValue());

		if (atribute.get("raumnr") != null)
			course.setRoomNr(atribute.get("raumnr").getValue());

	}

	private void loadPersonDetail(PersonModel person) {
		GSON_GetResourceDetail_LEVEL_1 dataDetail = gsonGetResourceDetail(person
				.getId());
		Map<String, GSON_GetResourceDetail_LEVEL_3_1> atribute = dataDetail
				.getResult().getAttributeMap();

		if (atribute.get("abteilung") != null)
			person.setDepartment(atribute.get("abteilung").getValue());

		if (atribute.get("studiengang") != null)
			person.setStudyCourse(atribute.get("studiengang").getValue());

		if (atribute.get("email") != null)
			person.setEmail(atribute.get("email").getValue());

		if (atribute.get("bild") != null)
			person.setPicture(atribute.get("bild").getValue());

		if (atribute.get("telefon") != null)
			person.setTelefonNr(atribute.get("telefon").getValue());

		if (atribute.get("raumnr") != null)
			person.setRoomNr(atribute.get("raumnr").getValue());

	}

	private GSON_GetResources_LEVEL_1 gsonGetResources(String resource) {

		try {
			br = new BufferedReader(new InputStreamReader(new URL(URL_RESOURSE
					+ "%5B%22" + resource + "%22%2C%22%22%5D").openStream()));
		} catch (MalformedURLException e) {
			System.out.println("Error loading URL by loading Resource: "
					+ resource);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error loading URL by loading Resource: "
					+ resource);
			e.printStackTrace();
		}

		GSON_GetResources_LEVEL_1 ResourceData = new Gson().fromJson(br,
				GSON_GetResources_LEVEL_1.class);

		if (ResourceData != null) {
			System.out.println("Resource: " + resource + " is loaded");
			return ResourceData;
		} else {
			return null;
		}

	}

	private GSON_GetResourceDetail_LEVEL_1 gsonGetResourceDetail(String id) {

		try {
			br = new BufferedReader(new InputStreamReader(
					new URL(URL_RESOURSE_DETAIL + "%5B%22" + id + "%22%5D")
							.openStream()));
		} catch (MalformedURLException e) {
			System.out.println("Error loading URL by loading ResourceDetail: "
					+ id);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error loading URL by loading ResourceDetail: "
					+ id);
			e.printStackTrace();
		}

		GSON_GetResourceDetail_LEVEL_1 ResourceDetailData = new Gson()
				.fromJson(br, GSON_GetResourceDetail_LEVEL_1.class);

		if (ResourceDetailData != null) {
			System.out.println("Resource: " + id + " is loaded");
			return ResourceDetailData;
		} else {
			return null;
		}
	}

	public RoomModel[] getAllRooms() {
		return allRooms;
	}

	public CourseModel[] getAllCourses() {
		return allCourses;
	}

	public PersonModel[] getAllPersons() {
		return allPersons;
	}

}
