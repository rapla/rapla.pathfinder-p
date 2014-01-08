package com.pathfinder.presenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.pathfinder.util.properties.ApplicationProperties;
import com.pathfinder.util.properties.PropertiesKey;

/**
 * 
 * GenericDataLoader will get the Data to the runtime
 * 
 * @author Myracle
 * 
 */

public class GenericDataLoader implements GenericDataLoaderSpec {

	private static final Logger logger = LogManager.getLogger(DataLoader.class);

	private final String BASE_URL = ApplicationProperties.getInstance()
			.getProperty(PropertiesKey.RAPLA_BASE_URL);

	private final String FREE_RESOURCES = BASE_URL + "/getFreeResources";
	private final String MASSAGE_ERROR_URL_NOT_READABLE = "Error loading URL: ";

	private final JSONParser parser = new JSONParser();
	private JSONObject jsonObject = null;
	private JSONArray jsonArray = null;

	private BufferedReader br;

	@Override
	public List<JSONObject> getFreeResourcesResult() {

		try {
			br = new BufferedReader(new InputStreamReader(new URL(
					FREE_RESOURCES).openStream()));

			jsonObject = (JSONObject) parser.parse(br);

			@SuppressWarnings("unchecked")
			List<JSONObject> result = (List<JSONObject>) jsonObject
					.get("result");

			return result;

		} catch (MalformedURLException e) {
			logger.error(MASSAGE_ERROR_URL_NOT_READABLE, e);
			return null;
		} catch (IOException e) {
			logger.error(MASSAGE_ERROR_URL_NOT_READABLE, e);
			return null;
		} catch (ParseException e) {
			logger.error(MASSAGE_ERROR_URL_NOT_READABLE, e);
			return null;
		}

	}

	@Override
	public List<JSONObject> getFreeResourcesResources(JSONObject jsonObject) {

		this.jsonObject = jsonObject;

		@SuppressWarnings("unchecked")
		List<JSONObject> resources = (List<JSONObject>) this.jsonObject
				.get("resources");

		return resources;
	}

	@Override
	public JSONArray getModelDetails(String modelLink) {

		try {
			br = new BufferedReader(new InputStreamReader(new URL(BASE_URL
					+ "/" + modelLink).openStream()));

			jsonObject = (JSONObject) parser.parse(br);

			JSONArray attributMap = (JSONArray) ((JSONObject) jsonObject
					.get("result")).get("attributeMap");

			return attributMap;

		} catch (MalformedURLException e) {
			logger.error(MASSAGE_ERROR_URL_NOT_READABLE, e);
			return null;
		} catch (IOException e) {
			logger.error(MASSAGE_ERROR_URL_NOT_READABLE, e);
			return null;
		} catch (ParseException e) {
			logger.error(MASSAGE_ERROR_URL_NOT_READABLE, e);
			return null;
		}

	}
}