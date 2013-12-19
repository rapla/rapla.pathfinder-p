package com.pathfinder.view.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.pathfinder.model.CourseModel;
import com.pathfinder.model.PersonModel;
import com.pathfinder.model.PoiModel;
import com.pathfinder.model.RoomModel;
import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.pathfinder.view.listener.TreeStructureViewListenerSpec;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnHeaderMode;

/**
 * Defines the TreeStructure - accordion plus Tables to view the search results
 * 
 * @author alexh
 * 
 */
public class AccordionView extends CustomComponent implements AccordionSpec {

	private final Accordion accordion = new Accordion();
	private final TranslatorSpec translator = Translator.getInstance();
	private String roomsString = new String(
			translator.translate(TranslationKeys.ROOM));
	private String coursesString = new String(
			translator.translate(TranslationKeys.COURSE));
	private String personsString = new String(
			translator.translate(TranslationKeys.PERSON));
	private String poisString = new String(
			translator.translate(TranslationKeys.POI));
	private Table roomTable = null;
	private Table courseTable = null;
	private Table personTable = null;
	private Table poiTable = null;

	// Needed BeanItemContainer
	private BeanItemContainer<RoomModel> roomContainer = new BeanItemContainer<RoomModel>(
			RoomModel.class);
	private BeanItemContainer<CourseModel> courseContainer = new BeanItemContainer<CourseModel>(
			CourseModel.class);
	private BeanItemContainer<PersonModel> personContainer = new BeanItemContainer<PersonModel>(
			PersonModel.class);
	private BeanItemContainer<PoiModel> poiContainer = new BeanItemContainer<PoiModel>(
			PoiModel.class);

	private List<TreeStructureViewListenerSpec> listener = new ArrayList<TreeStructureViewListenerSpec>();

	public AccordionView() {
		this.roomTable = createTable(roomContainer);
		this.courseTable = createTable(courseContainer);
		this.personTable = createTable(personContainer);
		this.poiTable = createTable(poiContainer);
		this.buildLayout();

		this.setCompositionRoot(accordion);
	}

	private <T> Table createTable(BeanItemContainer<T> beanItemContainer) {
		Table table = new Table();
		table.setContainerDataSource(beanItemContainer);

		table.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
		table.setVisibleColumns(new Object[] { "name" });
		table.setPageLength(5);
		table.setSizeFull();
		table.setSelectable(true);

		return table;
	}

	private void buildLayout() {
		accordion.setSizeFull();
		accordion.addTab(roomTable, roomsString);
		accordion.addTab(courseTable, coursesString);
		accordion.addTab(personTable, personsString);
		accordion.addTab(poiTable, poisString);
	}

	@Override
	public void setRoomContainer(BeanItemContainer<RoomModel> beanItemContainer) {
		this.roomContainer = beanItemContainer;
		roomTable.setContainerDataSource(roomContainer);
	}

	@Override
	public void setCourseContainer(
			BeanItemContainer<CourseModel> beanItemContainer) {
		this.courseContainer = beanItemContainer;
		courseTable.setContainerDataSource(courseContainer);
	}

	@Override
	public void setPersonContainer(
			BeanItemContainer<PersonModel> beanItemContainer) {
		this.personContainer = beanItemContainer;
		personTable.setContainerDataSource(personContainer);
	}

	@Override
	public void setPoiContainer(BeanItemContainer<PoiModel> beanItemContainer) {
		this.poiContainer = beanItemContainer;
		poiTable.setContainerDataSource(poiContainer);
	}

	public void addFilters(String filterString) {
		Filter filter = new SimpleStringFilter("name", filterString, false,
				false);
		roomContainer.removeAllContainerFilters();
		courseContainer.removeAllContainerFilters();
		personContainer.removeAllContainerFilters();
		poiContainer.removeAllContainerFilters();

		roomContainer.addContainerFilter(filter);
		courseContainer.addContainerFilter(filter);
		personContainer.addContainerFilter(filter);
		poiContainer.addContainerFilter(filter);
	}

	@Override
	public void updateTranslations(Locale locale) {
		roomsString = new String(translator.translate(TranslationKeys.ROOM));
		coursesString = new String(translator.translate(TranslationKeys.COURSE));
		personsString = new String(translator.translate(TranslationKeys.PERSON));
		poisString = new String(translator.translate(TranslationKeys.POI));

		// accordion.getTab(rooms).setCaption(roomsString);
		// accordion.getTab(courses).setCaption(coursesString);
		// accordion.getTab(persons).setCaption(personsString);
		// accordion.getTab(pois).setCaption(poisString);
	}

	@Override
	public void addTreeStructureSpecListener(
			TreeStructureViewListenerSpec listener) {
		this.listener.add(listener);
	}
}