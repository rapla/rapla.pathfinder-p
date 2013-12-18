/**
 * 
 */
package com.pathfinder.util.properties;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author tim
 * 
 */
public class ApplicationPropertiesTest {

	ApplicationPropertiesSpec properties;

	@Before
	public void instantiate() {
		properties = ApplicationProperties.getInstance();
	}

	@Test
	public void getStringPropertyTest() {
		String property = properties.getProperty(PropertiesKey.EXAMPLE_STRING);
		assertEquals("Example Property", property);

		property = properties.getProperty(PropertiesKey.EXAMPLE_NOT_FOUND);
		assertEquals("", property);
	}

	@Test
	public void getStringPropertyWithDefaultValueTest() {
		String property = properties.getProperty(PropertiesKey.EXAMPLE_STRING,
				"Not found");
		assertEquals("Example Property", property);

		property = properties.getProperty(PropertiesKey.EXAMPLE_NOT_FOUND,
				"Not found");
		assertEquals("Not found", property);
	}

	@Test
	public void getIntPropertyTest() {
		int property = properties.getIntProperty(PropertiesKey.EXAMPLE_INT);
		assertEquals(12345, property);

		property = properties
				.getIntProperty(PropertiesKey.EXAMPLE_INT_NOT_FOUND);
		assertEquals(0, property);
	}

	@Test
	public void getIntPropertyWithDefaultValueTest() {
		int property = properties.getIntProperty(PropertiesKey.EXAMPLE_INT, 8);
		assertEquals(12345, property);

		property = properties.getIntProperty(
				PropertiesKey.EXAMPLE_INT_NOT_FOUND, 8);
		assertEquals(8, property);
	}

	@Test(expected = NumberFormatException.class)
	public void getIntPropertyFailTest() {
		properties.getIntProperty(PropertiesKey.EXAMPLE_STRING);
	}
}
