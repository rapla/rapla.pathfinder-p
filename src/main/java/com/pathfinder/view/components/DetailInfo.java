package com.pathfinder.view.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import com.pathfinder.PathfinderUI;
import com.pathfinder.model.CourseModel;
import com.pathfinder.model.PersonModel;
import com.pathfinder.model.PoiModel;
import com.pathfinder.model.RoomModel;
import com.pathfinder.presenter.GenericDataLoader;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * Defines the detail view for persons, courses, pois or rooms
 * 
 * @author alexh
 * 
 * @param <T>
 */
public class DetailInfo<T> extends CustomComponent implements DetailInfoSpec {
	// private BeanItem<T> beanItem = null;
	private static final Logger logger = LogManager
			.getLogger(PathfinderUI.class.getName());

	private static RoomModel roomModel;
	private static CourseModel courseModel;
	private static PersonModel personModel;
	private static PoiModel poiModel;

	private final static VerticalLayout verticalLayout = new VerticalLayout();
	private final static HorizontalLayout row1 = new HorizontalLayout();
	private final static HorizontalLayout row2 = new HorizontalLayout();
	private final static HorizontalLayout row3 = new HorizontalLayout();

	private static Label nameLabelLabel;
	private static Label nameValueLabel;
	private static Label roomLabelLabel;
	private static Label roomValueLabel;
	private static Label roomNrLabelLabel;
	private static Label roomNrValueLabel;

	private static Window w;

	private static GenericDataLoader genericDataLoader = new GenericDataLoader();

	public DetailInfo(Object object) {
		if (object instanceof RoomModel) {
			roomModel = (RoomModel) object;
			buildRoomModel();
			logger.trace("Room was clicked - ItemID: "
					+ ((RoomModel) object).getName());
		} else if (object instanceof CourseModel) {
			courseModel = (CourseModel) object;
			buildCourseModel();
			logger.trace("Course was clicked");
		} else if (object instanceof PersonModel) {
			personModel = (PersonModel) object;
			buildPersonModel();
			logger.trace("Person was clicked");
		} else if (object instanceof PoiModel) {
			poiModel = (PoiModel) object;
			buildPointOfInterestModel();
			logger.trace("Poi was clicked");
		} else {
			logger.trace("Unknown item was clicked");
			buildError();
		}
	}

	private void buildError() {
		// TODO Auto-generated method stub

	}

	private void buildPointOfInterestModel() {
		// TODO Auto-generated method stub

	}

	private void buildPersonModel() {
		// TODO Auto-generated method stub

	}

	private void buildCourseModel() {
		// TODO Auto-generated method stub

	}

	private void buildRoomModel() {
		if (verticalLayout != null) {
			row1.removeAllComponents();
			verticalLayout.removeAllComponents();
			UI.getCurrent().removeWindow(w);
		}

		JSONObject roomModelDetails = genericDataLoader
				.getRoomModelDetails(roomModel.getLink());

		String nameLabel = (String) ((JSONObject) roomModelDetails.get("name"))
				.get("label");
		String nameValue = (String) ((JSONObject) roomModelDetails.get("name"))
				.get("value");
		String roomLabel = (String) ((JSONObject) roomModelDetails
				.get("raumart")).get("label");
		String roomValue = (String) ((JSONObject) roomModelDetails
				.get("raumart")).get("value");
		String roomNrLabel = (String) ((JSONObject) roomModelDetails
				.get("raumnr")).get("label");
		String roomNrValue = (String) ((JSONObject) roomModelDetails
				.get("raumnr")).get("value");

		nameLabelLabel = new Label(nameLabel + ": " + nameValue);
		roomLabelLabel = new Label(roomLabel + ": " + roomValue);
		roomNrValueLabel = new Label(roomNrLabel + ": " + roomNrValue);

		verticalLayout.addComponent(nameLabelLabel);
		verticalLayout.addComponent(roomLabelLabel);
		verticalLayout.addComponent(roomNrValueLabel);

		w = new Window();
		w.setContent(verticalLayout);
		UI.getCurrent().addWindow(w);

	}

	@Override
	public void updateTranslations() {
		// TODO Auto-generated method stub
	}
}