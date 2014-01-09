package com.pathfinder.view.components;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pathfinder.model.CourseModel;
import com.pathfinder.model.PersonModel;
import com.pathfinder.model.PoiModel;
import com.pathfinder.model.ResourceModel;
import com.pathfinder.model.RoomModel;
import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnHeaderMode;

/**
 * Defines the accordion view plus tables to view the search results
 * 
 * @author alexh
 * 
 */
public class AccordionView extends CustomComponent implements AccordionViewSpec {

	private static final Logger logger = LogManager
			.getLogger(AccordionView.class.getName());

	private final TranslatorSpec translator = Translator.getInstance();

	private final ThemeResource orderlines = new ThemeResource(
			"icon/orderlines.png");
	private String accordionCaptionRooms = new String(
			translator.translate(TranslationKeys.ROOMS));
	private String accordionCaptionCourses = new String(
			translator.translate(TranslationKeys.COURSES));
	private String accordionCaptionPersons = new String(
			translator.translate(TranslationKeys.PERSONS));
	private String accordionCaptionPois = new String(
			translator.translate(TranslationKeys.POI));

	private final Accordion accordion = new Accordion();
	private final Table roomTable = new Table();
	private final Table courseTable = new Table();
	private final Table personTable = new Table();
	private final Table poiTable = new Table();

	private final String[] visibleRoomTableColumns = new String[] { ResourceModel.PROPERTY_NAME };
	private final String[] visibleCourseTableColumns = new String[] { ResourceModel.PROPERTY_NAME };
	private final String[] visiblePersonTableColumns = new String[] { ResourceModel.PROPERTY_NAME };
	private final String[] visiblePoiTableColumns = new String[] { ResourceModel.PROPERTY_NAME };

	private final BeanItemContainer<ResourceModel> roomContainer = new BeanItemContainer<ResourceModel>(
			ResourceModel.class);
	private final BeanItemContainer<ResourceModel> courseContainer = new BeanItemContainer<ResourceModel>(
			ResourceModel.class);
	private final BeanItemContainer<ResourceModel> personContainer = new BeanItemContainer<ResourceModel>(
			ResourceModel.class);
	private final BeanItemContainer<ResourceModel> poiContainer = new BeanItemContainer<ResourceModel>(
			ResourceModel.class);

	public AccordionView() {
		this.createTable(roomTable, roomContainer, visibleRoomTableColumns);
		this.createTable(courseTable, courseContainer,
				visibleCourseTableColumns);
		this.createTable(personTable, personContainer,
				visiblePersonTableColumns);
		this.createTable(poiTable, poiContainer, visiblePoiTableColumns);

		this.buildLayout();
		this.setCompositionRoot(accordion);
	}

	private <T> void createTable(Table table,
			BeanItemContainer<T> beanItemContainer, Object[] vivisbleColumns) {
		table.setContainerDataSource(beanItemContainer);
		table.setImmediate(true);
		table.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
		table.setVisibleColumns(vivisbleColumns);
		table.setPageLength(5);
		table.setSortContainerPropertyId(ResourceModel.PROPERTY_NAME);
		table.setSortAscending(true);
		table.setSelectable(true);
		table.setSizeFull();
		table.setPrimaryStyleName("result-table");
	}

	private void buildLayout() {
		Tab rooms = accordion.addTab(roomTable, accordionCaptionRooms);
		rooms.setIcon(orderlines);
		Tab courses = accordion.addTab(courseTable, accordionCaptionCourses);
		courses.setIcon(orderlines);
		Tab persons = accordion.addTab(personTable, accordionCaptionPersons);
		persons.setIcon(orderlines);
		Tab pois = accordion.addTab(poiTable, accordionCaptionPois);
		pois.setIcon(orderlines);

		this.accordion.setSizeFull();
		this.accordion.setPrimaryStyleName("accordion-result");
	}

