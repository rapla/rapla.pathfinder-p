/**
 * This is the presenter from the Keyboard. 
 * 
 * @author Myracle
 */

package com.pathfinder.presenter;

import com.pathfinder.model.KeyboardModel;
import com.pathfinder.view.components.KeyboardView;
import com.pathfinder.view.components.KeyboardViewSpec;

import de.vksi.c4j.ContractReference;

@ContractReference(KeyboardPresenterContract.class)
public class KeyboardPresenter implements
		KeyboardViewSpec.KeyboardViewListener, KeyboardPresenterSpec {

	KeyboardModel model;
	KeyboardView view;
	String searchString;

	public KeyboardPresenter(KeyboardModel model, KeyboardView view) {
		this.model = model;
		this.view = view;

		view.addListener(this);
	}

	@Override
	public void buttonClick(String key) {
		if (!key.equals("DELETE")) {
			addKeybordKeyToSearchString(key);
			System.out.println(model.getSearchString());
		} else {
			deleteKeyFromSearchString();
			System.out.println(model.getSearchString());
		}
	}

	public void addKeybordKeyToSearchString(String key) {
		searchString = model.getSearchString();
		searchString += key;
		model.setSearchString(searchString);
	}

	public void deleteKeyFromSearchString() {
		searchString = model.getSearchString();

		if (searchString.length() > 0) {
			searchString = searchString.substring(0, searchString.length() - 1);
		}

		model.setSearchString(searchString);
	}

	public void clearSearchString() {
		model.setSearchString("");
	}

}