package com.pathfinder.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class FreeRoomModelTest {

	private FreeRoomModelSpec freeRoomModel;

	@Before
	public void initialize() {
		this.freeRoomModel = new FreeRoomModel("id", "name", "link", "start",
				"end");
	}

	@Test
	public void setIdTest() {
		assertEquals("id", freeRoomModel.getId());
		freeRoomModel.setId("test");
		assertEquals("test", freeRoomModel.getId());
	};

	@Test
	public void setNameTest() {
		assertEquals("name", freeRoomModel.getName());
		freeRoomModel.setName("test");
		assertEquals("test", freeRoomModel.getName());
	};

	@Test
	public void setLinkTest() {
		assertEquals("link", freeRoomModel.getLink());
		freeRoomModel.setLink("test");
		assertEquals("test", freeRoomModel.getLink());
	};

	@Test
	public void setStartTest() {
		assertEquals("start", freeRoomModel.getStart());
		freeRoomModel.setStart("test");
		assertEquals("test", freeRoomModel.getStart());
	};

	@Test
	public void setEndTest() {
		assertEquals("end", freeRoomModel.getEnd());
		freeRoomModel.setEnd("test");
		assertEquals("test", freeRoomModel.getEnd());
	};
}
