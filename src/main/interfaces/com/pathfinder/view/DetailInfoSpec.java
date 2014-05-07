package com.pathfinder.view;

import com.pathfinder.model.Attribut;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent.ItemClickListener;

/**
 * DetailSpec
 * 
 * @author alexh
 * 
 */
public interface DetailInfoSpec extends ComponentSpec {

	void removeDetails();

	void addDetails(BeanItemContainer<Attribut> resourceDetails);

	void addInfoTableItemClickListener(ItemClickListener listener);
}
