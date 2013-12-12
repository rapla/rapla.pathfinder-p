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
	public void RoomsNotNull() {
		dataloader.loadAllResources();
		assertNotNull(dataloader.getAllRooms());
	}

	@Test
	public void CoursesNotNull() {
		dataloader.loadAllResources();
		assertNotNull(dataloader.getAllCourses());
	}

	@Test
	public void PersonsNotNull() {
		dataloader.loadAllResources();
		assertNotNull(dataloader.getAllPersons());
	}

}
