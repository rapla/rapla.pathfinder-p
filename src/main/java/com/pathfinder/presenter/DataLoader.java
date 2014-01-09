package com.pathfinder.presenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.pathfinder.model.CourseModel;
import com.pathfinder.model.PersonModel;
import com.pathfinder.model.PoiModel;
import com.pathfinder.model.ResourceModel;
import com.pathfinder.model.RoomModel;
import com.pathfinder.model.gson.Attribut;
import com.pathfinder.model.gson.Category;
import com.pathfinder.model.gson.CategoryResult;
import com.pathfinder.model.gson.ResourceDetailResult;
import com.pathfinder.model.gson.ResourcesResult;
import com.pathfinder.util.properties.ApplicationProperties;
import com.pathfinder.util.properties.ApplicationPropertiesSpec;
import com.pathfinder.util.properties.PropertiesKey;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.UI;

/**
 * Load data from the rapla server
 * 
 * @author igor
 * 
 */
@Deprecated
public class DataLoader implements DataLoaderSpec {

	private static final Logger LOGGER = LogManager.getLogger(DataLoader.class);

	private final String BASE_URL = ApplicationProperties.getInstance()
			.getProperty(PropertiesKey.RAPLA_BASE_URL);

	private final String RESOURCES_METHOD = BASE_URL + "/getResources?";
	private final String RESOURCE_DETAIL_METHOD = BASE_URL + "/getResource?";
	private final String ORGANIGRAM_METHOD = BASE_URL + "/getOrganigram";

	private final String REQUEST_PERSONS = "persons";
	private final String REQUEST_ROOMS = "rooms";
	private final String REQUEST_POIS = "sonstiges";
	private final String REQUEST_COURSES = "courses";
	private final String REQUEST_ORGANIGRAM = "organigram";

	private final String ERROR_MASSAGE_LOADING_RESOURCE = "Error loading resource: ";
	private final String ERROR_MASSAGE_LOADING_RESOURCE_DETAIL = "Error loading resource detail - id: ";
	private final String ERROR_MESSAGE_EMPTY_RESSOURCE = "Resource is empty";

	private BufferedReader br;

	private final BeanItemContainer<ResourceModel> roomContainer = new BeanItemContainer<ResourceModel>(
			ResourceModel.class);
	private final BeanItemContainer<ResourceModel> courseContainer = new BeanItemContainer<ResourceModel>(
			ResourceModel.class);
	private final BeanItemContainer<ResourceModel> personContainer = new BeanItemContainer<ResourceModel>(
			ResourceModel.class);
	private final BeanItemContainer<ResourceModel> poiContainer = new BeanItemContainer<ResourceModel>(
			ResourceModel.class);

	/**
	 * Consumer of data have to register themselves to this class, to get
	 * notified, if data changes
	 */
	private List<DataLoaderListenerSpec> dataListener = new ArrayList<DataLoaderListenerSpec>();

	private ApplicationPropertiesSpec properties = ApplicationProperties
			.getInstance();

	private static DataLoader instance;

	private DataLoader() {
		// Load data once synchronously
		loadAllResources();

		// Load all data after specific interval again (see
		// application.properties)
		scheduleDataLoading();

		// Removes 'dead' UIs to avoid endless growth of listeners
		scheduleListenerRemover();
	}

	private synchronized void loadAllResources() {

		/* Reset all resource data */
		LOGGER.info("Reset all resource data");
		roomContainer.removeAllItems();
		courseContainer.removeAllItems();
		personContainer.removeAllItems();
		poiContainer.removeAllItems();

		/* Get the data */
		LOGGER.info("Begin loading all resource data");
		this.loadAllRooms();
		this.loadAllCourses();
		this.loadAllPersons();
		this.loadAllPois();

		// load Faculty
		this.loadFaculty();

	}

	private void notifyDataLoaderListener() {
		LOGGER.trace("Notify all UIs that data changed");
		for (DataLoaderListenerSpec listener : dataListener) {
			listener.dataUpdated();
		}
	}

	private void loadAllRooms() {
		// Get all rooms and all detail information
		ResourcesResult resourcesResult = gsonGetResources(REQUEST_ROOMS, "");

		if (resourcesResult != null)
			for (ResourceModel roomGet : resourcesResult.getResult()) {
				ResourceModel room = new ResourceModel(roomGet.getName(),
						roomGet.getLink(), roomGet.getId(),
						roomGet.getSearchTerms());
				roomContainer.addItem(room);
			}
	}

	private void loadAllCourses() {
		// Get all courses and all detail information
		ResourcesResult resourcesResult = gsonGetResources(REQUEST_COURSES, "");

		if (resourcesResult != null)
			for (ResourceModel courseGet : resourcesResult.getResult()) {
				ResourceModel course = new ResourceModel(courseGet.getId(),
						courseGet.getName(), courseGet.getLink(),
						courseGet.getSearchTerms());
				courseContainer.addItem(course);
			}
	}

