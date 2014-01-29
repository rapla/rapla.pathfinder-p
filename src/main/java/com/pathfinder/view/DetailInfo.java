package com.pathfinder.view;

import com.pathfinder.model.Attribut;
import com.pathfinder.util.properties.ApplicationProperties;
import com.pathfinder.util.properties.ApplicationPropertiesSpec;
import com.pathfinder.util.properties.PropertiesKey;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.CellStyleGenerator;
import com.vaadin.ui.Table.ColumnHeaderMode;

/**
 * Defines the detail view for persons, courses, pois or rooms
 * 
 * @author alexh
 * 
 */
public class DetailInfo extends CustomComponent implements DetailInfoSpec {
	private final Object[] visibleColumns = new String[] {
			Attribut.PROPERTY_LABEL, Attribut.PROPERTY_VALUE };
	private final Table detailInfoTable = new Table();
	private final BeanItemContainer<Attribut> attributeContainer = new BeanItemContainer<Attribut>(
			Attribut.class);
	private final ApplicationPropertiesSpec properties = ApplicationProperties
			.getInstance();

	private final String IMAGE_PATH = "img/";
	private final String IMAGE_ENDING = ".png";
	private final String DEFAULT_IMAGE = IMAGE_PATH
			+ properties.getProperty(PropertiesKey.DEFAULT_IMAGE_NAME);

	private final HorizontalLayout layout = new HorizontalLayout();

	private Image image = new Image();

	public DetailInfo() {
		initTable();
		buildLayout();
		addStyling();
		setCompositionRoot(layout);
	}

	private void initTable() {
		detailInfoTable.setContainerDataSource(attributeContainer);
		detailInfoTable.setVisibleColumns(visibleColumns);
		detailInfoTable.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
		detailInfoTable.setCacheRate(1);
		detailInfoTable.setSelectable(false);
		detailInfoTable.setCellStyleGenerator(new CustomCellStyleGenerator());
		detailInfoTable.setSizeFull();
		layout.addComponent(detailInfoTable);
	}

	private void buildLayout() {
		layout.setSizeFull();
		layout.setVisible(false);
		layout.addComponent(image);
		layout.setExpandRatio(detailInfoTable, 2);
		layout.setExpandRatio(image, 1);

		detailInfoTable.setVisible(false);
	}

	class CustomCellStyleGenerator implements CellStyleGenerator {
		@Override
		public String getStyle(Table source, Object itemId, Object propertyId) {
			return "result-row";
		}
	}

	private void addStyling() {
		// TODO .actualFreeRoomsLabel.addStyleName("big-caption");
		this.addStyleName("detailInfo");
		detailInfoTable.addStyleName("global-table");
	}

	@Override
	public void addDetails(BeanItemContainer<Attribut> resourceDetails) {
		int length = 0;
		for (Attribut attributeItem : resourceDetails.getItemIds()) {
			if (!"resourceurl".equals(attributeItem.getKey()))
				if (!"bild".equals(attributeItem.getKey())) {
					this.detailInfoTable.addItem(attributeItem);
					length += 1;
				}
			if ("bild".equals(attributeItem.getKey())) {
				ThemeResource tr = new ThemeResource(IMAGE_PATH
						+ attributeItem.getValue() + IMAGE_ENDING);
				image.setSource(tr);
				image.setWidth(33, Unit.PERCENTAGE);
				image.markAsDirty();

			}
		}

		detailInfoTable.setPageLength(length);
		detailInfoTable.setVisible(true);
		layout.setVisible(true);
	}

	@Override
	public void removeDetails() {
		detailInfoTable.removeAllItems();
		detailInfoTable.setVisible(false);
	}

	@Override
	public void updateTranslations() {
		// Will be blank
	}
}