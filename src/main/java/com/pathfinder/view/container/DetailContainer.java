package com.pathfinder.view.container;

import com.pathfinder.model.ResourceModel;
import com.pathfinder.view.components.DetailImage;
import com.pathfinder.view.components.DetailInfo;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

public class DetailContainer extends CustomComponent implements
		DetailContainerSpec {

	private final VerticalLayout layout = new VerticalLayout();
	private DetailInfo detailInfo = null;
	private DetailImage detailImage = null;

	public DetailContainer() {
		this.buildLayout();
		this.setCompositionRoot(layout);
	}

	@Override
	public void addDetails(ResourceModel resourceModel) {
		detailInfo = new DetailInfo<Object>(resourceModel);

		// detailImage = new DetailImage(resourceModel);
	}

	public void buildLayout() {
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
		// TODO Auto-generated method stub
	}
}