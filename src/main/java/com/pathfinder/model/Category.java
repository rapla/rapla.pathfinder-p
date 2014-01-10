package com.pathfinder.model;

/**
 * Model for the category
 * 
 * @author alexh
 * 
 */
public class Category {
	public static final String PROPERTY_ID = "id";
	public static final String PROPERTY_NAME = "name";

	private String name;
	private String id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Category [name=" + name + ", id=" + id + "]";
	}
}