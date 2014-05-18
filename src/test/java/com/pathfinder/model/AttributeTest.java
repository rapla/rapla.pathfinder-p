package com.pathfinder.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class AttributeTest {

	private AttributeSpec attribute;

	@Before
	public void initialize() {
		this.attribute = new Attribute();
	}

	@Test
	public void setKeyTest() {
		assertNull(attribute.getKey());
		attribute.setKey("name");
		assertEquals(AttributeKey.NAME_KEY, attribute.getKey());
		attribute.setKey("this is an invalid key");
		assertEquals(AttributeKey.UNKNOWN, attribute.getKey());
	}

	@Test
	public void setLabelTest() {
		assertNull(attribute.getLabel());
		attribute.setLabel("new label");
		assertEquals("new label", attribute.getLabel());
	}

	@Test
	public void setValueTest() {
		assertNull(attribute.getValue());
		attribute.setValue("new Value");
		assertEquals("new Value", attribute.getValue());
	}

	@Test
	public void setPersonTest() {
		assertNull(attribute.getPerson());
		attribute.setPerson("new Person");
		assertEquals("new Person", attribute.getPerson());
	}

	@Test
	public void setInformationTest() {
		assertNull(attribute.getInformation());
		attribute.setInformation("new Information");
		assertEquals("new Information", attribute.getInformation());
	}
}