	public void useFiltersForAllTables(String filterString) {
		/* Filter lists */
		List<Filter> roomFilters = new ArrayList<Filter>();
		List<Filter> courseFilters = new ArrayList<Filter>();
		List<Filter> personFilters = new ArrayList<Filter>();
		List<Filter> poiFilters = new ArrayList<Filter>();

		/* Create the filters */
		roomFilters = this.createFilters(visibleRoomTableColumns, filterString);
		courseFilters = this.createFilters(visibleCourseTableColumns,
				filterString);
		personFilters = this.createFilters(visiblePersonTableColumns,
				filterString);
		poiFilters = this.createFilters(visiblePoiTableColumns, filterString);

		/* Remove the old filters */
		this.removeAllFiltersFromContainers();

		/* Add the new filters */
		this.addFiltersToTables(roomFilters, roomContainer, "room");
		this.addFiltersToTables(courseFilters, courseContainer, "course");
		this.addFiltersToTables(personFilters, personContainer, "person");
		this.addFiltersToTables(poiFilters, poiContainer, "poi");
	}

	private List<Filter> createFilters(String[] visibleTableColumns,
			String filterString) {
		List<Filter> filters = new ArrayList<Filter>();
		for (String visibleColumn : visibleTableColumns) {
			Filter filter = new SimpleStringFilter(visibleColumn, filterString,
					true, false);
			filters.add(filter);
		}
		return filters;
	}

	private void removeAllFiltersFromContainers() {
		roomContainer.removeAllContainerFilters();
		courseContainer.removeAllContainerFilters();
		personContainer.removeAllContainerFilters();
		poiContainer.removeAllContainerFilters();
	}

	private <T> void addFiltersToTables(List<Filter> filters,
			BeanItemContainer<T> beanItemContainer, String type) {
		logger.trace("Length of " + type + " filters: "
				+ filters.toArray(new Filter[] {}).length);
		beanItemContainer.addContainerFilter(new Or(filters
				.toArray(new Filter[] {})));
	}

	public void deselectClickedItem(Table table, Object itemId) {
		// TODO Doesn´t work yet
		table.sanitizeSelection();
		table.unselect(itemId);
	}

	@Override
	public void setRoomContainer(BeanItemContainer<RoomModel> beanItemContainer) {
		this.roomTable.removeAllItems();
		for (ResourceModel itemId : beanItemContainer.getItemIds()) {
			this.roomTable.addItem(itemId);
		}
	}

	@Override
	public void setCourseContainer(
			BeanItemContainer<CourseModel> beanItemContainer) {
		this.courseContainer.removeAllItems();
		this.courseContainer.addAll(beanItemContainer.getItemIds());
	}

	@Override
	public void setPersonContainer(
			BeanItemContainer<PersonModel> beanItemContainer) {
		this.personContainer.removeAllItems();
		this.personContainer.addAll(beanItemContainer.getItemIds());
	}

	@Override
	public void setPoiContainer(BeanItemContainer<PoiModel> beanItemContainer) {
		this.poiContainer.removeAllItems();
		this.poiContainer.addAll(beanItemContainer.getItemIds());
	}

	@Override
	public void addItemClickListenerRoomTable(ItemClickListener listener) {
		roomTable.addItemClickListener(listener);
	}

	@Override
	public void addItemClickListenerCourseTable(ItemClickListener listener) {
		courseTable.addItemClickListener(listener);
	}

	@Override
	public void addItemClickListenerPersonTable(ItemClickListener listener) {
		personTable.addItemClickListener(listener);
	}

	@Override
	public void addItemClickListenerPoiTable(ItemClickListener listener) {
		poiTable.addItemClickListener(listener);
	}

	@Override
	public void updateTranslations() {
		accordionCaptionRooms = new String(
				translator.translate(TranslationKeys.ROOMS));
		accordionCaptionCourses = new String(
				translator.translate(TranslationKeys.COURSES));
		accordionCaptionPersons = new String(
				translator.translate(TranslationKeys.PERSONS));
		accordionCaptionPois = new String(
				translator.translate(TranslationKeys.POI));

		accordion.getTab(roomTable).setCaption(accordionCaptionRooms);
		accordion.getTab(courseTable).setCaption(accordionCaptionCourses);
		accordion.getTab(personTable).setCaption(accordionCaptionPersons);
		accordion.getTab(poiTable).setCaption(accordionCaptionPois);
	}
}