package com.pathfinder.view.components;

import java.util.Iterator;
import java.util.List;

import com.pathfinder.model.Attribut;
import com.pathfinder.model.ResourceModel;
import com.pathfinder.presenter.DataLoader;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Defines the detail view for persons, courses, pois or rooms
 * 
 * @author alexh
 * 
 * @param <T>
 */
public class DetailInfo<T> extends CustomComponent implements DetailInfoSpec {
	private List<Attribut> modelDetails = null;
	private VerticalLayout layout = new VerticalLayout();
	private final DataLoader dataLoader = DataLoader.getInstance();
	private Label label = new Label();

	public DetailInfo(ResourceModel resourceModel) {

		buildDetailInfo(resourceModel);

	}

	private void buildDetailInfo(ResourceModel resourceModel) {

		modelDetails = dataLoader.getModelDetails(resourceModel.getLink());
		Iterator<Attribut> modelDetailsIterator = modelDetails.iterator();

		while (modelDetailsIterator.hasNext()) {
			Attribut modelAttribut = modelDetailsIterator.next();

			label = new Label(modelAttribut.getLabel()
					+ modelAttribut.getValue());

			layout.addComponent(label);
		}

		setCompositionRoot(layout);

	}

	@Override
	public void updateTranslations() {
		// TODO Auto-generated method stub
	}
}