package com.pathfinder.view.container;

import com.pathfinder.model.ResourceModel;
import com.pathfinder.view.components.DetailImage;
import com.pathfinder.view.components.DetailInfo;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

public class DetailContainer extends CustomComponent implements
		DetailContainerSpec {

	private final VerticalLayout vertical = new VerticalLayout();
	private DetailInfo detailInfo = null;
	private DetailImage detailImage = null;

	public DetailContainer(ResourceModel ressourceModel, String imageSource) {
		this.detailInfo = new DetailInfo(ressourceModel);
		// this.detailImage = new DetailImage(caption, imageSource);
		// this.buildLayout();
	}

	public void buildLayout() {
		this.detailInfo.setSizeFull();
		this.detailImage.setSizeFull();
		this.setSizeFull();
		this.vertical.addComponent(detailInfo);
		this.vertical.addComponent(detailImage);
		this.setCompositionRoot(vertical);
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
	public void addDetails(Class<ResourceModel> clazz) {
		// TODO Auto-generated method stub
	}

	@Override
	public void updateTranslations() {
		// TODO Auto-generated method stub
	}
}