/**
 * 
 */
package com.pathfinder.util.properties;

import static de.vksi.c4j.Condition.ignored;
import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;
import de.vksi.c4j.ClassInvariant;
import de.vksi.c4j.Condition;
import de.vksi.c4j.Target;

/**
 * @author tim
 * 
 */
public class ApplicationPropertiesSpecContract implements
		ApplicationPropertiesSpec {

	@Target
	private ApplicationPropertiesSpec target;

	@ClassInvariant
	public void classInvariant() {
		assert Condition.unchanged(target) : "state didn't change";
	}

	@Override
	public String getProperty(PropertiesKey key) {
		if (preCondition()) {
			assert key != null : "key not null";
		}
		if (postCondition()) {
			String result = Condition.result();
			assert result != null : "result not null";
		}
		return ignored();
	}

	@Override
	public String getProperty(PropertiesKey key, String defaultValue) {
		if (preCondition()) {
			assert defaultValue != null : "default value not null";
			assert key != null : "key not null";
		}
		if (postCondition()) {
			String result = Condition.result();
			assert result != null : "result not null";
		}
		return ignored();
	}

	@Override
	public int getIntProperty(PropertiesKey key) {
		if (preCondition()) {
			assert key != null : "key not null";
		}
		return ignored();
	}

	@Override
	public int getIntProperty(PropertiesKey key, int defaultValue) {
		if (preCondition()) {
			assert key != null : "key not null";
		}
		return ignored();
	}
}