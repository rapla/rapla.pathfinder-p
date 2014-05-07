package com.pathfinder.view;

import com.pathfinder.model.Attribute;
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

	void addDetails(BeanItemContainer<Attribute> resourceDetails);

	void addInfoTableItemClickListener(ItemClickListener listener);
}
