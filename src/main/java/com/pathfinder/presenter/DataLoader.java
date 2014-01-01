package com.pathfinder.presenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.pathfinder.model.CourseModel;
import com.pathfinder.model.PersonModel;
import com.pathfinder.model.PoiModel;
import com.pathfinder.model.RoomModel;
import com.pathfinder.model.gson.GsonGetResourceDetailLevel1;
import com.pathfinder.model.gson.GsonGetResourceDetailLevel31;
import com.pathfinder.model.gson.GsonGetResourcesLevel1;
import com.pathfinder.model.gson.GsonGetResourcesLevel2;
import com.pathfinder.util.properties.ApplicationProperties;
import com.pathfinder.util.properties.PropertiesKey;
import com.vaadin.data.util.BeanItemContainer;

/**
 * Load data from the rapla server
 * 
 * @author igor
 * 
 */
public class DataLoader implements DataLoaderSpec {

	private static final Logger logger = LogManager.getLogger(DataLoader.class);

	private final String BASE_URL = ApplicationProperties.getInstance()
			.getProperty(PropertiesKey.GSON_BASE_URL);

	private final String URL_RESOURCE = BASE_URL
			+ "?method=getResources&jsonrpc=2.0&params=";

	private final String URL_RESOURCE_DETAIL = BASE_URL
			+ "?method=getResource&jsonrpc=2.0&params=";

	private final String EMPTYPARAMETER = ",''";

	private final String REQUEST_PERSONS = "persons";
	private final String REQUEST_ROOMS = "raum";
	private final String REQUEST_POIS = "sonstiges";
	private final String REQUEST_COURSES = "courses";

	private final String MASSAGE_REQUST_DETAIL_LOADING = "Load Details...";
	private final String MASSAGE_REQUST_DETAIL_LOADED = "Details are loaded";
	private final String MASSAGE_ERROR_LOADING_URL_RESOURCE = "Error loading URL by loading Resource: ";
	private final String MASSAGE_ERROR_LOADING_URL_RESOURCE_DETAIL = "Error loading URL by loading ResourceDetail id: ";

	private BufferedReader br;
	// TODO Why does this not work without static oO?
	private static BeanItemContainer<RoomModel> roomContainer = new BeanItemContainer<RoomModel>(
			RoomModel.class);
	private static BeanItemContainer<CourseModel> courseContainer = new BeanItemContainer<CourseModel>(
			CourseModel.class);
	private static BeanItemContainer<PersonModel> personContainer = new BeanItemContainer<PersonModel>(
			PersonModel.class);
	private static BeanItemContainer<PoiModel> poiContainer = new BeanItemContainer<PoiModel>(
			PoiModel.class);

	@Override
	public void loadAllResources() {
		// Reset all Data
		roomContainer.removeAllItems();
		courseContainer.removeAllItems();
		personContainer.removeAllItems();
		poiContainer.removeAllItems();

		logger.info(MASSAGE_REQUST_DETAIL_LOADING);

		// Get the data
		this.loadAllRooms();
		this.loadAllCourses();
		this.loadAllPersons();
		this.loadAllPois();
	}

	private void loadAllRooms() {
		// Get all rooms and all detail information
		GsonGetResourcesLevel1 gsonGetResourcesLevel1 = gsonGetResources(REQUEST_ROOMS);

		if (gsonGetResourcesLevel1 != null)
			for (GsonGetResourcesLevel2 roomGet : gsonGetResourcesLevel1
					.getResult()) {
				RoomModel room_save = new RoomModel(roomGet.getName(),
						roomGet.getLink(), roomGet.getId(),
						roomGet.getSearchTerms(), null, null, null, null);
				roomContainer.addItem(room_save);
				loadRoomDetail(room_save);
			}

		logger.info(MASSAGE_REQUST_DETAIL_LOADED);
	}

	// save the Detail Informations in RoomModel
	private void loadRoomDetail(RoomModel room) {
		GsonGetResourceDetailLevel1 dataDetail = gsonGetResourceDetail(room
				.getId());
		Map<String, GsonGetResourceDetailLevel31> atribute = dataDetail
				.getResult().getAttributeMap();

		if (atribute.get("abteilung") != null)
			room.setDepartment(atribute.get("abteilung").getValue());

		if (atribute.get("studiengang") != null)
			room.setCourse(atribute.get("studiengang").getValue());

		if (atribute.get("raumart") != null)
			room.setRoomType(atribute.get("raumart").getValue());

		if (atribute.get("raumnr") != null)
			room.setRoomNr(atribute.get("raumnr").getValue());
	}

	private void loadAllCourses() {
		// Get all courses and all detail information
		GsonGetResourcesLevel1 gsonGetResourcesLevel1 = gsonGetResources(REQUEST_COURSES);

		if (gsonGetResourcesLevel1 != null)
			for (GsonGetResourcesLevel2 courseGet : gsonGetResourcesLevel1
					.getResult()) {
				CourseModel course_save = new CourseModel(courseGet.getName(),
						courseGet.getLink(), courseGet.getId(),
						courseGet.getSearchTerms(), null, null, null, null,
						null);
				courseContainer.addItem(course_save);
				loadCourseDetail(course_save);
			}

		logger.info(MASSAGE_REQUST_DETAIL_LOADED);
	}

	// this Class should be GENERIC TYPE

