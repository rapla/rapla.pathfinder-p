package com.pathfinder.presenter;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pathfinder.model.Attribut;
import com.pathfinder.model.FreeRoomModel;
import com.vaadin.data.util.BeanItemContainer;

public class DataLoaderTest {
	DataLoader dataLoader;

	@Before
	public void setUp() {
		dataLoader = DataLoader.getInstance();
	}

	@Test
	public void roomsLoadedTest() {
		Assert.assertNotNull(dataLoader.getRoomContainer());
	}

	@Test
	public void coursesLoadedTest() {
		Assert.assertNotNull(dataLoader.getCourseContainer());
	}

	@Test
	public void personsLoadedTest() {
		Assert.assertNotNull(dataLoader.getPersonContainer());
	}

	@Test
	public void poisLoadedTest() {
		Assert.assertNotNull(dataLoader.getPoiContainer());
	}

	private boolean dataUpdated = false;

	@Test
	public void addDataListenerTest() {
		dataLoader.addDataListener(new DataLoaderListenerSpec() {

			@Override
			public void dataUpdated() {
				dataUpdated = true;
			}
		});

		dataLoader.reloadAllData();

		Assert.assertTrue(dataUpdated);

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