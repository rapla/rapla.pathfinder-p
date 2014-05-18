package com.pathfinder.model;

/**
 * Model for free rooms
 * 
 * @author alexh
 * 
 */
public class FreeRoomModel implements FreeRoomModelSpec {
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

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getLink() {
		return link;
	}

	@Override
	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String getStart() {
		return start;
	}

	@Override
	public void setStart(String start) {
		this.start = start;
	}

	@Override
	public String getEnd() {
		return end;
	}

	@Override
	public void setEnd(String end) {
		this.end = end;
	}
}