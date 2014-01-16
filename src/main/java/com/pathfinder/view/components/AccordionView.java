package com.pathfinder.view.components;

import java.util.ArrayList;
import java.util.List;

import com.pathfinder.model.ResourceModel;
import com.pathfinder.util.properties.ApplicationProperties;
import com.pathfinder.util.properties.PropertiesKey;
import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.CellStyleGenerator;
import com.vaadin.ui.Table.ColumnHeaderMode;

/**
 * Defines the accordion view plus tables to view the search results
 * 
 * @author alexh
 * 
 */
public class AccordionView extends CustomComponent implements AccordionViewSpec {
	private final TranslatorSpec translator = Translator.getInstance();

	private final ThemeResource orderlines = new ThemeResource(
			"icon/orderlines.png");
	private String accordionCaptionRooms = null;
	private String accordionCaptionCourses = null;
	private String accordionCaptionPersons = null;
	private String accordionCaptionPois = null;

	private final Accordion accordion = new Accordion();
	private final Table roomTable = new Table();
	private final Table courseTable = new Table();
	private final Table personTable = new Table();
	private final Table poiTable = new Table();
	private final String[] visibleTableColumns = new String[] { ResourceModel.PROPERTY_NAME };
	private final String[] visibleCourseColumns = new String[] {
			ResourceModel.PROPERTY_NAME, ResourceModel.PROPERTY_FACULTY };

	private final BeanItemContainer<ResourceModel> roomContainer = new BeanItemContainer<ResourceModel>(
			ResourceModel.class);
	private final BeanItemContainer<ResourceModel> courseContainer = new BeanItemContainer<ResourceModel>(
			ResourceModel.class);
	private final BeanItemContainer<ResourceModel> personContainer = new BeanItemContainer<ResourceModel>(
			ResourceModel.class);
	private final BeanItemContainer<ResourceModel> poiContainer = new BeanItemContainer<ResourceModel>(
			ResourceModel.class);

	public AccordionView() {
		this.createTable(roomTable, roomContainer, visibleTableColumns);
		this.createTable(courseTable, courseContainer, visibleCourseColumns);
		this.createTable(personTable, personContainer, visibleTableColumns);
		this.createTable(poiTable, poiContainer, visibleTableColumns);

		this.buildLayout();
		this.addStyling();
		this.setCompositionRoot(accordion);
	}

	private <T> void createTable(Table table,
			BeanItemContainer<T> beanItemContainer, Object[] vivisbleColumns) {
		table.setContainerDataSource(beanItemContainer);
		table.setImmediate(true);
		table.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
		table.setVisibleColumns(vivisbleColumns);
		table.setPageLength(5);
		table.setCacheRate(Double.parseDouble(ApplicationProperties
				.getInstance().getProperty(PropertiesKey.TABLE_CACHE_RATE)));
		table.setSortContainerPropertyId(ResourceModel.PROPERTY_NAME);
		table.setSortAscending(true);
		table.setSelectable(true);
		table.setSizeFull();
		table.setCellStyleGenerator(new CustomCellStyleGenerator());
	}

	class CustomCellStyleGenerator implements CellStyleGenerator {
		@Override
		public String getStyle(Table source, Object itemId, Object propertyId) {
			return "result-row";
		}
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
	}

	private void addStyling() {
		this.accordion.setPrimaryStyleName("accordion-result");
	}

	@Override
	public void addItemClickListener(ItemClickListener listener) {
		roomTable.addItemClickListener(listener);
		courseTable.addItemClickListener(listener);
		personTable.addItemClickListener(listener);
		poiTable.addItemClickListener(listener);
	}

	@Override
	public void useFiltersForAllTables(String filterString) {
		/* Filter lists */
		List<Filter> tableFilters = new ArrayList<Filter>();
		// List<Filter> roomFilters = new ArrayList<Filter>();
		// List<Filter> courseFilters = new ArrayList<Filter>();
		// List<Filter> personFilters = new ArrayList<Filter>();
		// List<Filter> poiFilters = new ArrayList<Filter>();

		/* Create the filters */
		tableFilters = createFiltersForAllPropertyIds(filterString);
		// roomFilters = this.createFilters(visibleTableColumns, filterString);
		// courseFilters = this.createFilters(visibleTableColumns,
		// filterString);
		// personFilters = this.createFilters(visibleTableColumns,
		// filterString);
		// poiFilters = this.createFilters(visibleTableColumns, filterString);

		/* Remove the old filters */
		this.removeAllFiltersFromContainers();

		/* Add the new filters */
		this.addFiltersToTables(tableFilters, roomContainer);
		this.addFiltersToTables(tableFilters, courseContainer);
		this.addFiltersToTables(tableFilters, personContainer);
		this.addFiltersToTables(tableFilters, poiContainer);

		// Update table captions
		this.updateTableCaptions();
	}

