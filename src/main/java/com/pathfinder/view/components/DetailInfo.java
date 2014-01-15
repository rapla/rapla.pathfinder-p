package com.pathfinder.view.components;

import com.pathfinder.model.Attribut;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;
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
	private final Table table = new Table();
	private final BeanItemContainer<Attribut> attributeContainer = new BeanItemContainer<Attribut>(
			Attribut.class);

	public DetailInfo() {
		initTable();
		setStyling();
		setCompositionRoot(table);
	}

	private void initTable() {
		table.setContainerDataSource(attributeContainer);
		table.setVisibleColumns(visibleColumns);
		table.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
		table.setCacheRate(1);
		table.setSelectable(false);
		table.setSizeFull();
	}

	private void setStyling() {
		this.setPrimaryStyleName("details");
	}

	@Override
	public void addDetails(BeanItemContainer<Attribut> resourceDetails) {
		for (Attribut attributeItem : resourceDetails.getItemIds()) {
			if (!"resourceurl".equals(attributeItem.getKey())) {
				this.table.addItem(attributeItem);
			}
		}
	}

	@Override
	public void removeDetails() {
		table.removeAllItems();
	}

	@Override
	public void updateTranslations() {
		// Will be blank
	}
}