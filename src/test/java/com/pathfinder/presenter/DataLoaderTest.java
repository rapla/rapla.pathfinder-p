package com.pathfinder.presenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DataLoaderTest {
	DataLoader dataloader;

	@Before
	public void setUp() {
		dataloader = DataLoader.getInstance();
		// dataloader.loadAllResources();
	}

	@Test
	public void roomsLoadedTest() {
		Assert.assertNotNull(dataloader.getRoomContainer());
	}

	@Test
	public void coursesLoadedTest() {
		Assert.assertNotNull(dataloader.getCourseContainer());
	}

	@Test
	public void personsLoadedTest() {
		Assert.assertNotNull(dataloader.getPersonContainer());
	}

	@Test
	public void poisLoadedTest() {
		Assert.assertNotNull(dataloader.getPoiContainer());
	}

}
