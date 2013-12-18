/**
 * View for the Keyboard
 * 
 * @author Myracle
 * 
 */

package com.pathfinder.view.components;

import static com.pathfinder.view.components.KeyboardId.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.pathfinder.translation.TranslatorSpec;
import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.view.listener.KeyboardViewListenerSpec;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class Keyboard extends CustomComponent implements KeyboardSpec,
		ClickListener {

	private Button deleteButton;
	private Button spaceButton;
	private TranslatorSpec translator = Translator.getInstance();

	public Keyboard() {
		VerticalLayout layout = new VerticalLayout();
		HorizontalLayout row1 = new HorizontalLayout();
		HorizontalLayout row2 = new HorizontalLayout();
		HorizontalLayout row3 = new HorizontalLayout();
		HorizontalLayout row4 = new HorizontalLayout();

		// The operations for the Keyboard in the order they appear on the
		// screen (left to right, top to bottom)
		KeyboardId[] firstRow = new KeyboardId[] { ONE, TWO, THREE, FOUR, FIVE,
				SIX, SEVEN, EIGHT, NINE, ZERO };
		KeyboardId[] secondRow = new KeyboardId[] { Q, W, E, R, T, Z, U, I, O,
				P, UE, LEFT };
		KeyboardId[] thirdRow = new KeyboardId[] { A, S, D, F, G, H, J, K, L,
				OE, AE, RIGHT };
		KeyboardId[] fourthRow = new KeyboardId[] { Y, X, C, V, B, N, M };

		deleteButton = createButton(DELETE);
		spaceButton = createButton(SPACE);

		// Add buttons and have them send click events
		// to this class
		for (KeyboardId id : firstRow) {
			row1.addComponent(createButton(id));
		}
		row1.addComponent(deleteButton);

		for (KeyboardId id : secondRow) {
			row2.addComponent(createButton(id));
		}

		for (KeyboardId id : thirdRow) {
			row3.addComponent(createButton(id));
		}

		for (KeyboardId id : fourthRow) {
			row4.addComponent(createButton(id));
		}
		row4.addComponent(spaceButton, 4);

		layout.addComponent(row1);
		layout.addComponent(row2);
		layout.addComponent(row3);
		layout.addComponent(row4);

		setCompositionRoot(layout);

	}

	/* Only the presenter registers one listener... */
	List<KeyboardViewListenerSpec> listeners = new ArrayList<KeyboardViewListenerSpec>();

	public void addListener(KeyboardViewListenerSpec listener) {
		listeners.add(listener);
	}

	/**
	 * Creates a new Button with label as specified in id and this class as
	 * listener
	 * 
	 * @param id
	 *            Keyboard ID with label
	 * @return New Button as specified above
	 */
	private Button createButton(KeyboardId id) {
		Button newButton = new Button();
		newButton.setData(id);
		newButton.addClickListener(this);

		switch (id) {
		case SPACE:
			newButton.setStyleName("keyboard-space");
			newButton.setCaption(translator.translate(TranslationKeys.SPACE));
			break;
		case DELETE:
			newButton.setStyleName("keyboard-delete");
			newButton.setCaption(translator.translate(TranslationKeys.DELETE));
			break;
		default:
			newButton.setStyleName("keyboard-button");
			newButton.setCaption(id.getLabel());
			break;
		}

		return newButton;
	}

	/**
	 * Relay button clicks to the presenter with an implementation-independent
	 * event
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		for (KeyboardViewListenerSpec listener : listeners)
			listener.buttonClick((KeyboardId) event.getButton().getData());
	}

	@Override
	public void updateTranslations(Locale locale) {
		deleteButton.setCaption(translator.translate(TranslationKeys.DELETE));
		spaceButton.setCaption(translator.translate(TranslationKeys.SPACE));
	}

}