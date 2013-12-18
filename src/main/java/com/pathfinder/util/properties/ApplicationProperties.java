/**
 * 
 */
package com.pathfinder.util.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class to get properties from a property-file
 * 
 * @author tim
 * 
 */
public class ApplicationProperties implements ApplicationPropertiesSpec {

	/**
	 * Logging instance of this class
	 */
	private final static Logger LOG = LogManager
			.getLogger(ApplicationProperties.class);

	/**
	 * Contains all properties
	 */
	private Properties properties = new Properties();

	/**
	 * Single instance of this class
	 */
	private static ApplicationPropertiesSpec instance;

	/**
	 * Filename of the file which contains all properties in form of
	 * key-value-pairs
	 */
	private final static String PROPERTIES_FILENAME = "application.properties";

	/**
	 * Creates the single instance of this class
	 * 
	 * @throws RuntimeException
	 *             thrown if property file couldn't be found or loaded
	 */
	private ApplicationProperties() {
		String errormessage = "Couldn't load property-file with filename \""
				+ PROPERTIES_FILENAME + "\"!";
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(PROPERTIES_FILENAME));
		} catch (FileNotFoundException fnfe) {
			LOG.error(errormessage, fnfe);
			throw new RuntimeException(errormessage, fnfe);
		} catch (IOException ioe) {
			LOG.error(errormessage, ioe);
			throw new RuntimeException(errormessage, ioe);
		}
	}

	/**
	 * Returns the single instance of this class; creates a new one, if it
	 * doesn't exist
	 * 
	 * @return single instance
	 */
	public static ApplicationPropertiesSpec getInstance() {
		if (instance == null) {
			instance = new ApplicationProperties();
		}
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pathfinder.util.properties.ApplicationPropertiesSpec#getProperty(
	 * com.pathfinder.util.properties.PropertiesKey)
	 */
	@Override
	public String getProperty(PropertiesKey key) {
		return getProperty(key, "");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pathfinder.util.properties.ApplicationPropertiesSpec#getProperty(
	 * com.pathfinder.util.properties.PropertiesKey, java.lang.String)
	 */
	@Override
	public String getProperty(PropertiesKey key, String defaultValue) {
		return properties.getProperty(key.toString(), defaultValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pathfinder.util.properties.ApplicationPropertiesSpec#getIntProperty
	 * (com.pathfinder.util.properties.PropertiesKey)
	 */
	@Override
	public int getIntProperty(PropertiesKey key) {
		return getIntProperty(key, 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pathfinder.util.properties.ApplicationPropertiesSpec#getIntProperty
	 * (com.pathfinder.util.properties.PropertiesKey, int)
	 */
	@Override
	public int getIntProperty(PropertiesKey key, int defaultValue) {
		String propertyString = getProperty(key);
		int property = defaultValue;
		if (!propertyString.equals(""))
			try {
				property = Integer.parseInt(propertyString);
			} catch (NumberFormatException nfe) {
				String errormsg = "The Property "
						+ key
						+ " cannot be parsed to an integer value! Please check property-file: "
						+ PROPERTIES_FILENAME;
				LOG.error(errormsg, nfe);
				throw new NumberFormatException(errormsg);
			}
		return property;
	}
}
