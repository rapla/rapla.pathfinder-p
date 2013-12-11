package com.pathfinder.presenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import com.google.gson.Gson;
import com.pathfinder.model.GSON_GetResourceDetail_LEVEL_1;
import com.pathfinder.model.GSON_GetResourceDetail_LEVEL_3_1;
import com.pathfinder.model.GSON_GetResources_LEVEL_1;
import com.pathfinder.model.GSON_GetResources_LEVEL_2;
import com.pathfinder.model.RoomModel;

public class DataLoader implements DataLoaderSpec {
	private static final String URL_RESOURSE = "http://localhost:8051/rapla/json/RaplaJsonService?method=getResources&jsonrpc=2.0&params=";
	private static final String URL_RESOURSE_DETAIL = "http://localhost:8051/rapla/json/RaplaJsonService?method=getResource&jsonrpc=2.0&params=";

	private BufferedReader br;
	private static RoomModel[] allRooms;

	public void loadAllResources() {
		GSON_GetResources_LEVEL_2[] ResourceData = gsonGetResources("rooms")
				.getResult();
		allRooms = new RoomModel[ResourceData.length];
		for (int i = 0; i < ResourceData.length; i++) {
			allRooms[i] = new RoomModel(ResourceData[i].getName(),
					ResourceData[i].getLink(), ResourceData[i].getId(),
					ResourceData[i].getSearchTerms(), null, null, null, null);
			loadDetail(allRooms[i]);
		}

	}

	// this Class should be GENERIC TYPE
	private void loadDetail(RoomModel room) {
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

	// public CourseDetailModel getCourseDetail(String id) {
	// GSON_GetResourceDetail_LEVEL_1 dataDetail = gsonGetResourceDetail(id);
	// String name = dataDetail.getResult().getAttributeMap().get("name")
	// .getValue();
	// String vintage = dataDetail.getResult().getAttributeMap()
	// .get("jahrgang").getValue();
	// String department = dataDetail.getResult().getAttributeMap()
	// .get("abteilung").getValue();
	// String studyCourse = dataDetail.getResult().getAttributeMap()
	// .get("studiengang").getValue();
	// String picture = dataDetail.getResult().getAttributeMap().get("bild")
	// .getValue();
	// String roomNr = dataDetail.getResult().getAttributeMap().get("raumnr")
	// .getValue();
	// return new CourseDetailModel(name, vintage, department, studyCourse,
	// picture, roomNr);
	//
	// }
	//

	//
	// public PersonDetailModel getPersonDetail(String id) {
	// GSON_GetResourceDetail_LEVEL_1 dataDetail = gsonGetResourceDetail(id);
	// String name = dataDetail.getResult().getAttributeMap().get("name")
	// .getValue();
	// String department = dataDetail.getResult().getAttributeMap()
	// .get("abteilung").getValue();
	// String studyCourse = dataDetail.getResult().getAttributeMap()
	// .get("studiengang").getValue();
	// String email = dataDetail.getResult().getAttributeMap().get("email")
	// .getValue();
	// String picture = dataDetail.getResult().getAttributeMap().get("bild")
	// .getValue();
	// String telefonNr = dataDetail.getResult().getAttributeMap()
	// .get("telefon").getValue();
	// String roomNr = dataDetail.getResult().getAttributeMap().get("raumnr")
	// .getValue();
	//
	// return new PersonDetailModel(name, department, studyCourse, email,
	// picture, telefonNr, roomNr);
	// }

	// //JUST TO TEST BETA
	// public static void main(String[] args) {
	// DataLoader x = new DataLoader();
	// x.loadAllResources();
	//
	// }
}
