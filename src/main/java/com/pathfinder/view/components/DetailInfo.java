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
 */
public class DetailInfo extends CustomComponent implements DetailInfoSpec {
	private List<Attribut> modelDetails = null;
	private final VerticalLayout layout = new VerticalLayout();
	private final DataLoader dataLoader = DataLoader.getInstance();
	private Label label = new Label();

	public DetailInfo() {
		setCompositionRoot(layout);
	}

	@Override
	public void addDetails(ResourceModel resource) {

		modelDetails = dataLoader.getModelDetails(resource.getLink());
		Iterator<Attribut> modelDetailsIterator = modelDetails.iterator();

		while (modelDetailsIterator.hasNext()) {
			Attribut modelAttribut = modelDetailsIterator.next();

			label = new Label(modelAttribut.getLabel()
					+ modelAttribut.getValue());

			layout.addComponent(label);
		}
	}

	@Override
	public void removeDetails() {
		// TODO Auto-generated method stub
	}

	@Override
	public void updateTranslations() {
		// TODO Auto-generated method stub
	}
}