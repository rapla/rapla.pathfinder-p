package com.pathfinder.view.components;

import com.pathfinder.model.FreeRoomModel;
import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.Table.ColumnHeaderMode;

public class FreeRoomView extends CustomComponent implements FreeRoomViewSpec {
	private final TranslatorSpec translator = Translator.getInstance();

	private final Label actualFreeRoomsLabel = new Label();

	private CssLayout cssLayout = new CssLayout();
	private BeanItemContainer<FreeRoomModel> freeRoomContainer = new BeanItemContainer<FreeRoomModel>(
			FreeRoomModel.class);
	private Table freeRoomTable = new Table();
	private final Object[] visibleFreeRoomTableColumns = new String[] {
			FreeRoomModel.PROPERTY_NAME, FreeRoomModel.PROPERTY_START,
			FreeRoomModel.PROPERTY_END, FreeRoomModel.PROPERTY_LINK };
	private final String[] headerFreeRoomTableCaptions = new String[] {
			"Raumname", "Beginn", "Ende", "Link" };

	private final Label noRoomsLabel = new Label(
			translator.translate(TranslationKeys.NO_FREE_ROOMS_AVAILABLE));

	private String room = translator.translate(TranslationKeys.ROOM);
	private String time = translator.translate(TranslationKeys.TIME);
	private String floorplan = translator
			.translate(TranslationKeys.TO_FLOOR_PLAN);

	public FreeRoomView() {
		this.createTable();
		this.buildLayout();
		this.setCompositionRoot(cssLayout);
	}

	private void createTable() {
		this.freeRoomTable.setContainerDataSource(freeRoomContainer);
		this.freeRoomTable.addGeneratedColumn(FreeRoomModel.PROPERTY_NAME,
				new LabelColumnGenerator());
		this.freeRoomTable.addGeneratedColumn(FreeRoomModel.PROPERTY_START,
				new LabelColumnGenerator());
		this.freeRoomTable.addGeneratedColumn(FreeRoomModel.PROPERTY_END,
				new LabelColumnGenerator());
		this.freeRoomTable.addGeneratedColumn(FreeRoomModel.PROPERTY_LINK,
				new LabelColumnGenerator());
		this.freeRoomTable.setImmediate(true);
		this.freeRoomTable.setColumnHeaderMode(ColumnHeaderMode.EXPLICIT);
		this.freeRoomTable.setVisibleColumns(visibleFreeRoomTableColumns);
		this.freeRoomTable.setColumnHeaders(headerFreeRoomTableCaptions);
		this.freeRoomTable.setPageLength(5);
		this.freeRoomTable.setCacheRate(1);
		this.freeRoomTable
				.setSortContainerPropertyId(FreeRoomModel.PROPERTY_START);
		this.freeRoomTable.setSortAscending(true);
		this.freeRoomTable.setWidth(100, Unit.PERCENTAGE);
		// TODO
		// this.freeRoomTable.setPrimaryStyleName("freeroom-table");
	}

	class LabelColumnGenerator implements ColumnGenerator {
		@Override
		public Component generateCell(Table source, Object itemId,
				Object columnId) {
			// Get the object stored in the cell as a property
			Property<?> prop = source.getItem(itemId).getItemProperty(columnId);

			if (FreeRoomModel.PROPERTY_NAME.equals(columnId)) {
				Label label = new Label(room + " " + prop.getValue());
				return label;
			} else if (FreeRoomModel.PROPERTY_START.equals(columnId)) {
				Label label = new Label(prop.getValue() + " " + time);
				return label;
			} else if (FreeRoomModel.PROPERTY_END.equals(columnId)) {
				Label label = new Label(prop.getValue() + " " + time);
				return label;
			}
			// TODO
			// else if (FreeRoomModel.PROPERTY_LINK.equals(columnId)) {
			// Label label = new Label(floorplan + " "
			// + new ExternalResource((String) prop.getValue()));
			// return label;
			// }
			else {
				return null;
			}
		}
	}

	class CustomTableFieldFactory extends DefaultFieldFactory {
		@Override
		public Field<?> createField(Container container, Object itemId,
				Object propertyId, Component uiContext) {

			final Field<?> field = super.createField(container, itemId,
					propertyId, uiContext);

			if (FreeRoomModel.PROPERTY_NAME.equals(propertyId)) {
				((AbstractField<?>) field).setCaption(room + " " + itemId);
				// Label nameProperty = new Label(room + " " + itemId);
				return field;
			} else if (FreeRoomModel.PROPERTY_START.equals(propertyId)) {
				Label nameProperty = new Label(itemId + " - ");
				return (Field<?>) nameProperty;
			} else if (FreeRoomModel.PROPERTY_END.equals(propertyId)) {
				Label nameProperty = new Label(itemId + " " + time);
				return (Field<?>) nameProperty;
			} else if (FreeRoomModel.PROPERTY_LINK.equals(propertyId)) {
				Label nameProperty = new Label(floorplan + " "
						+ new ExternalResource((String) itemId));
				return (Field<?>) nameProperty;
			} else {
				return null;
			}
		}
	}

	private void buildLayout() {
		actualFreeRoomsLabel.setCaption(translator
				.translate(TranslationKeys.CURRENTLY_FREE_ROOMS));
		cssLayout.addComponent(actualFreeRoomsLabel);
		cssLayout.addComponent(freeRoomTable);
		cssLayout.setPrimaryStyleName("freeroom-border");
	}

	@Override
	public void refreshFreeRooms(
			BeanItemContainer<FreeRoomModel> freeRoomContainer) {
		this.freeRoomTable.removeAllItems();

		if (freeRoomContainer.size() != 0) {
			// TODO Check if its already added
			cssLayout.removeComponent(noRoomsLabel);

			for (FreeRoomModel freeRoomItem : freeRoomContainer.getItemIds()) {
				this.freeRoomTable.addItem(freeRoomItem);
			}
		} else {
			cssLayout.addComponent(noRoomsLabel);
		}
	}

	@Override
	public void hideFreeRoomView() {
		this.setVisible(false);
	}

	@Override
	public void showFreeRoomView() {
		this.setVisible(true);
	}

	@Override
	public void updateTranslations() {
		actualFreeRoomsLabel.setCaption(translator
				.translate(TranslationKeys.CURRENTLY_FREE_ROOMS));
		room = translator.translate(TranslationKeys.ROOM);
		time = translator.translate(TranslationKeys.TIME);
		floorplan = translator.translate(TranslationKeys.TO_FLOOR_PLAN);

		// TODO Doesn´t work well
		this.freeRoomTable.markAsDirtyRecursive();
		// refreshFreeRooms(this.freeRoomContainer);
	}
}