package com.pathfinder.view;

import java.util.List;

import com.pathfinder.model.Attribut;
import com.vaadin.data.util.BeanItemContainer;

/**
 * DetailSpec
 * 
 * @author alexh
 * 
 */
public interface DetailInfoSpec extends ComponentSpec {

	void removeDetails();

	void addDetails(BeanItemContainer<Attribut> resourceDetails);
}
