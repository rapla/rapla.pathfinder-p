package com.pathfinder.view.components;

import java.util.List;

import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;

public class FreeRoomView extends CustomComponent implements FreeRoomViewSpec {
	private final TranslatorSpec translator = Translator.getInstance();

	private HorizontalLayout horizontalLayout;
	private final VerticalLayout verticalLayout = new VerticalLayout();

	public FreeRoomView() {
		buildLayout();
		setCompositionRoot(verticalLayout);
	}

	private void buildLayout() {
		this.setCaption(translator
				.translate(TranslationKeys.CURRENTLY_FREE_ROOMS));
	}

	@Override
	public void refreshFreeRooms(List<String> raumNameList,
			List<String> raumLinkList, List<String> raumIdList,
			List<String> startList, List<String> endList) {

		for (int i = 0; i < raumNameList.size(); i++) {

			horizontalLayout = new HorizontalLayout();

			horizontalLayout.addComponent(new Label("Raum "
					+ raumNameList.get(i) + " bis " + endList.get(i) + "Uhr"));

			horizontalLayout.addComponent(new Link("zum Raumplan",
					new ExternalResource(raumLinkList.get(i))));

			verticalLayout.addComponent(horizontalLayout);

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