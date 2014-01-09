package com.pathfinder.view.components;

import java.util.List;

import com.pathfinder.model.Attribut;
import com.pathfinder.model.ResourceModel;
import com.pathfinder.presenter.GenericDataLoader;
import com.vaadin.ui.CustomComponent;

/**
 * Defines the detail view for persons, courses, pois or rooms
 * 
 * @author alexh
 * 
 * @param <T>
 */
public class DetailInfo<T> extends CustomComponent implements DetailInfoSpec {
	private List<Attribut> modelDetails = null;

	private static GenericDataLoader genericDataLoader = new GenericDataLoader();

	public DetailInfo(ResourceModel resourceModel) {

		buildDetailInfo(resourceModel);

	}

	private void buildDetailInfo(ResourceModel resourceModel) {

		modelDetails = genericDataLoader.getModelDetails(resourceModel
				.getLink());

		// Generic Build of the DetailInfo

	}

	@Override
	public void updateTranslations() {
		// TODO Auto-generated method stub
	}
}