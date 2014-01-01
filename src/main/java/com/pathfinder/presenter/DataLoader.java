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
import com.pathfinder.model.ResourceModel;
import com.pathfinder.model.RoomModel;
import com.pathfinder.model.gson.GsonGetResourceDetailLevel1;
import com.pathfinder.model.gson.GsonGetResourceDetailLevel31;
import com.pathfinder.model.gson.GsonGetResourcesLevel1;
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

	private final String URL_EMPTY_PARAMETER = ",''";

	private final String REQUEST_PERSONS = "persons";
	private final String REQUEST_ROOMS = "raum";
	private final String REQUEST_POIS = "sonstiges";
	private final String REQUEST_COURSES = "courses";

	private final String MASSAGE_REQUST_DETAIL_LOADED = "Details are loaded";
	private final String MASSAGE_ERROR_LOADING_URL_RESOURCE = "Error loading URL by loading resource: ";
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
		/* Reset all resource data */
		logger.info("Reset all resource data");
		roomContainer.removeAllItems();
		courseContainer.removeAllItems();
		personContainer.removeAllItems();
		poiContainer.removeAllItems();

		/* Get the data */
		logger.info("Begin loading all resource data");
		this.loadAllRooms();
		this.loadAllCourses();
		this.loadAllPersons();
		this.loadAllPois();
	}

	private void loadAllRooms() {
		// Get all rooms and all detail information
		GsonGetResourcesLevel1 gsonGetResourcesLevel1 = gsonGetResources(REQUEST_ROOMS);

		if (gsonGetResourcesLevel1 != null)
			for (ResourceModel roomGet : gsonGetResourcesLevel1
					.getResult()) {
				RoomModel room = new RoomModel(roomGet.getName(),
						roomGet.getLink(), roomGet.getId(),
						roomGet.getSearchTerms(), null, null, null, null);
				loadRoomDetail(room);
				roomContainer.addItem(room);
			}

		logger.info(MASSAGE_REQUST_DETAIL_LOADED);
	}

	private void loadRoomDetail(RoomModel room) {
		GsonGetResourceDetailLevel1 dataDetail = gsonGetResourceDetail(room
				.getId());
		Map<String, GsonGetResourceDetailLevel31> attribute = dataDetail
				.getResult().getAttributeMap();

		if (attribute.get("abteilung") != null)
			room.setDepartment(attribute.get("abteilung").getValue());

		if (attribute.get("studiengang") != null)
			room.setCourse(attribute.get("studiengang").getValue());

		if (attribute.get("raumart") != null)
			room.setRoomType(attribute.get("raumart").getValue());

		if (attribute.get("raumnr") != null)
			room.setRoomNr(attribute.get("raumnr").getValue());
	}

	private void loadAllCourses() {
		// Get all courses and all detail information
		GsonGetResourcesLevel1 gsonGetResourcesLevel1 = gsonGetResources(REQUEST_COURSES);

		if (gsonGetResourcesLevel1 != null)
			for (ResourceModel courseGet : gsonGetResourcesLevel1
					.getResult()) {
				CourseModel course = new CourseModel(courseGet.getName(),
						courseGet.getLink(), courseGet.getId(),
						courseGet.getSearchTerms(), null, null, null, null,
						null);
				courseContainer.addItem(course);
				loadCourseDetail(course);
			}

		logger.info(MASSAGE_REQUST_DETAIL_LOADED);
	}

	private void loadCourseDetail(CourseModel course) {
		GsonGetResourceDetailLevel1 dataDetail = gsonGetResourceDetail(course
				.getId());
		Map<String, GsonGetResourceDetailLevel31> attribute = dataDetail
				.getResult().getAttributeMap();

		if (attribute.get("jahrgang") != null)
			course.setVintage(attribute.get("jahrgang").getValue());

		if (attribute.get("abteilung") != null)
			course.setDepartment(attribute.get("abteilung").getValue());

		if (attribute.get("studiengang") != null)
			course.setCourse(attribute.get("studiengang").getValue());

		if (attribute.get("bild") != null)
			course.setPicture(attribute.get("bild").getValue());

		if (attribute.get("raumnr") != null)
			course.setRoomNr(attribute.get("raumnr").getValue());
	}

	private void loadAllPersons() {
		// Get all persons and all detail information
		GsonGetResourcesLevel1 gsonGetResourcesLevel1 = gsonGetResources(REQUEST_PERSONS);

		if (gsonGetResourcesLevel1 != null)
			for (ResourceModel personGet : gsonGetResourcesLevel1
					.getResult()) {
				PersonModel person = new PersonModel(personGet.getName(),
						personGet.getLink(), personGet.getId(),
						personGet.getSearchTerms(), null, null, null, null,
						null, null);
				personContainer.addItem(person);
				loadPersonDetail(person);
			}

		logger.info(MASSAGE_REQUST_DETAIL_LOADED);
	}

	private void loadPersonDetail(PersonModel person) {
		GsonGetResourceDetailLevel1 dataDetail = gsonGetResourceDetail(person
				.getId());
		Map<String, GsonGetResourceDetailLevel31> attribute = dataDetail
				.getResult().getAttributeMap();

		if (attribute.get("abteilung") != null)
			person.setDepartment(attribute.get("abteilung").getValue());

		if (attribute.get("studiengang") != null)
			person.setCourse(attribute.get("studiengang").getValue());

		if (attribute.get("email") != null)
			person.setEmail(attribute.get("email").getValue());

		if (attribute.get("bild") != null)
			person.setPicture(attribute.get("bild").getValue());

		if (attribute.get("telefon") != null)
			person.setTelephone(attribute.get("telefon").getValue());

		if (attribute.get("raumnr") != null)
			person.setRoomNr(attribute.get("raumnr").getValue());
	}

	private void loadAllPois() {
		// Get all pois and all detail information
		GsonGetResourcesLevel1 gsonGetResourcesLevel1 = gsonGetResources(REQUEST_POIS);

		if (gsonGetResourcesLevel1 != null)
			for (ResourceModel poiGet : gsonGetResourcesLevel1
					.getResult()) {
				PoiModel poi = new PoiModel(poiGet.getName(), poiGet.getLink(),
						poiGet.getId(), poiGet.getSearchTerms(), null, null);
				poiContainer.addItem(poi);
				loadPoiDetail(poi);
			}

		logger.info(MASSAGE_REQUST_DETAIL_LOADED);
	}

	private void loadPoiDetail(PoiModel poi) {
		GsonGetResourceDetailLevel1 dataDetail = gsonGetResourceDetail(poi
				.getId());
		Map<String, GsonGetResourceDetailLevel31> attribute = dataDetail
				.getResult().getAttributeMap();

		if (attribute.get("raumnr") != null)
			poi.setRoomNr(attribute.get("raumnr").getValue());

		if (attribute.get("bild") != null)
			poi.setPicture(attribute.get("bild").getValue());
	}

	private GsonGetResourcesLevel1 gsonGetResources(String resource) {
		try {
			br = new BufferedReader(new InputStreamReader(new URL(URL_RESOURCE
					+ "['" + resource + "'" + URL_EMPTY_PARAMETER
					+ URL_EMPTY_PARAMETER + "]").openStream()));
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
					URL_RESOURCE_DETAIL + "['" + id + "'" + URL_EMPTY_PARAMETER
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