	private List<Filter> createFiltersForAllPropertyIds(String filterString) {
		List<Filter> filters = new ArrayList<Filter>();

		BeanItem<ResourceModel> beanItem = new BeanItem<ResourceModel>(
				new ResourceModel());
		for (Object propertyId : beanItem.getItemPropertyIds()) {
			Filter filter = new SimpleStringFilter(propertyId, filterString,
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
			BeanItemContainer<T> beanItemContainer) {
		beanItemContainer.addContainerFilter(new Or(filters
				.toArray(new Filter[] {})));
	}

	@Override
	public void deselectClickedItem(Table table, Object itemId) {
		table.unselect(itemId);
	}

	@Override
	public void setRoomContainer(
			BeanItemContainer<ResourceModel> beanItemContainer) {
		this.roomTable.removeAllItems();
		this.roomContainer.addAll(beanItemContainer.getItemIds());
		this.updateTableCaptions();
	}

	@Override
	public void setCourseContainer(
			BeanItemContainer<ResourceModel> beanItemContainer) {
		this.courseTable.removeAllItems();
		this.courseContainer.addAll(beanItemContainer.getItemIds());
		this.updateTableCaptions();
	}

	@Override
	public void setPersonContainer(
			BeanItemContainer<ResourceModel> beanItemContainer) {
		this.personTable.removeAllItems();
		this.personContainer.addAll(beanItemContainer.getItemIds());
		this.updateTableCaptions();
	}

	@Override
	public void setPoiContainer(
			BeanItemContainer<ResourceModel> beanItemContainer) {
		this.poiTable.removeAllItems();
		this.poiContainer.addAll(beanItemContainer.getItemIds());
		this.updateTableCaptions();
	}

	private void updateTableCaptions() {
		if (getRoomTableLength() > 0) {
			accordionCaptionRooms = new String(this.getRoomTableLength() + " "
					+ translator.translate(TranslationKeys.ROOMS));
			accordion.getTab(roomTable).setCaption(accordionCaptionRooms);
			accordion.getTab(roomTable).setVisible(true);
		} else {
			accordion.getTab(roomTable).setVisible(false);
		}

		if (getCourseTableLength() > 0) {
			accordionCaptionCourses = new String(this.getCourseTableLength()
					+ " " + translator.translate(TranslationKeys.COURSES));
			accordion.getTab(courseTable).setCaption(accordionCaptionCourses);
			accordion.getTab(courseTable).setVisible(true);
		} else {
			accordion.getTab(courseTable).setVisible(false);
		}

		if (getPersonTableLength() > 0) {
			accordionCaptionPersons = new String(this.getPersonTableLength()
					+ " " + translator.translate(TranslationKeys.PERSONS));
			accordion.getTab(personTable).setCaption(accordionCaptionPersons);
			accordion.getTab(personTable).setVisible(true);
		} else {
			accordion.getTab(personTable).setVisible(false);
		}

		if (getPoiTableLength() > 0) {
			accordionCaptionPois = new String(this.getPoiTableLength() + " "
					+ translator.translate(TranslationKeys.POI));
			accordion.getTab(poiTable).setCaption(accordionCaptionPois);
			accordion.getTab(poiTable).setVisible(true);
		} else {
			accordion.getTab(poiTable).setVisible(false);
		}
	}

	private int getRoomTableLength() {
		return roomTable.size();
	}

	private int getCourseTableLength() {
		return courseTable.size();
	}

	private int getPersonTableLength() {
		return personTable.size();
	}

	private int getPoiTableLength() {
		return poiTable.size();
	}

	@Override
	public void updateTranslations() {
		this.updateTableCaptions();
	}
}