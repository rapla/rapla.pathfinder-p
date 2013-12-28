package com.pathfinder.presenter;

import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class DataLoaderTest {
	DataLoader dataloader;

	@Before
	public void setUp() {
		dataloader = new DataLoader();
	}

	@Test
	public void roomsNotNullTest() {
		dataloader.loadAllResources();
		assertNotEquals(0, dataloader.getRoomContainer());
	}

	@Test
	public void coursesNotNullTest() {
		dataloader.loadAllResources();
		assertNotEquals(0, dataloader.getCourseContainer());
	}

	@Test
	public void personsNotNullTest() {
		dataloader.loadAllResources();
		assertNotEquals(0, dataloader.getPersonContainer());
	}

	@Test
	public void poisNotNullTest() {
		dataloader.loadAllResources();
		assertNotEquals(0, dataloader.getPoiContainer());
	}

}
