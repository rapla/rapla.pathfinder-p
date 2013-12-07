package com.pathfinder.presenter;

import com.pathfinder.view.components.DetailContainer;

public class DetailContainerPresenter implements DetailContainerPresenterSpec {
	private final DetailContainer detailContainer = new DetailContainer();

	public DetailContainer getDetailContainer() {
		return this.detailContainer;
	}
}
