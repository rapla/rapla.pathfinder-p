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
	private final String IMAGE_PATH = "img/";
	private final String IMAGE_ENDING = ".png";

	public DetailImage() {
		buildLayout();
	}

	private void buildLayout() {
		this.setAlternateText("Wegbeschreibung ist leider nicht verfügbar");
	}

	@Override
	public void setImage(String imageDescription) {
		this.setSource(new ThemeResource(IMAGE_PATH + imageDescription
				+ IMAGE_ENDING));
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