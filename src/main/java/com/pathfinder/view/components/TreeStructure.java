package com.pathfinder.view.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.pathfinder.model.ResourceModel;
import com.pathfinder.translation.TranslationKeys;
import com.pathfinder.translation.Translator;
import com.pathfinder.translation.TranslatorSpec;
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
public class TreeStructure extends CustomComponent implements TreeStructureSpec {

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
	private Table rooms = null;
	private Table courses = null;
	private Table persons = null;
	private Table pois = null;
	private BeanItemContainer<ResourceModel> roomContainer = new BeanItemContainer<ResourceModel>(ResourceModel.class);
	private BeanItemContainer<ResourceModel> courseContainer = new BeanItemContainer<ResourceModel>(ResourceModel.class);;
	private BeanItemContainer<ResourceModel> personContainer = new BeanItemContainer<ResourceModel>(ResourceModel.class);;
	private BeanItemContainer<ResourceModel> poiContainer = new BeanItemContainer<ResourceModel>(ResourceModel.class);;

	private List<TreeStructureViewListenerSpec> listener = new ArrayList<TreeStructureViewListenerSpec>();

	public TreeStructure() {
		this.rooms = createTable(roomContainer);
		this.courses = createTable(courseContainer);
		this.persons = createTable(personContainer);
		this.pois = createTable(poiContainer);
		init();

		setCompositionRoot(accordion);
	}

	private void init() {
		accordion.setSizeFull();
		accordion.addTab(rooms, roomsString);
		accordion.addTab(courses, coursesString);
		accordion.addTab(persons, personsString);
		accordion.addTab(pois, poisString);
	}

	private Table createTable(BeanItemContainer<ResourceModel> beanItemContainer) {
		Table table = new Table();

		// TODO Only for testing
		for (int i = 0; i < 10; i++) {
			ResourceModel resource = new ResourceModel(Integer.toString(i));
			beanItemContainer.addItem(resource);
		}

		table.setContainerDataSource(beanItemContainer);

		table.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
		// TODO column identifier have to be checked - it should be the same in
		// all models
		// table.setVisibleColumns(new Object[] { "name" });
		table.setPageLength(5);
		table.setSizeFull();
		table.setSelectable(true);

		return table;
	}

	public void setContainerDataSourceForRooms(
			BeanItemContainer<ResourceModel> container) {
		this.roomContainer = container;
		rooms.setContainerDataSource(roomContainer);
	}

	public void setContainerDataSourceForCourses(
			BeanItemContainer<ResourceModel> container) {
		this.courseContainer = container;
		courses.setContainerDataSource(courseContainer);
	}

	public void setContainerDataSourceForPersons(
			BeanItemContainer<ResourceModel> container) {
		this.personContainer = container;
		persons.setContainerDataSource(personContainer);
	}

	public void setContainerDataSourceForPois(
			BeanItemContainer<ResourceModel> container) {
		this.poiContainer = container;
		pois.setContainerDataSource(poiContainer);
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