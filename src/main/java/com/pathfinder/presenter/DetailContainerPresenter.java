package com.pathfinder.presenter;

import com.pathfinder.view.container.DetailContainer;

public class DetailContainerPresenter implements DetailContainerPresenterSpec {
	private DetailContainer detailContainer = new DetailContainer(null, null,
			null);

	@Override
	public DetailContainer getDetailContainer() {
		return this.detailContainer;
	}
}
