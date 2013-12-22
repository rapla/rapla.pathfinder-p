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
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
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
	private String[] visibleRoomTableColumns = new String[] { "name" };
	private String[] visibleCourseTableColumns = new String[] { "name" };
	private String[] visiblePersonTableColumns = new String[] { "name" };
	private String[] visiblePoiTableColumns = new String[] { "name", "roomNr" };

	// Needed BeanItemContainer
	private BeanItemContainer<RoomModel> roomContainer = new BeanItemContainer<RoomModel>(
			RoomModel.class);
	private BeanItemContainer<CourseModel> courseContainer = new BeanItemContainer<CourseModel>(
			CourseModel.class);
	private BeanItemContainer<PersonModel> personContainer = new BeanItemContainer<PersonModel>(
			PersonModel.class);
	private BeanItemContainer<PoiModel> poiContainer = new BeanItemContainer<PoiModel>(
			PoiModel.class);

	public AccordionView() {
		this.roomTable = createTable(roomContainer, visibleRoomTableColumns);
		this.courseTable = createTable(courseContainer,
				visibleCourseTableColumns);
		this.personTable = createTable(personContainer,
				visiblePersonTableColumns);
		this.poiTable = createTable(poiContainer, visiblePoiTableColumns);
		this.buildLayout();

		this.setCompositionRoot(accordion);
	}

	private <T> Table createTable(BeanItemContainer<T> beanItemContainer,
			Object[] vivisbleColumns) {
		Table table = new Table();
		table.setContainerDataSource(beanItemContainer);
		table.setImmediate(true);

		table.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
		table.setVisibleColumns(vivisbleColumns);
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

	public void addFilters(String filterString) {
		List<Filter> roomFilters = new ArrayList<Filter>();
		for (String visibleColumn : visibleRoomTableColumns) {
			Filter filter = new SimpleStringFilter(visibleColumn, filterString,
					true, false);
			roomFilters.add(filter);
		}

		List<Filter> courseFilters = new ArrayList<Filter>();
		for (String visibleColumn : visibleCourseTableColumns) {
			Filter filter = new SimpleStringFilter(visibleColumn, filterString,
					true, false);
			courseFilters.add(filter);
		}

		List<Filter> personFilters = new ArrayList<Filter>();
		for (String visibleColumn : visiblePersonTableColumns) {
			Filter filter = new SimpleStringFilter(visibleColumn, filterString,
					true, false);
			personFilters.add(filter);
		}

		List<Filter> poiFilters = new ArrayList<Filter>();
		for (String visibleColumn : visiblePoiTableColumns) {
			Filter filter = new SimpleStringFilter(visibleColumn, filterString,
					true, false);
			poiFilters.add(filter);
		}

		//Only for testing
		System.out.println(roomFilters.toArray(new Filter[] {}).length);
		System.out.println(courseFilters.toArray(new Filter[] {}).length);
		System.out.println(personFilters.toArray(new Filter[] {}).length);
		System.out.println(poiFilters.toArray(new Filter[] {}).length);

		roomContainer.removeAllContainerFilters();
		courseContainer.removeAllContainerFilters();
		personContainer.removeAllContainerFilters();
		poiContainer.removeAllContainerFilters();

		roomContainer.addContainerFilter(new Or(roomFilters
				.toArray(new Filter[] {})));
		courseContainer.addContainerFilter(new Or(courseFilters
				.toArray(new Filter[] {})));
		personContainer.addContainerFilter(new Or(personFilters
				.toArray(new Filter[] {})));
		poiContainer.addContainerFilter(new Or(poiFilters
				.toArray(new Filter[] {})));
	}

	@Override
	public void setRoomContainer(BeanItemContainer<RoomModel> beanItemContainer) {
		this.roomTable.removeAllItems();
		for (RoomModel itemId : beanItemContainer.getItemIds()) {
			this.roomTable.addItem(itemId);
		}

		// TODO Table have to be refreshed to show the newest data
		// roomTable.setContainerDataSource(roomTable.getContainerDataSource());
		// roomTable.refreshRowCache();
		// accordion.markAsDirty();
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
}