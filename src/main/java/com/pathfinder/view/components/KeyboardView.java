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
import com.pathfinder.translation.TranslatorSpec;
import com.pathfinder.view.listener.KeyboardViewListenerSpec;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;

@SuppressWarnings("serial")
public class KeyboardView extends CustomComponent implements KeyboardSpec,
		ClickListener {

	private Button deleteButton;
	private Button spaceButton;
	private Button wildCardButton;
	private TranslatorSpec translator = Translator.getInstance();

	public KeyboardView() {
		String caption;
		GridLayout layout = new GridLayout(12, 4);

		deleteButton = new Button(translator.translate(TranslationKeys.DELETE)
				.toUpperCase(), this);
		spaceButton = new Button(translator.translate(TranslationKeys.SPACE)
				.toUpperCase(), this);

		// The operations for the Keyboard in the order they appear on the
		// screen (left to right, top to bottom)
		String[] firstRow = new String[] { "1", "2", "3", "4", "5", "6", "7",
				"8", "9", "0",
				translator.translate(TranslationKeys.DELETE).toUpperCase() };

		String[] secondRow = new String[] { "Q", "W", "E", "R", "T", "Z", "U",
				"I", "O", "P", "Ü", "<" };

		String[] thirdRow = new String[] { "A", "S", "D", "F", "G", "H", "J",
				"K", "L", "Ö", "Ä", ">" };

		String[] fourthRow = new String[] { "Y", "X", "C", "V",
				translator.translate(TranslationKeys.SPACE).toUpperCase(), "B",
				"N", "M" };

		// Add buttons and have them send click events
		// to this class
		for (int i = 0; i < firstRow.length; i++) {
			caption = firstRow[i];
			wildCardButton = new Button(caption, this);

			if (!caption.equals(translator.translate(TranslationKeys.DELETE)
					.toUpperCase())) {
				wildCardButton.setId(caption);
				layout.addComponent(wildCardButton, i, 0);
			} else {
				deleteButton.setId("DELETE");
				layout.addComponent(deleteButton, i, 0);
			}
		}

		for (int i = 0; i < secondRow.length; i++) {
			caption = secondRow[i];
			wildCardButton = new Button(caption, this);
			wildCardButton.setId(caption);
			layout.addComponent(wildCardButton, i, 1);
		}

		for (int i = 0; i < thirdRow.length; i++) {
			caption = thirdRow[i];
			wildCardButton = new Button(caption, this);
			wildCardButton.setId(caption);
			layout.addComponent(wildCardButton, i, 2);
		}

		for (int i = 0; i < fourthRow.length; i++) {
			caption = fourthRow[i];
			wildCardButton = new Button(caption, this);
			if (!caption.equals(translator.translate(TranslationKeys.SPACE)
					.toUpperCase())) {
				wildCardButton.setId(caption);
				layout.addComponent(wildCardButton, i, 3);
			} else {
				spaceButton.setId("SPACE");
				layout.addComponent(spaceButton, i, 3);
			}
		}

		setCompositionRoot(layout);

	}

	/* Only the presenter registers one listener... */
	List<KeyboardViewListenerSpec> listeners = new ArrayList<KeyboardViewListenerSpec>();

	public void addListener(KeyboardViewListenerSpec listener) {
		listeners.add(listener);
	}

	/**
	 * Relay button clicks to the presenter with an implementation-independent
	 * event
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		for (KeyboardViewListenerSpec listener : listeners)
			listener.buttonClick(event.getButton().getId());
	}

	@Override
	public void updateTranslations(Locale locale) {
		deleteButton.setCaption(translator.translate(TranslationKeys.DELETE)
				.toUpperCase());
		spaceButton.setCaption(translator.translate(TranslationKeys.SPACE)
				.toUpperCase());
	}

}