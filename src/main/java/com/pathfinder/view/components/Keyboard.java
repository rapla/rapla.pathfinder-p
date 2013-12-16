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

/**
 * Virtual keyboard for user interaction on systems where no hardware keyboard
 * is available
 */
@SuppressWarnings("serial")
public class Keyboard extends CustomComponent implements KeyboardSpec,
		ClickListener {

	private Button deleteButton;
	private Button spaceButton;
	private TranslatorSpec translator = Translator.getInstance();

	public Keyboard() {

		GridLayout gridLayout = new GridLayout(12, 4);

		deleteButton = createButton(
				translator.translate(TranslationKeys.DELETE),
				KeyboardIds.DELETE);
		spaceButton = createButton(translator.translate(TranslationKeys.SPACE),
				KeyboardIds.SPACE);
		spaceButton.setWidth(100, Unit.PERCENTAGE);

		Button leftButton = createButton("<", KeyboardIds.LEFT);
		leftButton.setWidth(100, Unit.PERCENTAGE);
		Button rightButton = createButton(">", KeyboardIds.RIGHT);
		rightButton.setWidth(100, Unit.PERCENTAGE);

		// The captions for the Keyboard in the order they appear on the
		// screen (left to right)
		String[] firstRowCaptions = new String[] { "1", "2", "3", "4", "5",
				"6", "7", "8", "9", "0", "ß" };

		String[] secondRowCaptions = new String[] { "Q", "W", "E", "R", "T",
				"Z", "U", "I", "O", "P", "Ü" };

		String[] thirdRowCaptions = new String[] { "A", "S", "D", "F", "G",
				"H", "J", "K", "L", "Ö", "Ä" };

		String[] fourthRowCaptionsLeftOfSpacebar = new String[] { "Y", "X",
				"C", "V" };
		String[] fourthRowCaptionsRightOfSpacebar = new String[] { "B", "N",
				"M" };

		// Create first keyboard row
		for (String keyCaption : firstRowCaptions) {
			gridLayout.addComponent(createButton(keyCaption));
		}
		gridLayout.addComponent(deleteButton);

		// Create second keyboard row
		for (String keyCaption : secondRowCaptions) {
			gridLayout.addComponent(createButton(keyCaption));
		}
		gridLayout.addComponent(leftButton);

		// Create third keyboard row
		for (String keyCaption : thirdRowCaptions) {
			gridLayout.addComponent(createButton(keyCaption));
		}
		gridLayout.addComponent(rightButton);

		// Create fourth keyboard row
		for (String keyCaption : fourthRowCaptionsLeftOfSpacebar) {
			gridLayout.addComponent(createButton(keyCaption));
		}
		// Add spacebar (taking room of four keys)
		gridLayout.addComponent(spaceButton, 4, 3, 7, 3);
		for (String keyCaption : fourthRowCaptionsRightOfSpacebar) {
			gridLayout.addComponent(createButton(keyCaption));
		}

		setCompositionRoot(gridLayout);

	}

	/**
	 * Creates Button with given caption; Button's id will be set to the same
	 * value as caption; Click listener will be set to instance of this class
	 * 
	 * @param caption
	 *            Caption and Id the Button should get
	 * @return new Button as described above
	 */
	private Button createButton(String caption) {
		return createButton(caption, caption);
	}

	/**
	 * Creates Button with given caption and id (not css id); Click listener
	 * will be set to instance of this class
	 * 
	 * @param caption
	 *            Caption the Button should get
	 * @param id
	 *            the Button should get (not css id)
	 * @return new Button as described above
	 */
	private Button createButton(String caption, Object id) {
		Button newButton = new Button(caption, this);
		newButton.setData(id);
		return newButton;
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
			listener.buttonClick(event.getButton().getData());
	}

	@Override
	public void updateTranslations(Locale locale) {
		deleteButton.setCaption(translator.translate(TranslationKeys.DELETE));
		spaceButton.setCaption(translator.translate(TranslationKeys.SPACE));
	}

}