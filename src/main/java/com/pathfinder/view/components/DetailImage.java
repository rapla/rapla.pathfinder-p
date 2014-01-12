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

	public DetailImage() {
		buildLayout();
	}

	private void buildLayout() {
		this.setAlternateText("");
	}

	@Override
	public void setImage(String imageDescription) {
		// TODO Check if image is available
		this.setSource(new ThemeResource(IMAGEPATH + imageDescription + ".png"));
		this.setSizeFull();
	}

	@Override
	public void removeImage() {
		this.setSource(null);
	}

	@Override
	public void updateTranslations() {
		// Will be blank
	}
}