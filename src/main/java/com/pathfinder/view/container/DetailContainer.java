package com.pathfinder.view.container;

import com.pathfinder.model.ResourceModel;
import com.pathfinder.view.components.DetailImage;
import com.pathfinder.view.components.DetailImageSpec;
import com.pathfinder.view.components.DetailInfo;
import com.pathfinder.view.components.DetailInfoSpec;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

public class DetailContainer extends CustomComponent implements
		DetailContainerSpec {

	private final VerticalLayout layout = new VerticalLayout();
	private final DetailInfoSpec detailInfo = new DetailInfo();
	private final DetailImageSpec detailImage = new DetailImage();
	private ResourceModel resource = null;

	public DetailContainer() {
		this.buildLayout();
		this.setCompositionRoot(layout);
	}

	private void buildLayout() {
		layout.addComponent(detailInfo);
		layout.addComponent(detailImage);
	}

	@Override
	public void addDetails(ResourceModel resource) {
		this.resource = resource;
		detailInfo.addDetails(this.resource);
		if ("room".equals(resource.getType())) {
			detailImage.setImage(resource);
		}
	}

	@Override
	public void removeDetails() {
		detailInfo.removeDetails();
		detailImage.removeImage();
		this.resource = null;
	}

	@Override
	public void hideDetailContainer() {
		this.setVisible(false);
	}

	@Override
	public void showDetailContainer() {
		this.setVisible(true);
	}

	@Override
	public void updateTranslations() {
		detailInfo.removeDetails();
		detailInfo.addDetails(this.resource);
	}
}