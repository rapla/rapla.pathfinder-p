package com.pathfinder.presenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;
import com.pathfinder.model.CourseDetailModel;
import com.pathfinder.model.DataDetailModel;
import com.pathfinder.model.DataModel;
import com.pathfinder.model.PersonDetailModel;
import com.pathfinder.model.RoomDetailModel;

public class DataLoader implements DataLoaderSpec {
	private BufferedReader br;

	private DataModel gsonGetResources(String resource) {

		try {
			br = new BufferedReader(
					new InputStreamReader(
							new URL(
									"http://localhost:8051/rapla/json/RaplaJsonService?method=getResources&jsonrpc=2.0&params=%5B%22"
											+ resource + "%22%2C%22%22%5D")
									.openStream()));
		} catch (MalformedURLException e) {
			System.out.println("Error loading URL by loading Resource: "
					+ resource);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error loading URL by loading Resource: "
					+ resource);
			e.printStackTrace();
		}

		DataModel ResourceData = new Gson().fromJson(br, DataModel.class);

		if (ResourceData != null) {
			System.out.println("Resource: " + resource + " is loaded");
			return ResourceData;
		} else {
			return null;
		}

	}

	private DataDetailModel gsonGetResourceDetail(String id) {

		try {
			br = new BufferedReader(
					new InputStreamReader(
							new URL(
									"http://localhost:8051/rapla/json/RaplaJsonService?method=getResource&jsonrpc=2.0&params=%5B%22"
											+ id + "%22%5D").openStream()));
		} catch (MalformedURLException e) {
			System.out.println("Error loading URL by loading ResourceDetail: "
					+ id);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error loading URL by loading ResourceDetail: "
					+ id);
			e.printStackTrace();
		}

		DataDetailModel ResourceDetailData = new Gson().fromJson(br,
				DataDetailModel.class);

		if (ResourceDetailData != null) {
			System.out.println("Resource: " + id + " is loaded");
			return ResourceDetailData;
		} else {
			return null;
		}
	}

	public void loadAllResources() {
		DataModel.setAllRooms(gsonGetResources("rooms").getResult());
		DataModel.setAllCourses(gsonGetResources("courses").getResult());
		DataModel.setAllPersons(gsonGetResources("persons").getResult());

	}

	public CourseDetailModel getCourseDetail(String id) {
		DataDetailModel dataDetail = gsonGetResourceDetail(id);
		String name = dataDetail.getResult().getAttributeMap().get("name")
				.getValue();
		String vintage = dataDetail.getResult().getAttributeMap()
				.get("jahrgang").getValue();
		String department = dataDetail.getResult().getAttributeMap()
				.get("abteilung").getValue();
		String studyCourse = dataDetail.getResult().getAttributeMap()
				.get("studiengang").getValue();
		String picture = dataDetail.getResult().getAttributeMap().get("bild")
				.getValue();
		String roomNr = dataDetail.getResult().getAttributeMap().get("raumnr")
				.getValue();
		return new CourseDetailModel(name, vintage, department, studyCourse,
				picture, roomNr);

	}

	public RoomDetailModel getRoomDetail(String id) {
		DataDetailModel dataDetail = gsonGetResourceDetail(id);
		String roomName = dataDetail.getResult().getAttributeMap().get("name")
				.getValue();
		String department = dataDetail.getResult().getAttributeMap()
				.get("abteilung").getValue();
		String studyCourse = dataDetail.getResult().getAttributeMap()
				.get("studiengang").getValue();
		String roomType = dataDetail.getResult().getAttributeMap()
				.get("raumart").getValue();
		String roomNr = dataDetail.getResult().getAttributeMap().get("raumnr")
				.getValue();

		return new RoomDetailModel(roomName, roomNr, roomType, studyCourse,
				department);
	}

	public PersonDetailModel getPersonDetail(String id) {
		DataDetailModel dataDetail = gsonGetResourceDetail(id);
		String name = dataDetail.getResult().getAttributeMap().get("name")
				.getValue();
		String department = dataDetail.getResult().getAttributeMap()
				.get("abteilung").getValue();
		String studyCourse = dataDetail.getResult().getAttributeMap()
				.get("studiengang").getValue();
		String email = dataDetail.getResult().getAttributeMap().get("email")
				.getValue();
		String picture = dataDetail.getResult().getAttributeMap().get("bild")
				.getValue();
		String telefonNr = dataDetail.getResult().getAttributeMap()
				.get("telefon").getValue();
		String roomNr = dataDetail.getResult().getAttributeMap().get("raumnr")
				.getValue();

		return new PersonDetailModel(name, department, studyCourse, email,
				picture, telefonNr, roomNr);
	}
}
