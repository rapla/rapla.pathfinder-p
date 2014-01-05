package com.pathfinder.presenter;

import java.util.List;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GenericDataLoaderTest {

	GenericDataLoader genericDataLoader;

	@Before
	public void setUp() {
		genericDataLoader = new GenericDataLoader();
	}

	@Test
	public void getFreeRessources() {
		Assert.assertNotNull(genericDataLoader.getFreeResourcesResult());
	}

	@Test
	public void readSomethingFromFreeResourcesResult() {
		List<JSONObject> freeResourcesResult = genericDataLoader
				.getFreeResourcesResult();

		String free = (String) freeResourcesResult.get(0).get("name");

		Assert.assertTrue(free.equals("free"));

	}

	@Test
	public void readSomethingFromFreeResourcesResources() {
		List<JSONObject> freeResourcesResult = genericDataLoader
				.getFreeResourcesResult();

		List<JSONObject> freeResourcesResources = genericDataLoader
				.getFreeResourcesResources(freeResourcesResult.get(0));

		String name = (String) freeResourcesResources.get(0).get("name");

		Assert.assertTrue(name.equals("XPZ1"));
	}
}
