package com.pathfinder.presenter;

import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;

import com.pathfinder.model.KeyboardModel;
import com.pathfinder.view.components.KeyboardView;

import de.vksi.c4j.ClassInvariant;
import de.vksi.c4j.Target;

public class KeyboardPresenterContract extends KeyboardPresenter {

	@Target
	private KeyboardPresenter target;

	@ClassInvariant
	public void classInvariant() {
		// TODO: write invariants if required
	}

	public KeyboardPresenterContract(KeyboardModel model, KeyboardView view) {
		super(model, view);
		if (preCondition()) {
			// TODO: write preconditions if required
		}
		if (postCondition()) {
			// TODO: write postconditions if required
		}
	}

	@Override
	public void buttonClick(String key) {
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
