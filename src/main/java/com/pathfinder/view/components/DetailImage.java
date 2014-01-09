package com.pathfinder.view.components;

import com.pathfinder.model.ResourceModel;
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

	public DetailImage(ResourceModel resourceModel) {
		init(resourceModel);
	}

	private void init(ResourceModel resourceModel) {
		this.setCaption(resourceModel.getName());
		this.setSource(new ThemeResource(IMAGEPATH + resourceModel));
		this.setSizeFull();
	}

	@Override
	public void updateTranslations() {
		// TODO Auto-generated method stub
	}
}