/**
 * This is the presenter from the Keyboard. 
 * 
 * @author Myracle
 */

package com.pathfinder.presenter;

import com.pathfinder.model.KeyboardModel;
import com.pathfinder.view.components.Keyboard;
import com.pathfinder.view.components.SearchField;
import com.pathfinder.view.components.TreeStructure;
import com.pathfinder.view.layout.SearchPanel;
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
	private final BeanFieldGroup<KeyboardModel> binder = new BeanFieldGroup<KeyboardModel>(KeyboardModel.class);

	private String searchString;

	public SearchPanelPresenter() {
		this.keyboardView.addListener(this);
		this.refreshItemDataSource();
		this.binder.bind(searchField, KeyboardModel.PROPERTY_SEARCHSTRING);
	}

	@Override
	public void buttonClick(String key) {
		// TODO Use Id´s instead of descriptions or values
		if (!key.equals("LÖSCHEN")) {
			addKeybordKeyToSearchString(key);
			System.out.println(keyboard.getSearchString());
		} else {
			deleteKeyFromSearchString();
			System.out.println(keyboard.getSearchString());
		}
	}

	public void addKeybordKeyToSearchString(String key) {
		searchString = keyboard.getSearchString();
		searchString += key;
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
	
	private void refreshItemDataSource()
	{
		binder.setItemDataSource(keyboard);
	}

	public SearchPanel getSearchPanel() {
		return this.searchPanel;
	}
}