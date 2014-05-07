package com.pathfinder.model;

/**
 * Model for attributes
 * 
 * @author alexh
 * 
 */
public class Attribut {

	public static final String PROPERTY_KEY = "key";
	public static final String PROPERTY_LABEL = "label";
	public static final String PROPERTY_VALUE = "value";

	private AttributKey key;
	private String label;
	private String value;

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

	@Override
	public String toString() {
		return "ResourceDetailRowModel [label=" + label + ", value=" + value
				+ "]";
	}
}