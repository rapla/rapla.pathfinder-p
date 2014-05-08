package com.pathfinder.view;

import java.io.File;
import java.util.List;

import org.jsoup.Jsoup;

import com.pathfinder.model.AttributKey;
import com.pathfinder.model.Attribute;
import com.pathfinder.model.Device;
import com.pathfinder.model.ResourceType;
import com.pathfinder.util.properties.ApplicationProperties;
import com.pathfinder.util.properties.ApplicationPropertiesSpec;
import com.pathfinder.util.properties.PropertiesKey;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.CellStyleGenerator;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.Table.ColumnHeaderMode;
import com.vaadin.ui.UI;

/**
 * Defines the detail view for persons, courses, pois or rooms
 * 
 * @author alexh
 * 
 */
public class DetailInfo extends CustomComponent implements DetailInfoSpec {
	private final Object[] visibleColumns = new String[] { Attribute.PROPERTY_LABEL };
	private final Table detailInfoTable = new Table();
	private final BeanItemContainer<Attribute> attributeContainer = new BeanItemContainer<Attribute>(
			Attribute.class);
	private final ApplicationPropertiesSpec properties = ApplicationProperties
			.getInstance();

	private final String IMAGE_PATH = "img/";
	private final String IMAGE_ENDING = ".png";
	private final String RESOURCE_PATH = "/VAADIN/themes/rapla_pathfinder_p/";
	private final String DEFAULT_IMAGE_PERSON = properties
			.getProperty(PropertiesKey.DEFAULT_IMAGE_PERSON);
	private final String DEFAULT_IMAGE_COURSE = properties
			.getProperty(PropertiesKey.DEFAULT_IMAGE_COURSE);
	private final String SERVER_PATH = UI.getCurrent().getSession()
			.getService().getBaseDirectory().getAbsolutePath();

	private final HorizontalLayout layout = new HorizontalLayout();

	private final static String DOTS = "...";

	private final static int MAX_INFO_LENGTH = 25;

	private Device device = Device.UNDEFINED;

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
		detailInfoTable.addGeneratedColumn("htmlvalue", new ColumnGenerator() {
			public Component generateCell(Table source, Object itemId,
					Object columnId) {
				String value = ((Attribute) itemId).getValue();
				Label label = new Label(value, ContentMode.HTML);
				label.setSizeUndefined();
				return label;
			}
		});
		layout.addComponent(detailInfoTable);
	}

	private void buildLayout() {
		layout.setSizeFull();
		layout.setVisible(false);
		layout.addComponent(image);

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
	public void addDetails(BeanItemContainer<Attribute> resourceDetails,
			ResourceType resourceType) {

		List<Attribute> attributeItems = resourceDetails.getItemIds();
		for (Attribute attributeItem : attributeItems) {

			boolean addToTable = false;

			switch (attributeItem.getKey()) {
			case RESOURCE_URL_KEY:
				// skip
				break;
			case PICTURE_NAME_KEY:
				setPicture(attributeItem.getValue(), resourceType);
				break;
			case INFO_KEY:
				String htmlInformation = attributeItem.getValue();
				attributeItem.setValue(removeHtmlAndCut(htmlInformation,
						MAX_INFO_LENGTH));
				attributeItem.setInformation(htmlInformation);
				attributeItem.setPerson(getName(attributeItems));
				addToTable = true;
				break;
			case ROOM_NR_KEY:
				// skip, if ressource is a room
				if (resourceType != ResourceType.ROOM)
					addToTable = true;
				break;
			case EMAIL_KEY:
				if (!device.isStele())
					attributeItem.setValue(addMailTo(attributeItem.getValue()));
				addToTable = true;
				break;
			default:
				addToTable = true;
				break;
			}

			if (addToTable)
				this.detailInfoTable.addItem(attributeItem);

		}

		layout.setExpandRatio(detailInfoTable, 1);
		detailInfoTable.setPageLength(detailInfoTable.getItemIds().size());
		detailInfoTable.setVisible(true);
		layout.setVisible(true);
	}

	private String addMailTo(String mail) {
		String result = mail;
		if (mail != null && mail.length() > 0) {
			result = "<a href=\"mailto:" + mail + "\">" + mail + "</a>";
		}
		return result;
	}

	private String removeHtmlAndCut(String textToCut, int length) {
		String withoutHtml = Jsoup.parse(textToCut).text();
		length = Math.min(withoutHtml.length(), length);
		return withoutHtml.substring(0, length) + DOTS;
	}

	private String getName(List<Attribute> attributeItems) {
		String result = null;
		for (Attribute attribute : attributeItems) {
			if (attribute.getKey() == AttributKey.NAME_KEY) {
				result = attribute.getValue();
				break;
			}
		}
		return result;
	}

	private void setPicture(String name, ResourceType type) {
		boolean showImage = true;
		ThemeResource tr;
		String file = name + IMAGE_ENDING;
		if (fileExists(file)) {
			tr = new ThemeResource(IMAGE_PATH + name + IMAGE_ENDING);
		} else {
			String imageName;
			if (type == ResourceType.PERSON) {
				imageName = DEFAULT_IMAGE_PERSON;
			} else {
				imageName = DEFAULT_IMAGE_COURSE;
			}
			if (fileExists(imageName)) {
				tr = new ThemeResource(IMAGE_PATH + imageName);
			} else {
				tr = null;
				showImage = false;
			}
		}

		if (showImage && tr != null) {
			image.setSource(tr);
			image.setVisible(true);
		} else {
			image.setVisible(false);
		}

		image.markAsDirty();
		image.setWidth(20, Unit.PERCENTAGE);
		layout.setExpandRatio(detailInfoTable, 2);
		layout.setExpandRatio(image, 1);
	}

	private boolean fileExists(String name) {
		File file = new File(SERVER_PATH + RESOURCE_PATH + IMAGE_PATH + name);
		return file.exists();
	}

	@Override
	public void removeDetails() {
		image.setSource(null);
		layout.setExpandRatio(image, 0);
		detailInfoTable.removeAllItems();
		detailInfoTable.setVisible(false);
	}

	@Override
	public void updateTranslations() {
		// Will be blank
	}

	@Override
	public void addInfoTableItemClickListener(ItemClickListener listener) {
		detailInfoTable.addItemClickListener(listener);
	}

	@Override
	public void setDevice(Device device) {
		this.device = device;
	}

	@Override
	public void doCleanup() {
		removeDetails();
		attributeContainer.removeAllItems();

	}
}