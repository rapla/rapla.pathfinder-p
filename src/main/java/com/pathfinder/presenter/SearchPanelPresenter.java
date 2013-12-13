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

public class SearchPanelPresenter implements KeyboardViewListenerSpec,
		SearchPanelPresenterSpec {

	private final TreeStructure treeStructure = new TreeStructure();
	private final Keyboard keyboard = new Keyboard();
	private final SearchField searchField = new SearchField();
	private final SearchPanel searchPanel = new SearchPanel(treeStructure,
			keyboard, searchField);
	private final BeanFieldGroup<KeyboardModel> binder = new BeanFieldGroup<KeyboardModel>(
			KeyboardModel.class);

	private String searchString;
	private int changePosCounter;

	public SearchPanelPresenter() {
		this.keyboard.addListener(this);
		this.binder.setBuffered(false);
		this.binder.setItemDataSource(new KeyboardModel());
		this.binder.bind(searchField, KeyboardModel.PROPERTY_SEARCHSTRING);
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
		searchString = getSearchString();
		if (changePosCounter < 0) {
			int positonCounter = searchString.length() + changePosCounter;

			searchString = searchString.substring(0, positonCounter)
					+ key
					+ searchString.substring(positonCounter,
							searchString.length());
		} else {
			searchString += key;
		}

		setSearchString(searchString);

	}

	public void deleteKeyFromSearchString() {
		searchString = getSearchString();
		int positonCounter = searchString.length() + changePosCounter;

		if (searchString.length() > 0) {
			if (positonCounter != 0) {
				searchString = searchString.substring(0, positonCounter - 1)
						+ searchString.substring(positonCounter,
								searchString.length());
			}
		}

		setSearchString(searchString);
	}

	public void clearSearchString() {
		setSearchString("");
	}

	public SearchPanel getSearchPanel() {
		return this.searchPanel;
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
		return (String) binder.getItemDataSource()
				.getItemProperty(KeyboardModel.PROPERTY_SEARCHSTRING)
				.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pathfinder.presenter.SearchPanelPresenterSpec#setSearchString(java
	 * .lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setSearchString(String value) {
		binder.getItemDataSource()
				.getItemProperty(KeyboardModel.PROPERTY_SEARCHSTRING)
				.setValue(value);
	}

}