	private void loadAllPersons() {
		// Get all persons and all detail information
		ResourcesResult resourcesResult = gsonGetResources(REQUEST_PERSONS, "");

		if (resourcesResult != null)
			for (ResourceModel personGet : resourcesResult.getResult()) {
				ResourceModel person = new ResourceModel(personGet.getId(),
						personGet.getName(), personGet.getLink(),
						personGet.getSearchTerms());
				personContainer.addItem(person);
			}
	}

	private void loadAllPois() {
		// Get all pois and all detail information
		ResourcesResult resourcesResult = gsonGetResources(REQUEST_POIS, "");

		if (resourcesResult != null)
			for (ResourceModel poiGet : resourcesResult.getResult()) {
				ResourceModel poi = new ResourceModel(poiGet.getName(),
						poiGet.getLink(), poiGet.getId(),
						poiGet.getSearchTerms());
				poiContainer.addItem(poi);
			}
	}

	@Deprecated
	private void loadRoomDetail(RoomModel room) {
		ResourceDetailResult dataDetail = gsonGetResourceDetail(room.getId());
		Map<String, Attribut> attribute = dataDetail.getResult()
				.getAttributeMap();

		if (attribute.get("abteilung") != null)
			room.setDepartment(attribute.get("abteilung").getValue());

		if (attribute.get("studiengang") != null)
			room.setCourse(attribute.get("studiengang").getValue());

		if (attribute.get("raumart") != null)
			room.setRoomType(attribute.get("raumart").getValue());

		if (attribute.get("raumnr") != null)
			room.setRoomNr(attribute.get("raumnr").getValue());
	}

