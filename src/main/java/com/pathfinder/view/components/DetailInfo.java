package com.pathfinder.view.components;

import java.util.Iterator;
import java.util.List;

import com.pathfinder.model.Attribut;
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
	private final VerticalLayout layout = new VerticalLayout();
	private Label label = new Label();

	public DetailInfo() {
		setCompositionRoot(layout);
	}

	@Override
	public void addDetails(List<Attribut> resourceDetails) {
		// TODO Should we use a table instead labels?
		Iterator<Attribut> modelDetailsIterator = resourceDetails.iterator();

		while (modelDetailsIterator.hasNext()) {
			Attribut modelAttribut = modelDetailsIterator.next();

			if (!"resourceurl".equals(modelAttribut.getKey())) {
				label = new Label(modelAttribut.getLabel()
						+ modelAttribut.getValue());
				layout.addComponent(label);
			}
		}
	}

	@Override
	public void removeDetails() {
		layout.removeAllComponents();
	}

	@Override
	public void updateTranslations() {
		// Will be blank
	}
}