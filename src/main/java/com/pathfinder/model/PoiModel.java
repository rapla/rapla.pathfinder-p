package com.pathfinder.model;

@Deprecated
public class PoiModel extends ResourceModel {
	public static final String PROPERTY_PICTURE = "picture";

	private String picture;

	@Deprecated
	public PoiModel(String name, String link, String id, String[] searchTerms, String picture) {
		super(id, name, link, searchTerms);
		this.setPicture(picture);
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
}