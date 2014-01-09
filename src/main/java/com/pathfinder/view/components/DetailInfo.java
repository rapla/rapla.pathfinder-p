package com.pathfinder.view.components;

import java.util.Iterator;

import org.json.simple.JSONArray;

import com.pathfinder.model.Attribut;
import com.pathfinder.model.ResourceModel;
import com.pathfinder.presenter.GenericDataLoader;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Window;

/**
 * Defines the detail view for persons, courses, pois or rooms
 * 
 * @author alexh
 * 
 * @param <T>
 */
public class DetailInfo<T> extends CustomComponent implements DetailInfoSpec {
	private JSONArray modelDetails = null;

	private static Window w;

	private static GenericDataLoader genericDataLoader = new GenericDataLoader();

	public DetailInfo(ResourceModel resourceModel) {

		buildDetailInfo(resourceModel);

	}

	private void buildDetailInfo(ResourceModel resourceModel) {

		modelDetails = genericDataLoader.getModelDetails(resourceModel
				.getLink());

		Iterator<String> keys = modelDetails.iterator();

		while (keys.hasNext()) {

			Attribut attributObject = new Attribut();

			attributObject.setLabel("");
			attributObject.setValue("");

		}

		// Generic Build of the DetailInfo

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

	// private void buildRoomModel() {
	// if (verticalLayout != null) {
	// row1.removeAllComponents();
	// verticalLayout.removeAllComponents();
	// UI.getCurrent().removeWindow(w);
	// }
	//
	// JSONObject roomModelDetails = genericDataLoader
	// .getModelDetails(roomModel.getLink());
	//
	// String nameLabel = (String) ((JSONObject) roomModelDetails.get("name"))
	// .get("label");
	// String nameValue = (String) ((JSONObject) roomModelDetails.get("name"))
	// .get("value");
	// String roomLabel = (String) ((JSONObject) roomModelDetails
	// .get("raumart")).get("label");
	// String roomValue = (String) ((JSONObject) roomModelDetails
	// .get("raumart")).get("value");
	// String roomNrLabel = (String) ((JSONObject) roomModelDetails
	// .get("raumnr")).get("label");
	// String roomNrValue = (String) ((JSONObject) roomModelDetails
	// .get("raumnr")).get("value");
	//
	// nameLabelLabel = new Label(nameLabel + ": " + nameValue);
	// roomLabelLabel = new Label(roomLabel + ": " + roomValue);
	// roomNrValueLabel = new Label(roomNrLabel + ": " + roomNrValue);
	//
	// verticalLayout.addComponent(nameLabelLabel);
	// verticalLayout.addComponent(roomLabelLabel);
	// verticalLayout.addComponent(roomNrValueLabel);
	//
	// w = new Window();
	// w.setContent(verticalLayout);
	// UI.getCurrent().addWindow(w);
	//
	// }

	@Override
	public void updateTranslations() {
		// TODO Auto-generated method stub
	}
}