package com.pathfinder.presenter;

import java.util.List;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pathfinder.model.Attribut;
import com.pathfinder.model.FreeRoomModel;
import com.vaadin.data.util.BeanItemContainer;

public class GenericDataLoaderTest {

	GenericDataLoader genericDataLoader;

	@Before
	public void setUp() {
		genericDataLoader = new GenericDataLoader();
	}

	@Test
	public void getFreeRessources() {
		Assert.assertNotNull(genericDataLoader.getFreeResources());
	}

	@Test
	public void readSomethingFromFreeResourcesResult() {
		BeanItemContainer<FreeRoomModel> freeResourcesResult = genericDataLoader
				.getFreeResources();

		Assert.assertNotNull(freeResourcesResult);

	}

	@Test
	public void getModelDetailsAndReadWithIterators() {

		List<Attribut> modelDetails = genericDataLoader
				.getModelDetails("getResource?resourceId=org.rapla.entities.domain.Allocatable_1320");

		Assert.assertNotNull(modelDetails);

	}
}
