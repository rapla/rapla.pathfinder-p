package com.pathfinder.view.components;

import com.pathfinder.model.CourseModel;
import com.pathfinder.model.PersonModel;
import com.pathfinder.model.PoiModel;
import com.pathfinder.model.RoomModel;
import com.pathfinder.view.listener.TreeStructureViewListenerSpec;
import com.vaadin.data.util.BeanItemContainer;

/**
 * TreeStructureSpec
 * 
 * @author alexh
 * 
 */
public interface AccordionSpec extends ComponentSpec {
	void addTreeStructureSpecListener(TreeStructureViewListenerSpec listener);

	void setRoomContainer(BeanItemContainer<RoomModel> beanItemContainer);

	void setCourseContainer(BeanItemContainer<CourseModel> beanItemContainer);

	void setPersonContainer(BeanItemContainer<PersonModel> beanItemContainer);

	void setPoiContainer(BeanItemContainer<PoiModel> beanItemContainer);
}
