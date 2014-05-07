/**
 * 
 */
package com.pathfinder.view;

import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;

/**
 * @author tim
 * 
 */
public class PersonInformationView extends MessageBox implements
		PersonInformationViewSpec {

	private String person;

	private final TranslatorSpec translator = Translator.getInstance();
	private final Button closeButton = new Button(
			translator.translate(TranslationKeys.CLOSE));

	public PersonInformationView() {
		super(RESOURCE_FACTORY.getIcon(Icon.NONE), "", new VerticalLayout(),
				null);
		closeButton.addClickListener(new ButtonClickedListener());
	}

	private void buildLayout(String information) {
		title = translator.translate(TranslationKeys.INFORMATION_ABOUT) + " "
				+ person;

		VerticalLayout layout = (VerticalLayout) messageComponent;
		layout.removeAllComponents();

		layout.addComponent(new Label(information, ContentMode.HTML));
		layout.addComponent(closeButton);

	}

	private class ButtonClickedListener implements ClickListener {

		@Override
		public void buttonClick(ClickEvent event) {
			close();
		}

	}

	@Override
	public void updateTranslations() {
		title = translator.translate(TranslationKeys.INFORMATION_ABOUT) + " "
				+ person;
		closeButton.setCaption(translator.translate(TranslationKeys.CLOSE));
	}

	@Override
	public void showInformation(String person, String information) {
		if (person != null && information != null) {
			this.person = person;
			buildLayout(information);
			open();
		}
	}

}
