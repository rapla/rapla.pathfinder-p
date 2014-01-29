package com.pathfinder.view;

import com.pathfinder.model.ResourceModel;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent.ItemClickListener;

/**
 * TreeStructureSpec
 * 
 * @author alexh
 * 
 */
public interface AccordionViewSpec extends ComponentSpec {

	void addAccordionTableItemClickListener(ItemClickListener listener);

	void useFiltersForAllTables(String searchString);

	void hideAccordionView();

	void showAccordionView();

	void setRoomContainer(BeanItemContainer<ResourceModel> beanItemContainer);

	void setCourseContainer(BeanItemContainer<ResourceModel> beanItemContainer);

	void setPersonContainer(BeanItemContainer<ResourceModel> beanItemContainer);

	void setPoiContainer(BeanItemContainer<ResourceModel> beanItemContainer);
}
