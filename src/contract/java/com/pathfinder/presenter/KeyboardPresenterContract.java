package com.pathfinder.presenter;

import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;

import com.pathfinder.model.KeyboardModel;
import com.pathfinder.view.components.Keyboard;

import de.vksi.c4j.ClassInvariant;
import de.vksi.c4j.Target;

public class KeyboardPresenterContract implements KeyboardPresenterSpec {

	@Target
	private SearchPanelPresenter target;

	@ClassInvariant
	public void classInvariant() {
		// TODO: write invariants if required
	}

	public KeyboardPresenterContract(KeyboardModel model, Keyboard view) {
		if (preCondition()) {
			// TODO: write preconditions if required
		}
		if (postCondition()) {
			// TODO: write postconditions if required
		}
	}

	@Override
	public void addKeybordKeyToSearchString(String key) {
		if (preCondition()) {
			// TODO: write preconditions if required
		}
		if (postCondition()) {
			// TODO: write postconditions if required
		}
	}

	@Override
	public void deleteKeyFromSearchString() {
		if (preCondition()) {
			// TODO: write preconditions if required
		}
		if (postCondition()) {
			// TODO: write postconditions if required
		}
	}

	@Override
	public void clearSearchString() {
		if (preCondition()) {
			// TODO: write preconditions if required
		}
		if (postCondition()) {
			// TODO: write postconditions if required
		}
	}

}
