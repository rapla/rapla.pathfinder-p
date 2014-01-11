package com.pathfinder.view.components;

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

	void addItemClickListenerRoomTable(ItemClickListener listener);

	void addItemClickListenerCourseTable(ItemClickListener listener);

	void addItemClickListenerPersonTable(ItemClickListener listener);

	void addItemClickListenerPoiTable(ItemClickListener listener);

	void setRoomContainer(BeanItemContainer<ResourceModel> beanItemContainer);

	void setCourseContainer(BeanItemContainer<ResourceModel> beanItemContainer);

	void setPersonContainer(BeanItemContainer<ResourceModel> beanItemContainer);

	void setPoiContainer(BeanItemContainer<ResourceModel> beanItemContainer);

	void useFiltersForAllTables(String searchString);
}
