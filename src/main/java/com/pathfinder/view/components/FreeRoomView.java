package com.pathfinder.view.components;

import com.pathfinder.model.FreeRoomModel;
import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.CellStyleGenerator;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.Table.ColumnHeaderMode;

/**
 * Layout for the FreeRoomView
 * 
 * @author alexh
 * 
 */
public class FreeRoomView extends CustomComponent implements FreeRoomViewSpec {
	private final TranslatorSpec translator = Translator.getInstance();

	private final CssLayout cssLayout = new CssLayout();
	private final Label actualFreeRoomsLabel = new Label();
	private final BeanItemContainer<FreeRoomModel> freeRoomContainer = new BeanItemContainer<FreeRoomModel>(
			FreeRoomModel.class);
	private final Object[] visibleFreeRoomTableColumns = new String[] {
			FreeRoomModel.PROPERTY_NAME, FreeRoomModel.PROPERTY_END };
	private String[] headerFreeRoomTableCaptions = new String[] {
			translator.translate(TranslationKeys.ROOM),
			translator.translate(TranslationKeys.FREE_TILL) };
	private final Table freeRoomTable = new Table();
	private final Label noRoomsLabel = new Label(
			translator.translate(TranslationKeys.NO_FREE_ROOMS_AVAILABLE));

	public FreeRoomView() {
		this.createTable();
		this.buildLayout();
		this.addStyling();
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
		this.freeRoomTable.setSortEnabled(false);
		this.freeRoomTable.setWidth(100, Unit.PERCENTAGE);
		this.freeRoomTable
				.setCellStyleGenerator(new CustomCellStyleGenerator());
		this.freeRoomTable.setSizeFull();
	}

	class LabelColumnGenerator implements ColumnGenerator {
		@Override
		public Component generateCell(Table source, Object itemId,
				Object columnId) {
			// Get the object stored in the cell as a property
			Property<?> prop = source.getItem(itemId).getItemProperty(columnId);

			if (FreeRoomModel.PROPERTY_NAME.equals(columnId)) {
				Label label = new Label((String) prop.getValue());
				return label;
			} else if (FreeRoomModel.PROPERTY_END.equals(columnId)) {
				Label label = new Label(prop.getValue() + " "
						+ translator.translate(TranslationKeys.TIME));
				return label;
			} else {
				return null;
			}
		}
	}

	class CustomCellStyleGenerator implements CellStyleGenerator {
		@Override
		public String getStyle(Table source, Object itemId, Object propertyId) {
			return "result-row";
		}
	}

	private void buildLayout() {
		actualFreeRoomsLabel.setCaption(translator
				.translate(TranslationKeys.CURRENTLY_FREE_ROOMS));
		cssLayout.addComponent(actualFreeRoomsLabel);
		cssLayout.addComponent(freeRoomTable);
		this.setSizeFull();
		cssLayout.setSizeFull();
	}

	private void addStyling() {
		cssLayout.setPrimaryStyleName("freeroom");
		actualFreeRoomsLabel.addStyleName("big-caption");
		freeRoomTable.addStyleName("global-table");
	}

	@Override
	public void refreshFreeRooms(
			BeanItemContainer<FreeRoomModel> freeRoomContainer) {
		this.freeRoomTable.removeAllItems();

		if (freeRoomContainer != null && freeRoomContainer.size() != 0) {
			cssLayout.removeComponent(noRoomsLabel);
			cssLayout.addComponent(freeRoomTable);

			for (FreeRoomModel freeRoomItem : freeRoomContainer.getItemIds()) {
				this.freeRoomTable.addItem(freeRoomItem);
			}
		} else {
			cssLayout.removeComponent(freeRoomTable);
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
		headerFreeRoomTableCaptions = new String[] {
				translator.translate(TranslationKeys.ROOM),
				translator.translate(TranslationKeys.FREE_TILL) };
		freeRoomTable.setColumnHeaders(headerFreeRoomTableCaptions);
		this.freeRoomTable.markAsDirtyRecursive();
		actualFreeRoomsLabel.setCaption(translator
				.translate(TranslationKeys.CURRENTLY_FREE_ROOMS));
	}
}