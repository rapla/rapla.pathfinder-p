/**
 * This is the presenter from the Keyboard. 
 * 
 * @author Myracle, Alex
 */

package com.pathfinder.presenter;

import com.pathfinder.model.KeyboardModel;
import com.pathfinder.view.components.Keyboard;
import com.pathfinder.view.components.SearchField;
import com.pathfinder.view.components.TreeStructure;
import com.pathfinder.view.container.SearchPanel;
import com.pathfinder.view.listener.KeyboardViewListenerSpec;
import com.vaadin.data.fieldgroup.BeanFieldGroup;

import de.vksi.c4j.ContractReference;

@ContractReference(KeyboardPresenterContract.class)
public class SearchPanelPresenter implements KeyboardViewListenerSpec,
		SearchPanelPresenterSpec {

	private final KeyboardModel keyboard = new KeyboardModel();
	private final TreeStructure treeStructure = new TreeStructure();
	private final Keyboard keyboardView = new Keyboard();
	private final SearchField searchField = new SearchField();
	private final SearchPanel searchPanel = new SearchPanel(treeStructure,
			keyboardView, searchField);
	private final BeanFieldGroup<KeyboardModel> binder = new BeanFieldGroup<KeyboardModel>(
			KeyboardModel.class);

	private String searchString;
	private int changePosCounter;

	public SearchPanelPresenter() {
		this.keyboardView.addListener(this);
		this.refreshItemDataSource();
		this.binder.bind(searchField, KeyboardModel.PROPERTY_SEARCHSTRING);
	}

	@Override
	public void buttonClick(String key) {
		if (key.equals("DELETE")) {
			deleteKeyFromSearchString();
		} else if (key.equals("SPACE")) {
			addKeybordKeyToSearchString(" ");
		} else if (key.equals("<")) {
			setChangePosCounter(getChangePosCounter() - 1);
		} else if (key.equals(">")) {
			if (getChangePosCounter() < 0) {
				setChangePosCounter(getChangePosCounter() + 1);
			}
		} else {
			addKeybordKeyToSearchString(key);
		}
		
		treeStructure.addFilters(keyboard.getSearchString());
	}

	public void addKeybordKeyToSearchString(String key) {
		changePosCounter = getChangePosCounter();
		searchString = keyboard.getSearchString();
		if (changePosCounter < 0) {
			int positonCounter = searchString.length() + changePosCounter;

			searchString = searchString.substring(0, positonCounter)
					+ key
					+ searchString.substring(positonCounter,
							searchString.length());
		} else {
			searchString += key;
		}

		keyboard.setSearchString(searchString);
		this.refreshItemDataSource();
	}

	public void deleteKeyFromSearchString() {
		searchString = keyboard.getSearchString();

		if (searchString.length() > 0) {
			searchString = searchString.substring(0, searchString.length() - 1);
		}

		keyboard.setSearchString(searchString);
		this.refreshItemDataSource();
	}

	public void clearSearchString() {
		keyboard.setSearchString("");
		this.refreshItemDataSource();
	}

	private void refreshItemDataSource() {
		this.binder.setItemDataSource(keyboard);
	}

	public SearchPanel getSearchPanel() {
		return this.searchPanel;
	}

	public KeyboardModel getKeyboardModel() {
		return this.keyboard;
	}

	// TODO Why? Is this necessary?
	public Keyboard getKeyboard() {
		return this.keyboardView;
	}

	public int getChangePosCounter() {
		return changePosCounter;
	}

	public void setChangePosCounter(int changePosCounter) {
		this.changePosCounter = changePosCounter;
	}
}
