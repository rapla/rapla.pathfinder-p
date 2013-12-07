/**
 * View for the Keyboard
 * 
 * @author Myracle
 * 
 */

package com.pathfinder.view.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.pathfinder.translation.TranslationKeys;
import com.pathfinder.translation.Translator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;

@SuppressWarnings("serial")
public class Keyboard extends CustomComponent implements KeyboardSpec,
		ClickListener {

	private Button deleteButton;
	private Button spaceButton;
	private Translator translator = Translator.getInstance();

	public Keyboard() {

		String caption;
		GridLayout layout = new GridLayout(11, 5);

		// The operations for the Keyboard in the order they appear on the
		// screen (left to right, top to bottom)
		String[] firstRow = new String[] { "1", "2", "3", "4", "5", "6", "7",
				"8", "9", "0", };

		String[] secondRow = new String[] { "Q", "W", "E", "R", "T", "Z", "U",
				"I", "O", "P", "Ü" };

		String[] thirdRow = new String[] { "A", "S", "D", "F", "G", "H", "J",
				"K", "L", "Ö", "Ä" };

		String[] fourthRow = new String[] { "Y", "X", "C", "V", "B", "N", "M" };

		// Add buttons and have them send click events
		// to this class
		for (int i = 0; i < firstRow.length; i++) {
			caption = firstRow[i];
			layout.addComponent(new Button(caption, this), i, 0);
		}

		for (int i = 0; i < secondRow.length; i++) {
			caption = secondRow[i];
			layout.addComponent(new Button(caption, this), i, 1);
		}

		for (int i = 0; i < thirdRow.length; i++) {
			caption = thirdRow[i];
			layout.addComponent(new Button(caption, this), i, 2);
		}

		for (int i = 0; i < fourthRow.length; i++) {
			caption = fourthRow[i];
			layout.addComponent(new Button(caption, this), i, 3);
		}

		deleteButton = new Button(translator.translate(TranslationKeys.DELETE)
				.toUpperCase(), this);
		spaceButton = new Button(translator.translate(TranslationKeys.SPACE)
				.toUpperCase(), this);
		layout.addComponent(deleteButton, 0, 4);
		layout.addComponent(spaceButton, 1, 4);

		setCompositionRoot(layout);

	}

	/* Only the presenter registers one listener... */
	List<KeyboardViewListener> listeners = new ArrayList<KeyboardViewListener>();

	public void addListener(KeyboardViewListener listener) {
		listeners.add(listener);
	}

	/**
	 * Relay button clicks to the presenter with an implementation-independent
	 * event
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		for (KeyboardViewListener listener : listeners)
			listener.buttonClick(event.getButton().getCaption().toString());
	}

	@Override
	public void updateTranslations(Locale locale) {
		deleteButton.setCaption(translator.translate(TranslationKeys.DELETE)
				.toUpperCase());
		spaceButton.setCaption(translator.translate(TranslationKeys.SPACE)
				.toUpperCase());
	}

}