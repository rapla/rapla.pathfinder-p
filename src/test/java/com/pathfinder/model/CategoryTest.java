package com.pathfinder.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class CategoryTest {

	private CategorySpec category;

	@Before
	public void initialize() {
		this.category = new Category();
	}

	@Test
	public void setNameTest() {
		assertNull(category.getName());
		category.setName("test");
		assertEquals("test", category.getName());
	}

	@Test
	public void setIdTest() {
		assertNull(category.getId());
		category.setId("test");
		assertEquals("test", category.getId());
	}

}
