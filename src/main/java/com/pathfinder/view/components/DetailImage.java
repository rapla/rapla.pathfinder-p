package com.pathfinder.view.components;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Image;

/**
 * Image to show in the detail view
 * 
 * @author alexh
 * 
 */
public class DetailImage extends Image implements DetailImageSpec {

	private final String IMAGEPATH = "img/";

	public DetailImage(String caption, String imageSource) {
		init(caption, imageSource);
	}

	private void init(String caption, String imageSource) {
		this.setCaption(caption);
		this.setSource(new ThemeResource(IMAGEPATH + imageSource));
		this.setSizeFull();
	}

	@Override
	public void updateTranslations() {
		// TODO Auto-generated method stub
	}
}