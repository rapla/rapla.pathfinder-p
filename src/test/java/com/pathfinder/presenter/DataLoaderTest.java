package com.pathfinder.presenter;

import java.util.Date;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pathfinder.model.Attribut;
import com.pathfinder.model.EventModel;
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

			@Override
			public boolean isTimeToGetRemoved() {
				return true;
			}

			@Override
			public void destroy() {
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

		BeanItemContainer<Attribut> modelDetails = dataLoader
				.getResourceDetails(
						"org.rapla.entities.domain.Allocatable_1320",
						new Locale("DE_de"));

		Assert.assertNotNull(modelDetails);
	}

	@Test
	public void getEventTest() {
		BeanItemContainer<EventModel> eventContainer = dataLoader.getEvent(
				"org.rapla.entities.domain.Allocatable_2431", new Date(),
				new Date(), Locale.GERMAN);
		Assert.assertNotNull(eventContainer);
	}
}