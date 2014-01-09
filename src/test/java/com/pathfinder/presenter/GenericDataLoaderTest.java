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

	DataLoader dataLoader;

	@Before
	public void setUp() {
		dataLoader = new DataLoader("s");
	}

	@Test
	public void getFreeRessources() {
		Assert.assertNotNull(dataLoader.getFreeResources());
	}

	@Test
	public void readSomethingFromFreeResourcesResult() {
		BeanItemContainer<FreeRoomModel> freeResourcesResult = dataLoader
				.getFreeResources();

		Assert.assertNotNull(freeResourcesResult);

	}

	@Test
	public void getModelDetailsAndReadWithIterators() {

		List<Attribut> modelDetails = dataLoader
				.getModelDetails("getResource?resourceId=org.rapla.entities.domain.Allocatable_1320");

		Assert.assertNotNull(modelDetails);

	}
}