	@Deprecated
	private void loadCourseDetail(CourseModel course) {
		ResourceDetailResult dataDetail = gsonGetResourceDetail(course.getId());
		Map<String, Attribut> attribute = dataDetail.getResult()
				.getAttributeMap();

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

	@Deprecated
	private void loadPersonDetail(PersonModel person) {
		ResourceDetailResult dataDetail = gsonGetResourceDetail(person.getId());
		Map<String, Attribut> attribute = dataDetail.getResult()
				.getAttributeMap();

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

	@Deprecated
	private void loadPoiDetail(PoiModel poi) {
		ResourceDetailResult dataDetail = gsonGetResourceDetail(poi.getId());
		Map<String, Attribut> attribute = dataDetail.getResult()
				.getAttributeMap();

		if (attribute.get("raumnr") != null)
			poi.setRoomNr(attribute.get("raumnr").getValue());

		if (attribute.get("bild") != null)
			poi.setPicture(attribute.get("bild").getValue());
	}

	private ResourcesResult gsonGetResources(String resource, String categoryId) {
		String url = RESOURCES_METHOD + "resourceType=" + resource
				+ "&categoryId=" + categoryId;
		// LOGGER.trace("URL: " + url);

		try {
			br = new BufferedReader(new InputStreamReader(
					new URL(url).openStream()));
		} catch (MalformedURLException e) {
			LOGGER.error(ERROR_MASSAGE_LOADING_RESOURCE + resource, e);
			return null;
		} catch (IOException e) {
			LOGGER.error(ERROR_MASSAGE_LOADING_RESOURCE + resource, e);
			return null;
		}

		ResourcesResult ResourceData = new Gson().fromJson(br,
				ResourcesResult.class);

		// Force Error
		try {
			ResourceData.getResult();
			if ("".equals(categoryId)) {
				LOGGER.info(resource + " loaded");
				return ResourceData;
			} else if (!"".equals(categoryId)) {
				return ResourceData;
			} else {
				return null;
			}
		} catch (NullPointerException ex) {
			LOGGER.error(ERROR_MASSAGE_LOADING_RESOURCE + resource);
			return null;
		}
	}

	private ResourceDetailResult gsonGetResourceDetail(String resourceId) {
		String url = RESOURCE_DETAIL_METHOD + "resourceId=" + resourceId;
		try {
			br = new BufferedReader(new InputStreamReader(
					new URL(url).openStream()));
		} catch (MalformedURLException e) {
			LOGGER.error(ERROR_MASSAGE_LOADING_RESOURCE_DETAIL + resourceId, e);
			return null;
		} catch (IOException e) {
			LOGGER.error(ERROR_MASSAGE_LOADING_RESOURCE_DETAIL + resourceId, e);
			return null;
		}

		ResourceDetailResult ResourceDetailData = new Gson().fromJson(br,
				ResourceDetailResult.class);

		// Force Error
		try {
			ResourceDetailData.getResult();
			return ResourceDetailData;
		} catch (NullPointerException ex) {
			LOGGER.error(ERROR_MASSAGE_LOADING_RESOURCE_DETAIL + resourceId);
			return null;
		}
	}

	private CategoryResult gsonGetOrganigram() {
		String url = ORGANIGRAM_METHOD;
		try {
			br = new BufferedReader(new InputStreamReader(
					new URL(url).openStream()));
		} catch (MalformedURLException e) {
			LOGGER.error(ERROR_MASSAGE_LOADING_RESOURCE + REQUEST_ORGANIGRAM, e);
			return null;
		} catch (IOException e) {
			LOGGER.error(ERROR_MASSAGE_LOADING_RESOURCE + REQUEST_ORGANIGRAM, e);
			return null;
		}

		CategoryResult organigramResult = new Gson().fromJson(br,
				CategoryResult.class);

		// Force Error
		try {
			organigramResult.getResult();
			LOGGER.info(REQUEST_ORGANIGRAM + " loaded");
			return organigramResult;
		} catch (NullPointerException ex) {
			LOGGER.error(ERROR_MASSAGE_LOADING_RESOURCE + REQUEST_ORGANIGRAM);
			return null;
		}
	}

	private void loadFaculty() {
		// load the root facultys
		CategoryResult categoryResult = gsonGetOrganigram();
		List<ResourceModel> ResourceData;

		if (categoryResult != null)
			for (Category faculty_get : categoryResult.getResult()) {

				try {
					// get all courses in an special faculty
					ResourcesResult resourcesResult = gsonGetResources(
							REQUEST_COURSES, faculty_get.getId());
					if (resourcesResult != null)
						// look if there is any courses with the same id in the
						// RAM
						for (ResourceModel course_get : resourcesResult
								.getResult()) {
							ResourceModel course = courseContainer.getItem(
									course_get.getId()).getBean();
							if (course != null) {
								// add the actual category information to the
								// found
								// item
								course.setFaculty(faculty_get.getName());
							}

						}
				} catch (Exception e) {
					LOGGER.info("Faculty " + faculty_get.getName()
							+ " has no courses");
				}

				try {
					// get all persons in an special faculty
					ResourceData = gsonGetResources(REQUEST_PERSONS,
							faculty_get.getId()).getResult();

					// look if there is any persons with the same id in the RAM
					for (ResourceModel person_get : ResourceData) {
						ResourceModel person = personContainer.getItem(
								person_get.getId()).getBean();
						if (person != null) {
							// add the actual category information to the found
							// item
							person.setFaculty(faculty_get.getName());
						}
					}
				} catch (Exception e) {
					LOGGER.info("Faculty " + faculty_get.getName()
							+ " has no persons");
				}
			}
	}

	@Override
	public BeanItemContainer<ResourceModel> getRoomContainer() {
		return roomContainer;
	}

	@Override
	public BeanItemContainer<ResourceModel> getCourseContainer() {
		return courseContainer;
	}

	@Override
	public BeanItemContainer<ResourceModel> getPersonContainer() {
		return personContainer;
	}

	@Override
	public BeanItemContainer<ResourceModel> getPoiContainer() {
		return poiContainer;
	}

	@Override
	public void addDataListener(DataLoaderListenerSpec listener) {
		dataListener.add(listener);
		LOGGER.trace("DataLoaderListener added");
	}

	private TimerTask getTimerTask() {
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				LOGGER.trace("Get new data from the RAPLA-Server");
				reloadAllData();
				LOGGER.trace("Updated data from the RAPLA-Server");
			}
		};
		return timerTask;
	}

	private void scheduleDataLoading() {

		// Starts after specified interval and repeats in the same interval (see
		// application.properties)
		long loadInterval = properties
				.getIntProperty(PropertiesKey.DATA_LOAD_INTERVALL);
		new Timer().schedule(getTimerTask(), loadInterval, loadInterval);

	}

	@Override
	public void reloadAllData() {
		loadAllResources();

		// Notify all UIs
		notifyDataLoaderListener();
	}

	private void scheduleListenerRemover() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				removeDeadListener();
			}
		};

		new Timer().schedule(task, 0, 5 * 60 * 1000);
	}

	private void removeDeadListener() {
		long tenMinutesAgo = new Date().getTime() - 10 * 60 * 1000;
		List<DataLoaderListenerSpec> listenerToBeRemoved = new ArrayList<DataLoaderListenerSpec>();

		// Find all listener that are UIs and didn't send two consecutive
		// heartbeats (UI sends a heartbeat every 5 Minutes if alive)
		Iterator<DataLoaderListenerSpec> iterator = dataListener.iterator();
		while (iterator.hasNext()) {
			DataLoaderListenerSpec listener = iterator.next();
			if (listener instanceof UI) {
				long lastHeartbeat = ((UI) listener)
						.getLastHeartbeatTimestamp();
				if (lastHeartbeat < tenMinutesAgo)
					listenerToBeRemoved.add(listener);
			}
		}

		dataListener.removeAll(listenerToBeRemoved);
	}

	public static DataLoader getInstance() {
		if (instance == null) {
			instance = new DataLoader();
		}
		return instance;
	}
}