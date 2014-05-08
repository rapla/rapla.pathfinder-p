package com.pathfinder.view;

import java.io.File;

import com.pathfinder.util.properties.ApplicationProperties;
import com.pathfinder.util.properties.ApplicationPropertiesSpec;
import com.pathfinder.util.properties.PropertiesKey;
import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Image;
import com.vaadin.ui.UI;

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
		this.addStyleName("detailImage");
	}

	@Override
	public void setImage(String imageUrl) {
		if (!"".equals(imageUrl)) {

			// Check if file exists
			String serverpath = UI.getCurrent().getSession().getService()
					.getBaseDirectory().getAbsolutePath();
			String path = "" + IMAGE_PATH + imageUrl + IMAGE_ENDING;

			File file = new File(serverpath + File.separator + "VAADIN"
					+ File.separator + "themes" + File.separator
					+ "rapla_pathfinder_p" + File.separator + path);

			if (file.exists()) {
				ThemeResource themeResource = new ThemeResource(IMAGE_PATH
						+ imageUrl + IMAGE_ENDING);
				this.setSource(themeResource);
			} else {
				this.setSource(new ThemeResource(DEFAULT_IMAGE));
			}

			this.setSizeFull();
		}
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