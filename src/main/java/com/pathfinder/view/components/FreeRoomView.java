package com.pathfinder.view.components;

import java.util.List;

import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;

public class FreeRoomView extends CustomComponent implements FreeRoomViewSpec {
	private final TranslatorSpec translator = Translator.getInstance();

	private GridLayout gridLayout;

	public FreeRoomView() {
		buildLayout();

		this.setCaption(translator
				.translate(TranslationKeys.CURRENTLY_FREE_ROOMS));

		setCompositionRoot(gridLayout);
	}

	private void buildLayout() {
		this.setPrimaryStyleName("freeRooms");
	}

	@Override
	public void refreshFreeRooms(List<String> raumNameList,
			List<String> raumLinkList, List<String> raumIdList,
			List<String> startList, List<String> endList) {

		gridLayout = new GridLayout(2, raumNameList.size());

		for (int i = 0; i < raumNameList.size(); i++) {

			Label roomLabel = new Label("Raum " + raumNameList.get(i) + " bis "
					+ startList.get(i) + "Uhr");

			Link roomLink = new Link("zum Raumplan", new ExternalResource(
					raumLinkList.get(i)));

			roomLabel.setPrimaryStyleName("roomLabel");
			roomLink.setPrimaryStyleName("roomLink");

			gridLayout.addComponent(roomLabel);
			gridLayout.addComponent(roomLink);

			setCompositionRoot(gridLayout);
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
		this.setCaption(translator
				.translate(TranslationKeys.CURRENTLY_FREE_ROOMS));
	}
}