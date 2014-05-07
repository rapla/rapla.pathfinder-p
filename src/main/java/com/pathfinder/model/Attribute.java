package com.pathfinder.model;

/**
 * Model for attributes
 * 
 * @author alexh
 * 
 */
public class Attribute {

	public static final String PROPERTY_KEY = "key";
	public static final String PROPERTY_LABEL = "label";
	public static final String PROPERTY_VALUE = "value";

	private AttributKey key;
	private String label;
	private String value;

	private String person;
	private String information;

	public AttributKey getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = AttributKey.getKey(key);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	@Override
	public String toString() {
		return "ResourceDetailRowModel [label=" + label + ", value=" + value
				+ "]";
	}
}