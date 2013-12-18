package com.pathfinder.view.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.pathfinder.translation.TranslatorSpec;
import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.view.listener.FreeRoomViewListenerSpec;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class FreeRoom extends CustomComponent implements FreeRoomSpec {
	private TranslatorSpec translator = Translator.getInstance();
	private final Label description = new Label();

	private final VerticalLayout verticalLayoutOuter = new VerticalLayout();
	private final VerticalLayout verticalLayoutInner = new VerticalLayout();

	private List<FreeRoomViewListenerSpec> listener = new ArrayList<FreeRoomViewListenerSpec>();

	public FreeRoom() {
		buildLayout();
		setCompositionRoot(verticalLayoutOuter);
	}

	@Override
	public void buildLayout() {
		description.setCaption(translator
				.translate(TranslationKeys.CURRENTLY_FREE_ROOMS) + ":");
		verticalLayoutOuter.addComponent(description);
		verticalLayoutOuter.addComponent(verticalLayoutInner);
	}

	@Override
	public void refreshFreeRooms() {
		// TODO Auto-generated method stub - verticalLayoutInner
	}

	@Override
	public void updateTranslations(Locale locale) {
		description.setCaption(translator
				.translate(TranslationKeys.CURRENTLY_FREE_ROOMS) + ":");
	}

	@Override
	public void addFreeRoomListener(FreeRoomViewListenerSpec listener) {
		this.listener.add(listener);
	}

}
