/**
 * 
 */
package com.pathfinder.view;

import java.util.List;

import com.pathfinder.model.ResourceModel;
import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;

import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;

/**
 * @author tim
 * 
 */
public class EventSelectionView extends MessageBox implements
		EventSelectionViewSpec {

	private ClickListener buttonClickListener;
	private final TranslatorSpec translator = Translator.getInstance();
	private final String startTitle = translator
			.translate(TranslationKeys.SELECT_RESOURCE);
	private final Button cancelButton = new Button(
			translator.translate(TranslationKeys.BACK));

	public EventSelectionView() {
		super(RESOURCE_FACTORY.getIcon(Icon.NONE), "", new VerticalLayout(),
				null);
		cancelButton.setPrimaryStyleName("pop-up-go-back-button");
		cancelButton.addClickListener(new ButtonClickedListener());
		title = startTitle;
	}

	private void buildLayout(List<ResourceModel> resources) {
		VerticalLayout layout = (VerticalLayout) messageComponent;
		layout.removeAllComponents();

		for (ResourceModel resource : resources) {
			Button newButton = new Button(resource.getName());
			newButton.setData(resource);
			newButton.setPrimaryStyleName("pop-up-button");
			newButton.addClickListener(buttonClickListener);
			newButton.addClickListener(new ButtonClickedListener());
			layout.addComponent(newButton);
		}

		layout.addComponent(cancelButton);

	}

	@Override
	public void showEventResourceSelection(List<ResourceModel> resources) {

		buildLayout(resources);
		open();
	}

	@Override
	public void addButtonClickListener(ClickListener listener) {
		buttonClickListener = listener;
	}

	private class ButtonClickedListener implements ClickListener {

		@Override
		public void buttonClick(ClickEvent event) {
			close();
		}

	}

	@Override
	public void updateTranslations() {
		title = translator.translate(TranslationKeys.SELECT_RESOURCE);
		cancelButton.setCaption(translator.translate(TranslationKeys.CLOSE));
	}

	@Override
	public void doCleanup() {
	}

}
