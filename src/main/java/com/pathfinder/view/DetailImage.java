package com.pathfinder.view;

import java.io.InputStream;
import java.net.URL;

import com.pathfinder.util.properties.ApplicationProperties;
import com.pathfinder.util.properties.ApplicationPropertiesSpec;
import com.pathfinder.util.properties.PropertiesKey;
import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.server.ExternalResource;
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
	private final ApplicationPropertiesSpec properties = ApplicationProperties
			.getInstance();

	private final String IMAGE_PATH = "img/";
	private final String DEFAULT_IMAGE = IMAGE_PATH
			+ properties.getProperty(PropertiesKey.DEFAULT_IMAGE_NAME);

	public DetailImage() {
		buildLayout();
		addSytling();
	}

	private void buildLayout() {
		this.setSource(new ThemeResource(DEFAULT_IMAGE));
		this.setAlternateText(translator
				.translate(TranslationKeys.NO_DATA_AVAILABLE));
	}

	private void addSytling() {
		this.addStyleName("detailImage");
	}

	@Override
	public void setImage(String stelePrefix, String roomName, String imageUrl) {
		if (!"".equals(imageUrl)) {

			// Add prefix
			String imageUrlWithPrefix = imageUrl.replace(roomName, stelePrefix
					+ roomName);

			// Check if image exists
			boolean urlExists = urlExists(imageUrlWithPrefix);

			if (!urlExists) {
				// Check if image without prefix exists
				urlExists = urlExists(imageUrl);
				if (urlExists) {
					imageUrlWithPrefix = imageUrl;
				}
			}

			if (urlExists) {
				ExternalResource resource = new ExternalResource(
						imageUrlWithPrefix);
				this.setSource(resource);
			} else {
				this.setSource(new ThemeResource(DEFAULT_IMAGE));
			}

			this.setSizeFull();
		}
	}

	private boolean urlExists(String pictureURL) {
		boolean result = false;
		try (InputStream inputStream = new URL(pictureURL).openStream()) {
			result = true;
		} catch (Exception ex) {
		}
		return result;
	}

	@Override
	public void removeImage() {
		this.setSource(new ThemeResource(DEFAULT_IMAGE));
		this.setSizeFull();
	}

	@Override
	public void updateTranslations() {
	}

	@Override
	public void doCleanup() {
	}

}