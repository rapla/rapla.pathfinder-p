package com.pathfinder.model;

public class PoiModel extends ResourceModel {
	public static final String PROPERTY_PICTURE = "picture";

	private String picture;

	public PoiModel(String name, String link, String id, String[] searchTerms,
			String roomNr, String picture) {
		super(id, name, link, searchTerms, roomNr);
		this.setPicture(picture);
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
}