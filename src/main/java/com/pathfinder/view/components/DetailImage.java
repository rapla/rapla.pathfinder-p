package com.pathfinder.view.components;

import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Image;

/**
 * Image to show in the detail view
 * 
 * @author alexh
 * 
 */
public class DetailImage extends Image implements DetailImageSpec {
	private final TranslatorSpec translator = Translator.getInstance();
	private final String IMAGE_PATH = "img/";
	private final String IMAGE_ENDING = ".png";

	public DetailImage() {
		this.setPrimaryStyleName("detail-image");
		buildLayout();
		addSytling();
	}

	private void buildLayout() {
		this.setAlternateText(translator
				.translate(TranslationKeys.NO_DATA_AVAILABLE));
	}

	private void addSytling() {
		this.setPrimaryStyleName("detail-image");
	}

	@Override
	public void setImage(String imageDescription) {
		this.setSource(new ThemeResource(IMAGE_PATH + imageDescription
				+ IMAGE_ENDING));
		this.setSizeFull();
	}

	@Override
	public void removeImage() {
		this.setSource(null);
	}

	@Override
	public void updateTranslations() {
		this.setAlternateText(translator
				.translate(TranslationKeys.NO_DATA_AVAILABLE));
	}
}