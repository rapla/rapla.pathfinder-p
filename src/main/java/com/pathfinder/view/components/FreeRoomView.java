package com.pathfinder.view.components;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pathfinder.presenter.DataLoader;
import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;

public class FreeRoomView extends CustomComponent implements FreeRoomViewSpec {
	private final TranslatorSpec translator = Translator.getInstance();
	private static final Logger LOGGER = LogManager.getLogger(DataLoader.class);

	private Label actualFreeRoomsLabel = new Label();

	private CssLayout cssLayout = new CssLayout();
	private GridLayout gridLayout;
	private final Label noRoomsLabel = new Label(
			translator.translate(TranslationKeys.NO_FREE_ROOMS_AVAILABLE));

	private List<String> raumNameList;
	private List<String> raumLinkList;
	private List<String> raumIdList;
	private List<String> startList;
	private List<String> endList;

	private String room = translator.translate(TranslationKeys.ROOM);
	private String time = translator.translate(TranslationKeys.TIME);
	private String floorplan = translator
			.translate(TranslationKeys.TO_FLOOR_PLAN);

	public FreeRoomView() {
		buildLayout();

		setCompositionRoot(cssLayout);
	}

	private void buildLayout() {

		actualFreeRoomsLabel.setCaption(translator
				.translate(TranslationKeys.CURRENTLY_FREE_ROOMS));
		cssLayout.addComponent(actualFreeRoomsLabel);
		cssLayout.setPrimaryStyleName("freeRooms");
	}

	@Override
	public void refreshFreeRooms(List<String> raumNameList,
			List<String> raumLinkList, List<String> raumIdList,
			List<String> startList, List<String> endList) {

		this.raumNameList = raumNameList;
		this.raumLinkList = raumLinkList;
		this.raumIdList = raumIdList;
		this.startList = startList;
		this.endList = endList;

		// Remove old Grid Layout
		if (gridLayout != null)
			cssLayout.removeComponent(gridLayout);

		if (raumNameList.size() != 0) {
			gridLayout = new GridLayout(3, raumNameList.size());

			for (int i = 0; i < raumNameList.size(); i++) {

				Label roomLabel = new Label(room + " " + raumNameList.get(i)
						+ ": ");

				Label roomTime = new Label(startList.get(i) + " - "
						+ endList.get(i) + " " + time);

				Link roomLink = new Link(floorplan, new ExternalResource(
						raumLinkList.get(i)));

				roomLabel.setPrimaryStyleName("roomLabel");
				roomTime.setPrimaryStyleName("roomTime");
				roomLink.setPrimaryStyleName("roomLink");

				gridLayout.addComponent(roomLabel);
				gridLayout.addComponent(roomTime);
				
				gridLayout.addComponent(roomLink);

				cssLayout.addComponent(gridLayout);
			}
		} else {
			LOGGER.debug("No free Rooms - Freiraum online?");

			cssLayout.addComponent(noRoomsLabel);
		}
	}

	@Override
	public void hideFreeRoom() {
		this.setVisible(false);
	}

	@Override
	public void showFreeRoom() {
		this.setVisible(true);
	}

	@Override
	public void updateTranslations() {
		actualFreeRoomsLabel.setCaption(translator
				.translate(TranslationKeys.CURRENTLY_FREE_ROOMS));
		room = translator.translate(TranslationKeys.ROOM);
		time = translator.translate(TranslationKeys.TIME);
		floorplan = translator.translate(TranslationKeys.TO_FLOOR_PLAN);

		refreshFreeRooms(raumNameList, raumLinkList, raumIdList, startList,
				endList);
	}

	@Override
	public Label getFreeRoomLabel() {
		return actualFreeRoomsLabel;
	}
}