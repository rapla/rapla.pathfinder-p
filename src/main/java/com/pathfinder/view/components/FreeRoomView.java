package com.pathfinder.view.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pathfinder.model.FreeRoomModel;
import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnHeaderMode;
import com.vaadin.ui.TableFieldFactory;

public class FreeRoomView extends CustomComponent implements FreeRoomViewSpec {
	private static final Logger LOGGER = LogManager
			.getLogger(FreeRoomView.class);

	private final TranslatorSpec translator = Translator.getInstance();

	private final Label actualFreeRoomsLabel = new Label();

	private CssLayout cssLayout = new CssLayout();
	private BeanItemContainer<FreeRoomModel> freeRoomContainer = new BeanItemContainer<FreeRoomModel>(
			FreeRoomModel.class);
	private Table freeRoomTable = new Table();
	private final Object[] visibleFreeRoomTableColumns = new String[] {
			FreeRoomModel.PROPERTY_NAME, FreeRoomModel.PROPERTY_START,
			FreeRoomModel.PROPERTY_END, FreeRoomModel.PROPERTY_LINK };

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
		this.freeRoomTable.setImmediate(true);
		this.freeRoomTable.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
		this.freeRoomTable.setVisibleColumns(visibleFreeRoomTableColumns);
		// this.freeRoomTable.setTableFieldFactory(new
		// CustomTableFieldFactory());
		this.freeRoomTable.setPageLength(5);
		this.freeRoomTable
				.setSortContainerPropertyId(FreeRoomModel.PROPERTY_START);
		this.freeRoomTable.setSortAscending(true);
		// this.freeRoomTable.setSelectable(true);
		this.freeRoomTable.setSizeFull();
		// TODO
		// this.freeRoomTable.setPrimaryStyleName("result-table");
	}

	class CustomTableFieldFactory implements TableFieldFactory {
		@Override
		public Field<?> createField(Container container, Object itemId,
				Object propertyId, Component uiContext) {

			if (FreeRoomModel.PROPERTY_NAME.equals(propertyId)) {
				Label nameProperty = new Label(room + " " + itemId);
				return (Field<?>) nameProperty;
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
		cssLayout.setPrimaryStyleName("freeRooms");
	}

	@Override
	public void refreshFreeRooms(
			BeanItemContainer<FreeRoomModel> freeRoomContainer) {

		this.freeRoomContainer.removeAllItems();

		if (freeRoomContainer.size() != 0) {
			cssLayout.removeComponent(noRoomsLabel);

			this.freeRoomContainer = freeRoomContainer;
			this.freeRoomTable.setContainerDataSource(this.freeRoomContainer);

			// roomLabel.setPrimaryStyleName("roomLabel");
			// roomTime.setPrimaryStyleName("roomTime");
			// roomLink.setPrimaryStyleName("roomLink");
		} else {
			LOGGER.debug("No free Rooms");
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

		refreshFreeRooms(this.freeRoomContainer);
	}
}