/**
 * This is the presenter from the Keyboard. 
 * 
 * @author Myracle, Alex
 */

package com.pathfinder.presenter;

import com.pathfinder.model.KeyboardModel;
import com.pathfinder.view.components.ClearButton;
import com.pathfinder.view.components.Keyboard;
import com.pathfinder.view.components.SearchField;
import com.pathfinder.view.components.TreeStructure;
import com.pathfinder.view.container.SearchPanel;
import com.pathfinder.view.listener.ButtonClearListenerSpec;
import com.pathfinder.view.listener.KeyboardViewListenerSpec;
import com.vaadin.data.fieldgroup.BeanFieldGroup;

public class SearchPanelPresenter implements KeyboardViewListenerSpec,
		ButtonClearListenerSpec, SearchPanelPresenterSpec {

	private final KeyboardModel keyboardModel = new KeyboardModel();
	private final TreeStructure treeStructure = new TreeStructure();
	private final Keyboard keyboard = new Keyboard();
	private final SearchField searchField = new SearchField();
	private final ClearButton clearButton = new ClearButton();
	private final SearchPanel searchPanel = new SearchPanel(treeStructure,
			keyboard, searchField, clearButton);
	private final BeanFieldGroup<KeyboardModel> binder = new BeanFieldGroup<KeyboardModel>(
			KeyboardModel.class);

	private String searchString;
	private int changePosCounter;

	public SearchPanelPresenter() {
		this.keyboard.addListener(this);
		this.refreshItemDataSource();
		this.binder.bind(searchField, KeyboardModel.PROPERTY_SEARCHSTRING);
	}

	// Clear ButtonListener
	@Override
	public void buttonClick() {
		setChangePosCounter(0);
		this.getKeyboardModel().setSearchString("");
		this.refreshItemDataSource();
	}

	// Keyboard ClickListener
	@Override
	public void buttonClick(String key) {
		if (key.equals("DELETE")) {
			deleteKeyFromSearchString();
		} else if (key.equals("SPACE")) {
			addKeybordKeyToSearchString(" ");
		} else if (key.equals("<")) {
			if (getSearchString().length() + getChangePosCounter() > 0) {
				setChangePosCounter(getChangePosCounter() - 1);
			}
		} else if (key.equals(">")) {
			if (getChangePosCounter() < 0) {
				setChangePosCounter(getChangePosCounter() + 1);
			}
		} else {
			addKeybordKeyToSearchString(key);

		}
	}

	public void addKeybordKeyToSearchString(String key) {
		changePosCounter = getChangePosCounter();
		searchString = keyboardModel.getSearchString();
		if (changePosCounter < 0) {
			int positonCounter = searchString.length() + changePosCounter;

			searchString = searchString.substring(0, positonCounter)
					+ key
					+ searchString.substring(positonCounter,
							searchString.length());
		} else {
			searchString += key;
		}

		keyboardModel.setSearchString(searchString);
		this.refreshItemDataSource();
	}

	public void deleteKeyFromSearchString() {
		searchString = keyboardModel.getSearchString();
		int positonCounter = searchString.length() + changePosCounter;

		if (searchString.length() > 0) {
			if (positonCounter != 0) {
				searchString = searchString.substring(0, positonCounter - 1)
						+ searchString.substring(positonCounter,
								searchString.length());
			}
		}

		keyboardModel.setSearchString(searchString);
		this.refreshItemDataSource();
	}

	public void clearSearchString() {
		keyboardModel.setSearchString("");
		this.refreshItemDataSource();
	}

	private void refreshItemDataSource() {
		this.binder.setItemDataSource(keyboardModel);
	}

	public SearchPanel getSearchPanel() {
		return this.searchPanel;
	}

	public KeyboardModel getKeyboardModel() {
		return this.keyboardModel;
	}

	public Keyboard getKeyboard() {
		return this.keyboard;
	}

	public int getChangePosCounter() {
		return changePosCounter;
	}

	public void setChangePosCounter(int changePosCounter) {
		this.changePosCounter = changePosCounter;
	}

	@Override
	public String getSearchString() {
		return this.keyboardModel.getSearchString();
	}

}
