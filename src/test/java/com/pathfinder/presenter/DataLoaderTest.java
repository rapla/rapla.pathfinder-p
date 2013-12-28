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
		assertNotEquals(0, dataloader.getAllRooms());
	}

	@Test
	public void coursesNotNullTest() {
		dataloader.loadAllResources();
		assertNotEquals(0, dataloader.getAllCourses());
	}

	@Test
	public void personsNotNullTest() {
		dataloader.loadAllResources();
		assertNotEquals(0, dataloader.getAllPersons().size());
	}

	@Test
	public void poisNotNullTest() {
		dataloader.loadAllResources();
		assertNotEquals(0, dataloader.getAllPois());
	}

}
