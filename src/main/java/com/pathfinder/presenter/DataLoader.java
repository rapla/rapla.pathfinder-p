package com.pathfinder.presenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pathfinder.model.Attribut;
import com.pathfinder.model.Category;
import com.pathfinder.model.FreeRoomModel;
import com.pathfinder.model.ResourceModel;
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
public class DataLoader implements DataLoaderSpec {
	private static final Logger LOGGER = LogManager.getLogger(DataLoader.class);
	private ApplicationPropertiesSpec properties = ApplicationProperties
			.getInstance();

	private final String BASE_URL = ApplicationProperties.getInstance()
			.getProperty(PropertiesKey.RAPLA_BASE_URL);

	private String dhbwEntryPoint = "";

	private final String RESOURCES_METHOD = BASE_URL + "/getResources?";
	private final String RESOURCE_DETAIL_METHOD = BASE_URL + "/getResource?";
	private final String ORGANIGRAM_METHOD = BASE_URL + "/getOrganigram";
	private final String FREE_RESOURCES_METHOD = BASE_URL + "/getFreeResources";

	private final String REQUEST_PARAMETER_PERSONS = "persons";
	private final String REQUEST_PARAMETER_ROOMS = "rooms";
	private final String REQUEST_PARAMETER_POIS = "sonstiges";
	private final String REQUEST_PARAMETER_COURSES = "courses";
	private final String REQUEST_PARAMETER_ORGANIGRAM = "organigram";

	private final String ERROR_MASSAGE_LOADING_RESOURCE = "Error loading resource: ";
	private final String ERROR_MASSAGE_URL_NOT_READABLE = "Error loading URL: ";
	private final String ERROR_MASSAGE_SERVER_NOT_AVAILABLE = "No connection to the server";
	private final String ERROR_MASSAGE_JSON_HANDLING = "Can't handling JSON, maybe some problems with the data.xml";
	private final String ERROR_MASSAGE_LOADING_RESOURCE_DETAIL = "Error loading resource detail - id: ";

	private final BeanItemContainer<ResourceModel> roomContainer = new BeanItemContainer<ResourceModel>(
			ResourceModel.class);
	private final BeanItemContainer<ResourceModel> courseContainer = new BeanItemContainer<ResourceModel>(
			ResourceModel.class);
	private final BeanItemContainer<ResourceModel> personContainer = new BeanItemContainer<ResourceModel>(
			ResourceModel.class);
	private final BeanItemContainer<ResourceModel> poiContainer = new BeanItemContainer<ResourceModel>(
			ResourceModel.class);

	private final JSONParser parser = new JSONParser();
	private JSONObject jsonObject = null;

	private BufferedReader br;

	/**
	 * Consumer of data have to register themselves to this class, to get
	 * notified, if data changes
	 */
	private List<DataLoaderListenerSpec> dataListener = new ArrayList<DataLoaderListenerSpec>();

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
		this.loadResource(REQUEST_PARAMETER_COURSES, courseContainer);
		this.loadResource(REQUEST_PARAMETER_ROOMS, roomContainer);
		this.loadResource(REQUEST_PARAMETER_PERSONS, personContainer);
		this.loadResource(REQUEST_PARAMETER_POIS, poiContainer);

