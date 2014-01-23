package com.pathfinder.view;

import com.pathfinder.model.Attribut;
import com.pathfinder.view.DetailInfoSpec;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;
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

	public DetailInfo() {
		initTable();
		buildLayout();
		addStyling();
		setCompositionRoot(detailInfoTable);
	}

	private void initTable() {
		detailInfoTable.setContainerDataSource(attributeContainer);
		detailInfoTable.setVisibleColumns(visibleColumns);
		detailInfoTable.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
		detailInfoTable.setCacheRate(1);
		detailInfoTable.setSelectable(false);
		detailInfoTable.setCellStyleGenerator(new CustomCellStyleGenerator());
		detailInfoTable.setSizeFull();
	}

	private void buildLayout() {
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
			if (!"resourceurl".equals(attributeItem.getKey())) {
				this.detailInfoTable.addItem(attributeItem);
				length += 1;
			}
		}
		detailInfoTable.setPageLength(length);
		detailInfoTable.setVisible(true);
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