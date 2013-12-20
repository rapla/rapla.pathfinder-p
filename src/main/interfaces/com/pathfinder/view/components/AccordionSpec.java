package com.pathfinder.view.components;

import com.pathfinder.model.CourseModel;
import com.pathfinder.model.PersonModel;
import com.pathfinder.model.PoiModel;
import com.pathfinder.model.RoomModel;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent.ItemClickListener;

/**
 * TreeStructureSpec
 * 
 * @author alexh
 * 
 */
public interface AccordionSpec extends ComponentSpec {

	void setRoomContainer(BeanItemContainer<RoomModel> beanItemContainer);

	void setCourseContainer(BeanItemContainer<CourseModel> beanItemContainer);

	void setPersonContainer(BeanItemContainer<PersonModel> beanItemContainer);

	void setPoiContainer(BeanItemContainer<PoiModel> beanItemContainer);

	void addItemClickListenerRoomTable(ItemClickListener listener);

	void addItemClickListenerCourseTable(ItemClickListener listener);

	void addItemClickListenerPersonTable(ItemClickListener listener);

	void addItemClickListenerPoiTable(ItemClickListener listener);
}
