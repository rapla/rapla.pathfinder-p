package com.pathfinder.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.pathfinder.model.ResourceModel;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;

public class AccordionViewTest {

	private AccordionViewSpec accordionView;

	@Before
	public void setUp() {
		accordionView = new AccordionView();
	}

	@Test
	public void addAccordionTableItemClickListenerTest() {
		Table poiTable = accordionView.getPoiTable();
		Table personTable = accordionView.getPersonTable();
		Table roomTable = accordionView.getRoomTable();
		Table courseTable = accordionView.getCourseTable();

		ItemClickListener listener = new ItemClickListener() {

			@Override
			public void itemClick(ItemClickEvent event) {
			}
		};

		accordionView.addAccordionTableItemClickListener(listener);

		assertEquals(listener, poiTable.getListeners(ItemClickEvent.class)
				.iterator().next());
		assertEquals(listener, personTable.getListeners(ItemClickEvent.class)
				.iterator().next());
		assertEquals(listener, roomTable.getListeners(ItemClickEvent.class)
				.iterator().next());
		assertEquals(listener, courseTable.getListeners(ItemClickEvent.class)
				.iterator().next());
	}

	@Test
	public void useFiltersForAllTablesTest() {
		Table poiTable = accordionView.getPoiTable();
		Table personTable = accordionView.getPersonTable();
		Table roomTable = accordionView.getRoomTable();
		Table courseTable = accordionView.getCourseTable();

		String myFilterString = "stringy";
		accordionView.useFiltersForAllTables(myFilterString);

		assertFilterSet(myFilterString, poiTable);
		assertFilterSet(myFilterString, personTable);
		assertFilterSet(myFilterString, roomTable);
		assertFilterSet(myFilterString, courseTable);

	}

	@SuppressWarnings("unchecked")
	private void assertFilterSet(String compareString, Table table) {
		BeanItemContainer<ResourceModel> container = (BeanItemContainer<ResourceModel>) table
				.getContainerDataSource();
		Or orFilter = (Or) container.getContainerFilters().iterator().next();
		container = (BeanItemContainer<ResourceModel>) table
				.getContainerDataSource();
		SimpleStringFilter filter = (SimpleStringFilter) orFilter.getFilters()
				.iterator().next();
		assertEquals(compareString, filter.getFilterString());
	}

	@Test
	public void hideAccordionViewTest() {
		Component accordion = (Component) accordionView;
		assertTrue(accordion.isVisible());
		accordionView.hideAccordionView();
		assertFalse(accordion.isVisible());
	}

	@Test
	public void showAccordionViewTest() {
		Component accordion = (Component) accordionView;
		assertTrue(accordion.isVisible());
		accordionView.hideAccordionView();
		assertFalse(accordion.isVisible());
		accordionView.showAccordionView();
		assertTrue(accordion.isVisible());
	}

	@Test
	public void setRoomContainerTest() {
		Table roomTable = accordionView.getRoomTable();
		BeanItemContainer<ResourceModel> container = new BeanItemContainer<>(
				ResourceModel.class);
		ResourceModel model = new ResourceModel();
		container.addBean(model);
		accordionView.setRoomContainer(container);

		assertEquals(model, roomTable.getContainerDataSource().getItemIds()
				.iterator().next());
	}

	@Test
	public void setCourseContainerTest() {
		Table courseTabel = accordionView.getCourseTable();
		BeanItemContainer<ResourceModel> container = new BeanItemContainer<>(
				ResourceModel.class);
		ResourceModel model = new ResourceModel();
		container.addBean(model);
		accordionView.setCourseContainer(container);

		assertEquals(model, courseTabel.getContainerDataSource().getItemIds()
				.iterator().next());
	}

	@Test
	public void setPersonContainerTest() {
		Table personTable = accordionView.getPersonTable();
		BeanItemContainer<ResourceModel> container = new BeanItemContainer<>(
				ResourceModel.class);
		ResourceModel model = new ResourceModel();
		container.addBean(model);
		accordionView.setPersonContainer(container);

		assertEquals(model, personTable.getContainerDataSource().getItemIds()
				.iterator().next());
	}

	@Test
	public void setPoiContainerTest() {
		Table poiTable = accordionView.getPoiTable();
		BeanItemContainer<ResourceModel> container = new BeanItemContainer<>(
				ResourceModel.class);
		ResourceModel model = new ResourceModel();
		container.addBean(model);
		accordionView.setPoiContainer(container);

		assertEquals(model, poiTable.getContainerDataSource().getItemIds()
				.iterator().next());
	}
}
