package com.pathfinder.view;

import com.pathfinder.view.KeyboardSpecContract;
import com.vaadin.ui.Button.ClickListener;

import de.vksi.c4j.ContractReference;

@ContractReference(KeyboardSpecContract.class)
public interface KeyboardSpec extends ComponentSpec {
	void addKeyboardButtonListener(ClickListener listener);

	void hideKeyboard();

	void showKeyboard();
}
