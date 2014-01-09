package com.pathfinder.model;

/**
 * Model for free rooms
 * 
 * @author alexh
 * 
 */
public class FreeRoomModel {
	public static final String PROPERTY_ID = "id";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_LINK = "link";
	public static final String PROPERTY_START = "start";
	public static final String PROPERTY_END = "end";

	private String id = "";
	private String name = "";
	private String link = "";
	private String start = "";
	private String end = "";

	public FreeRoomModel(String id, String name, String link, String start,
			String end) {
		this.setId(id);
		this.setName(name);
		this.setLink(link);
		this.setStart(start);
		this.setEnd(end);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
}