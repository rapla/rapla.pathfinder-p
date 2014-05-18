package com.pathfinder.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class EventModelTest {

	private EventModelSpec eventModel;

	@Before
	public void initialize() {
		this.eventModel = new EventModel();
	}

	@Test
	public void setNameTest() {
		assertNull(eventModel.getName());
		eventModel.setName("test");
		assertEquals("test", eventModel.getName());
	}

	@Test
	public void setStartTest() {
		assertNull(eventModel.getStart());
		eventModel.setStart("test");
		assertEquals("test", eventModel.getStart());
	}

	@Test
	public void setStartDateTest() {
		assertNull(eventModel.getStartDate());
		eventModel.setStartDate("test");
		assertEquals("test", eventModel.getStartDate());
	}

	@Test
	public void setEndTest() {
		assertNull(eventModel.getEnd());
		eventModel.setEnd("test");
		assertEquals("test", eventModel.getEnd());
	}

	@Test
	public void setEndDateTest() {
		assertNull(eventModel.getEndDate());
		eventModel.setEndDate("test");
		assertEquals("test", eventModel.getEndDate());
	}

	@Test
	public void setResourcesTest() {
		assertNull(eventModel.getResources());
		List<ResourceModel> resources = new ArrayList<>();
		eventModel.setResources(resources);
		assertEquals(resources, eventModel.getResources());
	}
}
