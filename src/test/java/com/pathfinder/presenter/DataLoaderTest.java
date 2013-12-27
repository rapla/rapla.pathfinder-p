package com.pathfinder.presenter;

import static org.junit.Assert.*;

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
		assertNotNull(dataloader.getAllRooms());
	}

	@Test
	public void coursesNotNullTest() {
		dataloader.loadAllResources();
		assertNotNull(dataloader.getAllCourses());
	}

	@Test
	public void personsNotNullTest() {
		dataloader.loadAllResources();
		assertNotNull(dataloader.getAllPersons());
	}

	@Test
	public void poisNotNullTest() {
		dataloader.loadAllResources();
		assertNotNull(dataloader.getAllPois());
	}

}