		// load Faculties
		this.loadFaculty();
	}

	private void loadResource(String resourceParameter,
			BeanItemContainer<ResourceModel> resourceContainer) {
		List<ResourceModel> resourceResult = jacksonGetResources(
				resourceParameter, "");

		String type = "";
		switch (resourceParameter) {
		case REQUEST_PARAMETER_COURSES:
			type = "course";
			break;
		case REQUEST_PARAMETER_ROOMS:
			type = "room";
			break;
		case REQUEST_PARAMETER_PERSONS:
			type = "person";
			break;
		case REQUEST_PARAMETER_POIS:
			type = "poi";
			break;
		}

		if (resourceResult != null) {
			for (ResourceModel concreteResult : resourceResult) {
				concreteResult.setType(type);
			}
			resourceContainer.addAll(resourceResult);
		}
	}

	private List<ResourceModel> jacksonGetResources(String resource,
			String categoryId) {
		String url = RESOURCES_METHOD + "resourceType=" + resource
				+ "&categoryId=" + categoryId;
		// LOGGER.trace("URL: " + url);

		try {
			br = new BufferedReader(new InputStreamReader(
					new URL(url).openStream()));

			// Create a standard Jackson mapper object.
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node;

			node = mapper.readTree(br);
			node = node.path("result");

			List<ResourceModel> list = mapper.readValue(node.traverse(),
					new TypeReference<List<ResourceModel>>() {
					});

			if (list.equals(null))
				return null;
			else
				return list;

		} catch (ConnectException e) {
			LOGGER.info(ERROR_MASSAGE_SERVER_NOT_AVAILABLE);
			LOGGER.error(ERROR_MASSAGE_LOADING_RESOURCE + resource, e);
			return null;
		} catch (MalformedURLException e) {
			LOGGER.error(ERROR_MASSAGE_LOADING_RESOURCE + resource, e);
			return null;
		} catch (JsonProcessingException e) {
			LOGGER.error(ERROR_MASSAGE_JSON_HANDLING, e);
			LOGGER.error(ERROR_MASSAGE_LOADING_RESOURCE + resource, e);
			return null;
		} catch (IOException e) {
			LOGGER.error(ERROR_MASSAGE_LOADING_RESOURCE + resource, e);
			return null;
		}
	}

	private List<Category> jacksonGetOrganigram() {
		String url = ORGANIGRAM_METHOD;
		try {
			br = new BufferedReader(new InputStreamReader(
					new URL(url).openStream()));

			// Create a standard Jackson mapper object.
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node;

			node = mapper.readTree(br);
			node = node.path("result");

			List<Category> list = mapper.readValue(node.traverse(),
					new TypeReference<List<Category>>() {
					});

			if (list.equals(null))
				return null;
			else
				return list;
		} catch (ConnectException e) {
			LOGGER.info(ERROR_MASSAGE_SERVER_NOT_AVAILABLE);
			LOGGER.error(ERROR_MASSAGE_LOADING_RESOURCE
					+ REQUEST_PARAMETER_ORGANIGRAM, e);
			return null;
		} catch (MalformedURLException e) {
			LOGGER.error(ERROR_MASSAGE_LOADING_RESOURCE
					+ REQUEST_PARAMETER_ORGANIGRAM, e);
			return null;
		} catch (JsonProcessingException e) {
			LOGGER.error(ERROR_MASSAGE_JSON_HANDLING, e);
			LOGGER.error(ERROR_MASSAGE_LOADING_RESOURCE
					+ REQUEST_PARAMETER_ORGANIGRAM, e);
			return null;
		} catch (IOException e) {
			LOGGER.error(ERROR_MASSAGE_LOADING_RESOURCE
					+ REQUEST_PARAMETER_ORGANIGRAM, e);
			return null;
		}
	}

	private void loadFaculty() {
		// load the root facultys
		List<Category> categoryResult = jacksonGetOrganigram();
		List<ResourceModel> ResourceData;

		if (categoryResult != null)
			for (Category faculty : categoryResult) {

				try {
					// get all courses in an special faculty
					// TODO Use courseContainer - don´t call rapla again
					List<ResourceModel> resourcesResult = jacksonGetResources(
							REQUEST_PARAMETER_COURSES, faculty.getId());
					if (resourcesResult != null)
						// look if there is any courses with the same id in the
						// RAM
						for (ResourceModel course_get : resourcesResult) {
							ResourceModel course = courseContainer.getItem(
									course_get.getId()).getBean();
							if (course != null) {
								// add the actual category information to the
								// found
								// item
								course.setFaculty(faculty.getName());
							}

						}
				} catch (Exception e) {
					LOGGER.info("Faculty " + faculty.getName()
							+ " has no courses");
				}

				try {
					// get all persons in an special faculty
					// TODO Use personContainer - don´t call rapla again
					ResourceData = jacksonGetResources(
							REQUEST_PARAMETER_PERSONS, faculty.getId());

					// look if there is any persons with the same id in the RAM
					for (ResourceModel person_get : ResourceData) {
						ResourceModel person = personContainer.getItem(
								person_get.getId()).getBean();
						if (person != null) {
							// add the actual category information to the found
							// item
							person.setFaculty(faculty.getName());
						}
					}
				} catch (Exception e) {
					LOGGER.info("Faculty " + faculty.getName()
							+ " has no persons");
				}
			}
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

	private void notifyDataLoaderListener() {
		LOGGER.trace("Notify all UIs that data changed");
		for (DataLoaderListenerSpec listener : dataListener) {
			listener.dataUpdated();
		}
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

	@Override
	public void addDataListener(DataLoaderListenerSpec listener) {
		dataListener.add(listener);
		LOGGER.trace("DataLoaderListener added");
	}

	@Override
	public void setDhbwEntryPoint(String dhbwEntryPoint) {
		this.dhbwEntryPoint = dhbwEntryPoint;
	}

	@Override
	public void reloadAllData() {
		loadAllResources();

		// Notify all UIs
		notifyDataLoaderListener();
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

	@SuppressWarnings("finally")
	@Override
	public BeanItemContainer<FreeRoomModel> getFreeResources() {
		FreeRoomModel freeRoom = null;

		BeanItemContainer<FreeRoomModel> freeRoomContainer = new BeanItemContainer<FreeRoomModel>(
				FreeRoomModel.class);
		int counter = 0;

		try {
			br = new BufferedReader(new InputStreamReader(new URL(
					FREE_RESOURCES_METHOD).openStream()));

			jsonObject = (JSONObject) parser.parse(br);

			@SuppressWarnings("unchecked")
			List<JSONObject> freeResourcesResult = (List<JSONObject>) jsonObject
					.get("result");

			if (freeResourcesResult.size() > 5) {
				for (int i = 4; i < freeResourcesResult.size(); i++)
					freeResourcesResult.remove(i);
			}

			Iterator<JSONObject> jsonIterator = freeResourcesResult.iterator();

			if (!freeResourcesResult.isEmpty()) {

				while (jsonIterator.hasNext()) {

					JSONObject result = jsonIterator.next();

					if (counter < 5) {

						List<JSONObject> freeRoomResources = this
								.getFreeResourcesResources(result);

						freeRoom = new FreeRoomModel((String) freeRoomResources
								.get(0).get(FreeRoomModel.PROPERTY_ID),
								(String) freeRoomResources.get(0).get(
										FreeRoomModel.PROPERTY_NAME),
								(String) freeRoomResources.get(0).get(
										FreeRoomModel.PROPERTY_LINK),
								(String) result
										.get(FreeRoomModel.PROPERTY_START),
								(String) result.get(FreeRoomModel.PROPERTY_END));
						freeRoomContainer.addItem(freeRoom);
						counter++;
					}
				}
			}
		} catch (ConnectException e) {
			LOGGER.info(ERROR_MASSAGE_SERVER_NOT_AVAILABLE);
			LOGGER.error(ERROR_MASSAGE_URL_NOT_READABLE, e);
		} catch (MalformedURLException e) {
			LOGGER.error(ERROR_MASSAGE_URL_NOT_READABLE, e);
		} catch (ParseException e) {
			LOGGER.error(ERROR_MASSAGE_JSON_HANDLING, e);
			LOGGER.error(ERROR_MASSAGE_URL_NOT_READABLE, e);
		} catch (IOException e) {
			LOGGER.error(ERROR_MASSAGE_URL_NOT_READABLE, e);

		} finally {
			return freeRoomContainer;
		}
	}

	private List<JSONObject> getFreeResourcesResources(JSONObject jsonObject) {

		this.jsonObject = jsonObject;

		@SuppressWarnings("unchecked")
		List<JSONObject> resources = (List<JSONObject>) this.jsonObject
				.get("resources");

		return resources;
	}

	@Override
	public List<Attribut> getResourceDetails(String resourceId, Locale locale) {
		String url = RESOURCE_DETAIL_METHOD + "resourceId=" + resourceId;
		if (locale != null) {
			url += "&language=" + locale.getLanguage();
		}

		JSONObject attributMap;
		Attribut attribut;

		List<Attribut> attributList = new ArrayList<Attribut>();

		try {
			br = new BufferedReader(new InputStreamReader(
					new URL(url).openStream()));

			jsonObject = (JSONObject) parser.parse(br);

			attributMap = (JSONObject) ((JSONObject) jsonObject.get("result"))
					.get("attributeMap");

			@SuppressWarnings("unchecked")
			Set<String> attributeMapSet = attributMap.keySet();

			Iterator<String> attributeMapKeys = attributeMapSet.iterator();

			while (attributeMapKeys.hasNext()) {
				attribut = new Attribut();

				String nextKey = attributeMapKeys.next().toString();

				attribut.setLabel((String) ((JSONObject) attributMap
						.get(nextKey)).get(Attribut.PROPERTY_LABEL));
				attribut.setValue((String) ((JSONObject) attributMap
						.get(nextKey)).get(Attribut.PROPERTY_VALUE));

				attributList.add(attribut);
			}

			return attributList;
		} catch (ConnectException e) {
			LOGGER.info(ERROR_MASSAGE_LOADING_RESOURCE_DETAIL + resourceId);
			LOGGER.info(ERROR_MASSAGE_SERVER_NOT_AVAILABLE);
			LOGGER.error(ERROR_MASSAGE_URL_NOT_READABLE, e);
			return null;
		} catch (MalformedURLException e) {
			LOGGER.info(ERROR_MASSAGE_LOADING_RESOURCE_DETAIL + resourceId);
			LOGGER.error(ERROR_MASSAGE_URL_NOT_READABLE, e);
			return null;
		} catch (ParseException e) {
			LOGGER.info(ERROR_MASSAGE_LOADING_RESOURCE_DETAIL + resourceId);
			LOGGER.error(ERROR_MASSAGE_JSON_HANDLING, e);
			LOGGER.error(ERROR_MASSAGE_URL_NOT_READABLE, e);
			return null;
		} catch (IOException e) {
			LOGGER.info(ERROR_MASSAGE_LOADING_RESOURCE_DETAIL + resourceId);
			LOGGER.error(ERROR_MASSAGE_URL_NOT_READABLE, e);
			return null;

		}
	}

	@Override
	public String getDhbwEntryPoint() {
		return this.dhbwEntryPoint;
	}

	public static DataLoader getInstance() {
		if (instance == null) {
			instance = new DataLoader();
		}
		return instance;
	}
}