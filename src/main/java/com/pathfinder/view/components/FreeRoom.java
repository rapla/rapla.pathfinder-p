package com.pathfinder.view.components;

import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

public class FreeRoom extends CustomComponent implements FreeRoomSpec {
	private final TranslatorSpec translator = Translator.getInstance();

	private final VerticalLayout verticalLayout = new VerticalLayout();

	public FreeRoom() {
		buildLayout();
		setCompositionRoot(verticalLayout);
	}

	private void buildLayout() {
		this.setCaption(translator
				.translate(TranslationKeys.CURRENTLY_FREE_ROOMS));
	}

	@Override
	public void refreshFreeRooms() {
		// TODO Auto-generated method stub - verticalLayoutInner
	}

	@Override
	public void updateTranslations() {
		this.setCaption(translator
				.translate(TranslationKeys.CURRENTLY_FREE_ROOMS));
	}
}