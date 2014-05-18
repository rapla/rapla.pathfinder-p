package com.pathfinder.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Model for the category
 * 
 * @author alexh
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Category implements CategorySpec {
	public static final String PROPERTY_ID = "id";
	public static final String PROPERTY_NAME = "name";

	private String name;
	private String id;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Category [name=" + name + ", id=" + id + "]";
	}
}