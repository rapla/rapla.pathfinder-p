package com.pathfinder.model;

import static de.vksi.c4j.Condition.ignored;
import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;
import static de.vksi.c4j.Condition.result;
import de.vksi.c4j.Target;

public class AttributeSpecContract implements AttributeSpec {

	@Target
	private AttributeSpec target;

	@Override
	public AttributeKey getKey() {
		if (postCondition()) {
			AttributeKey result = result();
			assert result != null : "result not null";
		}
		return ignored();
	}

	@Override
	public void setKey(String key) {
		if (preCondition()) {
			assert key != null : "key not null";
		}
		if (postCondition()) {
			assert target.getKey().equals(AttributeKey.getKey(key)) : "value set successfully";
		}
	}

	@Override
	public String getLabel() {
		if (postCondition()) {
			String result = result();
			assert result != null : "result not null";
		}
		return ignored();
	}

	@Override
	public void setLabel(String label) {
		if (preCondition()) {
			assert label != null : "label not null";
		}
		if (postCondition()) {
			assert target.getLabel().equals(label) : "value set successfully";
		}
	}

	@Override
	public String getValue() {
		if (postCondition()) {
			String result = result();
			assert result != null : "result not null";
		}
		return ignored();
	}

	@Override
	public void setValue(String value) {
		if (preCondition()) {
			assert value != null : "value not null";
		}
		if (postCondition()) {
			assert target.getValue().equals(value) : "value set successfully";
		}
	}

	@Override
	public String getPerson() {
		if (postCondition()) {
			String result = result();
			assert result != null : "result not null";
		}
		return ignored();
	}

	@Override
	public void setPerson(String person) {
		if (preCondition()) {
			assert person != null : "person not null";
		}
		if (postCondition()) {
			assert target.getPerson().equals(person) : "value set successfully";
		}
	}

	@Override
	public String getInformation() {
		if (postCondition()) {
			String result = result();
			assert result != null : "result not null";
		}
		return ignored();
	}

	@Override
	public void setInformation(String information) {
		if (preCondition()) {
			assert information != null : "information not null";
		}
		if (postCondition()) {
			assert target.getInformation().equals(information) : "value set successfully";
		}
	}

}
