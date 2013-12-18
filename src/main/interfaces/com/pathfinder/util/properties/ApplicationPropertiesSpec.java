/**
 * 
 */
package com.pathfinder.util.properties;

import de.vksi.c4j.ContractReference;
import de.vksi.c4j.Pure;

/**
 * @author tim
 * 
 */
@ContractReference(ApplicationPropertiesSpecContract.class)
public interface ApplicationPropertiesSpec {

	/**
	 * Returns the property with the specified key
	 * 
	 * @param key
	 *            Property-Key
	 * @return Value of the specified property-key or empty string ("") if
	 *         property not found
	 */
	@Pure
	String getProperty(PropertiesKey key);

	/**
	 * Returns the property with the specified key
	 * 
	 * @param key
	 *            Property-Key
	 * @param defaultValue
	 *            Default value which will be returned, if Property not found
	 * @return Value of the specified property-key or default-value if property
	 *         not found
	 */
	@Pure
	String getProperty(PropertiesKey key, String defaultValue);

	/**
	 * Returns the integer property with the specified key
	 * 
	 * @param key
	 *            Property-Key
	 * @return Integer value of the specified property-key or 0 if property not
	 *         found
	 */
	@Pure
	int getIntProperty(PropertiesKey key);

	/**
	 * Returns the integer property with the specified key
	 * 
	 * @param key
	 *            Property-Key
	 * @param defaultValue
	 *            Default return value, used if key not found
	 * @return Integer value of the specified property-key or default-value if
	 *         property not found
	 */
	@Pure
	int getIntProperty(PropertiesKey key, int defaultValue);

}