package com.pathfinder.view;

import java.util.ArrayList;
import java.util.List;

import com.pathfinder.model.ResourceModel;
import com.pathfinder.model.ResourceType;
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
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
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

	/** To translate GUI-Components */
	private final TranslatorSpec translator = Translator.getInstance();

	/** Icon for each accordion tab */
	private final ThemeResource orderlines = new ThemeResource(
			"icon/orderlines.png");

	/** Captions for each accordion tab */
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

	private void createTable(Table table,
			BeanItemContainer<ResourceModel> beanItemContainer,
			Object[] vivisbleColumns) {
		table.setContainerDataSource(beanItemContainer);
		table.setImmediate(true);
		table.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
		table.setVisibleColumns(vivisbleColumns);
		table.setPageLength(8);
		table.setCacheRate(Double.parseDouble(ApplicationProperties
				.getInstance().getProperty(PropertiesKey.TABLE_CACHE_RATE)));
		table.setSortContainerPropertyId(ResourceModel.PROPERTY_NAME);
		table.setSortAscending(true);
		table.setSelectable(true);
		table.setSizeFull();
		table.setCellStyleGenerator(new CustomCellStyleGenerator());
	}

	private class CustomCellStyleGenerator implements CellStyleGenerator {
		@Override
		public String getStyle(Table source, Object itemId, Object propertyId) {
			return "result-row";
		}
	}

	private void buildLayout() {

		Tab rooms = accordion.addTab(
				createTabContent(ResourceType.ROOM, roomTable),
				accordionCaptionRooms);
		rooms.setIcon(orderlines);
		Tab courses = accordion.addTab(
				createTabContent(ResourceType.COURSE, courseTable),
				accordionCaptionCourses);
		courses.setIcon(orderlines);
		Tab persons = accordion.addTab(
				createTabContent(ResourceType.PERSON, personTable),
				accordionCaptionPersons);
		persons.setIcon(orderlines);
		Tab pois = accordion.addTab(
				createTabContent(ResourceType.POI, poiTable),
				accordionCaptionPois);
		pois.setIcon(orderlines);
		this.accordion.setSizeFull();
		this.setSizeFull();
	}

	private HorizontalLayout createTabContent(ResourceType type, Table table) {
		final HorizontalLayout horizontal = new HorizontalLayout();
		final GridLayout verticalGrid = new GridLayout(1, 2);
		ThemeResource res = new ThemeResource("icon/dropup_icon.png");
		Button up = new Button();
		up.setPrimaryStyleName("button-up");
		up.setIcon(res);
		up.addClickListener(new ScrollClickListener(type, true));
		Button down = new Button();
		down.setPrimaryStyleName("button-down");
		down.setIcon(res);
		down.addClickListener(new ScrollClickListener(type, false));

		verticalGrid.addComponent(up, 0, 0);
		verticalGrid.addComponent(down, 0, 1);
		// verticalGrid.setSizeUndefined();
		// verticalGrid.setHeight(100, Unit.PERCENTAGE);
		// verticalGrid.setComponentAlignment(up, Alignment.MIDDLE_CENTER);
		// verticalGrid.setComponentAlignment(down, Alignment.MIDDLE_CENTER);

		horizontal.addComponent(table);
		horizontal.addComponent(verticalGrid);
		horizontal.setSizeFull();
		horizontal.setExpandRatio(table, 1f);

		return horizontal;
	}

	private class ScrollClickListener implements ClickListener {
		ResourceType type;
		boolean up;

		public ScrollClickListener(ResourceType type, boolean up) {
			this.type = type;
			this.up = up;
		}

		@Override
		public void buttonClick(ClickEvent event) {
			if (up) {
				switch (type) {
				case ROOM:
					upScrolling(roomTable);
					break;
				case COURSE:
					upScrolling(courseTable);
					break;
				case PERSON:
					upScrolling(personTable);
					break;
				case POI:
					upScrolling(poiTable);
					break;
				default:

				}
			} else {
				switch (type) {
				case ROOM:
					downScrolling(roomTable);
					break;
				case COURSE:
					downScrolling(courseTable);
					break;
				case PERSON:
					downScrolling(personTable);
					break;
				case POI:
					downScrolling(poiTable);
					break;
				default:
				}
			}

		}

		private void upScrolling(Table table) {
			int oldIndex = table.getCurrentPageFirstItemIndex();
			int newIndex;
			int pageLength = table.getPageLength();

			newIndex = oldIndex - pageLength;

			if (newIndex <= 0) {
				newIndex = 1;
			}

			table.setCurrentPageFirstItemIndex(newIndex);
		}

		private void downScrolling(Table table) {
			int oldIndex = table.getCurrentPageFirstItemIndex();
			int newIndex;
			int pageLength = table.getPageLength();

			newIndex = oldIndex + pageLength;

			table.setCurrentPageFirstItemIndex(newIndex);
		}
	}

	private void addStyling() {
		this.accordion.setPrimaryStyleName("resource-accordion");
	}

	@Override
	public void addAccordionTableItemClickListener(ItemClickListener listener) {
		roomTable.addItemClickListener(listener);
		courseTable.addItemClickListener(listener);
		personTable.addItemClickListener(listener);
		poiTable.addItemClickListener(listener);
	}

	@Override
	public void useFiltersForAllTables(String filterString) {
		/* Filter lists */
		List<Filter> tableFilters = new ArrayList<Filter>();

		/* Create the filters */
		tableFilters = createFiltersForAllPropertyIds(filterString);

		/* Remove the old filters */
		this.removeAllFiltersFromContainers();

		/* Add the new filters */
		this.addFiltersToTables(tableFilters, roomContainer);
		this.addFiltersToTables(tableFilters, courseContainer);
		this.addFiltersToTables(tableFilters, personContainer);
		this.addFiltersToTables(tableFilters, poiContainer);

		/* Update table captions */
		this.updateTableCaptions();

		/* Jumps to the right accordion tab */
		this.avoidEmptyTab();
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
		accordionCaptionRooms = new String(
				translator.translate(TranslationKeys.ROOMS) + " ["
						+ this.getRoomTableLength() + "]");
		accordion.getTab(0).setCaption(accordionCaptionRooms);

		accordionCaptionCourses = new String(
				translator.translate(TranslationKeys.COURSES) + " ["
						+ this.getCourseTableLength() + "]");
		accordion.getTab(1).setCaption(accordionCaptionCourses);

		accordionCaptionPersons = new String(
				translator.translate(TranslationKeys.PERSONS) + " ["
						+ this.getPersonTableLength() + "]");
		accordion.getTab(2).setCaption(accordionCaptionPersons);

		accordionCaptionPois = new String(
				translator.translate(TranslationKeys.POI) + " ["
						+ this.getPoiTableLength() + "]");
		accordion.getTab(3).setCaption(accordionCaptionPois);
	}

	private void avoidEmptyTab() {
		if (this.getRoomTableLength() > 0) {
			this.accordion.setSelectedTab(0);
		} else if (this.getCourseTableLength() > 0) {
			this.accordion.setSelectedTab(1);
		} else if (this.getPersonTableLength() > 0) {
			this.accordion.setSelectedTab(2);
		} else if (this.getPoiTableLength() > 0) {
			this.accordion.setSelectedTab(3);
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
	public void hideAccordionView() {
		this.setVisible(false);
	}

	@Override
	public void showAccordionView() {
		this.setVisible(true);
	}

	@Override
	public void updateTranslations() {
		this.updateTableCaptions();
	}

	@Override
	public void doCleanup() {
		this.roomTable.removeAllItems();
		this.courseTable.removeAllItems();
		this.personTable.removeAllItems();
		this.poiTable.removeAllItems();
	}

	@Override
	public Table getRoomTable() {
		return roomTable;
	}

	@Override
	public Table getCourseTable() {
		return courseTable;
	}

	@Override
	public Table getPersonTable() {
		return personTable;
	}

	@Override
	public Table getPoiTable() {
		return poiTable;
	}

}