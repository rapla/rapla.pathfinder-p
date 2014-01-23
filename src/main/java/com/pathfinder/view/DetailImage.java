package com.pathfinder.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pathfinder.util.properties.ApplicationProperties;
import com.pathfinder.util.properties.ApplicationPropertiesSpec;
import com.pathfinder.util.properties.PropertiesKey;
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
	private static final Logger LOGGER = LogManager.getLogger(DetailImage.class
			.getName());
	private final TranslatorSpec translator = Translator.getInstance();
	private final ApplicationPropertiesSpec properties = ApplicationProperties
			.getInstance();

	private final String IMAGE_PATH = "img/";
	private final String IMAGE_ENDING = ".png";
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
		this.setPrimaryStyleName("detail-image");
	}

	@Override
	public void setImage(String imageUrl) {
		if (!"".equals(imageUrl)) {
			this.setSource(new ThemeResource(IMAGE_PATH + imageUrl
					+ IMAGE_ENDING));
			this.setSizeFull();
			
			
			// int code = 0;
			//
			// try {
			// URL u = new URL(imageUrl);
			// HttpURLConnection huc = (HttpURLConnection) u.openConnection();
			// huc.setRequestMethod("GET"); // OR huc.setRequestMethod
			// // ("HEAD");
			// huc.connect();
			// code = huc.getResponseCode();
			//
			// if (code == 200) {
			// this.setSource(new ExternalResource(imageUrl));
			// this.setSizeFull();
			// }
			// } catch (MalformedURLException e) {
			// LOGGER.error("Url was malformed");
			// } catch (IOException e) {
			// LOGGER.error("Problem to load navigation image");
			// }
		}
	}

	@Override
	public void removeImage() {
		this.setSource(new ThemeResource(DEFAULT_IMAGE));
		this.setSizeFull();
	}

	@Override
	public void updateTranslations() {
		// TODO Should this be shown when we show a default image?
		this.setAlternateText(translator
				.translate(TranslationKeys.NO_DATA_AVAILABLE));
	}
}