	// should be replaced with the GENERIC TYPE
	// save the Detail Informations in RoomModel
	private void loadCourseDetail(CourseModel course) {
		GsonGetResourceDetailLevel1 dataDetail = gsonGetResourceDetail(course
				.getId());
		Map<String, GsonGetResourceDetailLevel31> atribute = dataDetail
				.getResult().getAttributeMap();

		if (atribute.get("jahrgang") != null)
			course.setVintage(atribute.get("jahrgang").getValue());

		if (atribute.get("abteilung") != null)
			course.setDepartment(atribute.get("abteilung").getValue());

		if (atribute.get("studiengang") != null)
			course.setCourse(atribute.get("studiengang").getValue());

		if (atribute.get("bild") != null)
			course.setPicture(atribute.get("bild").getValue());

		if (atribute.get("raumnr") != null)
			course.setRoomNr(atribute.get("raumnr").getValue());
	}

	private void loadAllPersons() {
		// Get all persons and all detail information
		GsonGetResourcesLevel1 gsonGetResourcesLevel1 = gsonGetResources(REQUEST_PERSONS);

		if (gsonGetResourcesLevel1 != null)
			for (GsonGetResourcesLevel2 personGet : gsonGetResourcesLevel1
					.getResult()) {
				PersonModel person_save = new PersonModel(personGet.getName(),
						personGet.getLink(), personGet.getId(),
						personGet.getSearchTerms(), null, null, null, null,
						null, null);
				personContainer.addItem(person_save);
				loadPersonDetail(person_save);
			}

		logger.info(MASSAGE_REQUST_DETAIL_LOADED);
	}

	// this Class should be GENERIC TYPE

	private void loadPersonDetail(PersonModel person) {
		GsonGetResourceDetailLevel1 dataDetail = gsonGetResourceDetail(person
				.getId());
		Map<String, GsonGetResourceDetailLevel31> atribute = dataDetail
				.getResult().getAttributeMap();

		if (atribute.get("abteilung") != null)
			person.setDepartment(atribute.get("abteilung").getValue());

		if (atribute.get("studiengang") != null)
			person.setCourse(atribute.get("studiengang").getValue());

		if (atribute.get("email") != null)
			person.setEmail(atribute.get("email").getValue());

		if (atribute.get("bild") != null)
			person.setPicture(atribute.get("bild").getValue());

		if (atribute.get("telefon") != null)
			person.setTelephone(atribute.get("telefon").getValue());

		if (atribute.get("raumnr") != null)
			person.setRoomNr(atribute.get("raumnr").getValue());
	}

	private void loadAllPois() {
		// Get all pois and all detail information
		GsonGetResourcesLevel1 gsonGetResourcesLevel1 = gsonGetResources(REQUEST_POIS);

		if (gsonGetResourcesLevel1 != null)
			for (GsonGetResourcesLevel2 poiGet : gsonGetResourcesLevel1
					.getResult()) {
				PoiModel poi_save = new PoiModel(poiGet.getName(),
						poiGet.getLink(), poiGet.getId(),
						poiGet.getSearchTerms(), null, null);
				poiContainer.addItem(poi_save);
				loadPOIDetail(poi_save);
			}

		logger.info(MASSAGE_REQUST_DETAIL_LOADED);
	}

	// this Class should be GENERIC TYPE

	// TODO should be replaced with the GENERIC TYPE
	// save the Detail Informations in POIModel
	private void loadPOIDetail(PoiModel poi) {
		GsonGetResourceDetailLevel1 dataDetail = gsonGetResourceDetail(poi
				.getId());
		Map<String, GsonGetResourceDetailLevel31> atribute = dataDetail
				.getResult().getAttributeMap();

		if (atribute.get("raumnr") != null)
			poi.setRoomNr(atribute.get("raumnr").getValue());

		if (atribute.get("bild") != null)
			poi.setPicture(atribute.get("bild").getValue());
	}

	private GsonGetResourcesLevel1 gsonGetResources(String resource) {
		try {
			br = new BufferedReader(new InputStreamReader(new URL(URL_RESOURCE
					+ "['" + resource + "'" + EMPTYPARAMETER + EMPTYPARAMETER
					+ "]").openStream()));
		} catch (MalformedURLException e) {
			logger.error(MASSAGE_ERROR_LOADING_URL_RESOURCE + resource, e);
			return null;
		} catch (IOException e) {
			logger.error(MASSAGE_ERROR_LOADING_URL_RESOURCE + resource, e);
			return null;
		}

		GsonGetResourcesLevel1 ResourceData = new Gson().fromJson(br,
				GsonGetResourcesLevel1.class);

		if (ResourceData != null) {
			logger.info("Resource: " + resource + " is loaded");
			return ResourceData;
		} else {
			return null;
		}
	}

	private GsonGetResourceDetailLevel1 gsonGetResourceDetail(String id) {
		try {
			br = new BufferedReader(new InputStreamReader(new URL(
					URL_RESOURCE_DETAIL + "['" + id + "'" + EMPTYPARAMETER
							+ "]").openStream()));
		} catch (MalformedURLException e) {
			logger.error(MASSAGE_ERROR_LOADING_URL_RESOURCE_DETAIL + id, e);
			return null;
		} catch (IOException e) {
			logger.error(MASSAGE_ERROR_LOADING_URL_RESOURCE_DETAIL + id, e);
			return null;
		}

		GsonGetResourceDetailLevel1 ResourceDetailData = new Gson().fromJson(
				br, GsonGetResourceDetailLevel1.class);

		if (ResourceDetailData != null) {
			return ResourceDetailData;
		} else {
			logger.error(MASSAGE_ERROR_LOADING_URL_RESOURCE_DETAIL + id);
			return null;
		}
	}

	@Override
	public BeanItemContainer<RoomModel> getRoomContainer() {
		return roomContainer;
	}

	@Override
	public BeanItemContainer<CourseModel> getCourseContainer() {
		return courseContainer;
	}

	@Override
	public BeanItemContainer<PersonModel> getPersonContainer() {
		return personContainer;
	}

	@Override
	public BeanItemContainer<PoiModel> getPoiContainer() {
		return poiContainer;
	}
}