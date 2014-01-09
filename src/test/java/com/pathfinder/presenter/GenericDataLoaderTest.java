package com.pathfinder.presenter;

import java.util.List;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pathfinder.model.Attribut;

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

	@Test
	public void getModelDetailsAndReadWithIterators() {

		List<Attribut> modelDetails = genericDataLoader
				.getModelDetails("getResource?resourceId=org.rapla.entities.domain.Allocatable_1320");

		for (Attribut a : modelDetails) {
			System.out.println(a.toString());
		}
	}
}
