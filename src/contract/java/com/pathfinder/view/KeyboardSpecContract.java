/**
 * 
 */
package com.pathfinder.view;

import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;

import com.vaadin.ui.Button.ClickListener;

import de.vksi.c4j.Target;

/**
 * @author tim
 * 
 */
public class KeyboardSpecContract extends ComponentSpecContract implements
		KeyboardSpec {

	@Target
	private KeyboardSpec target;

	@Override
	public void addKeyboardButtonListener(ClickListener listener) {
		if (preCondition()) {
			// assert listener != null : "Listener not null";
			// listenerCountBefore = target.getKeyboardViewListener().size();
		}
		if (postCondition()) {
			// int listenerCountAfter = target.getKeyboardViewListener().size();
			// assert listenerCountAfter == listenerCountBefore + 1 :
			// "One more listener added";
			// listenerCountBefore = -5;
		}
	}

	@Override
	public void hideKeyboard() {

	}

	@Override
	public void showKeyboard() {
	}

}
