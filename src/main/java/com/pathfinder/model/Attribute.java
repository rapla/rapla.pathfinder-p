package com.pathfinder.model;

/**
 * Model for attributes
 * 
 * @author alexh
 * 
 */
public class Attribute implements AttributeSpec {

	public static final String PROPERTY_KEY = "key";
	public static final String PROPERTY_LABEL = "label";
	public static final String PROPERTY_VALUE = "value";

	private AttributeKey key;
	private String label;
	private String value;

	private String person;
	private String information;

	@Override
	public AttributeKey getKey() {
		return key;
	}

	@Override
	public void setKey(String key) {
		this.key = AttributeKey.getKey(key);
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getPerson() {
		return person;
	}

	@Override
	public void setPerson(String person) {
		this.person = person;
	}

	@Override
	public String getInformation() {
		return information;
	}

	@Override
	public void setInformation(String information) {
		this.information = information;
	}

	@Override
	public String toString() {
		return "ResourceDetailRowModel [label=" + label + ", value=" + value
				+ "]";
	}
}