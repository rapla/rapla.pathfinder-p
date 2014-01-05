package com.pathfinder.presenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.pathfinder.util.properties.ApplicationProperties;
import com.pathfinder.util.properties.PropertiesKey;

import de.vksi.c4j.ContractReference;

/**
 * 
 * GenericDataLoader will get the Data to the runtime
 * 
 * GenericDataLoader lädt die Dateien zur Laufzeit
 * 
 * @author Myracle
 * 
 */

@ContractReference(GenericDataLoaderContract.class)
public class GenericDataLoader implements GenericDataLoaderSpec {

	private static final Logger logger = LogManager.getLogger(DataLoader.class);

	private final String BASE_URL = ApplicationProperties.getInstance()
			.getProperty(PropertiesKey.GSON_BASE_URL);

	private final String FREE_RESOURCES = BASE_URL + "/getFreeResources";

	private final String MASSAGE_ERROR_URL_NOT_READABLE = "Error loading URL: ";

	private BufferedReader br;

	@Override
	public List<JSONObject> getFreeResourcesResult() {

		JSONParser parser = new JSONParser();
		Object obj;
		JSONObject jsonObject;

		try {

			br = new BufferedReader(new InputStreamReader(new URL(
					FREE_RESOURCES).openStream()));

			obj = parser.parse(br);
			jsonObject = (JSONObject) obj;

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

		@SuppressWarnings("unchecked")
		List<JSONObject> resources = (List<JSONObject>) jsonObject
				.get("resources");

		return resources;

	}

}
