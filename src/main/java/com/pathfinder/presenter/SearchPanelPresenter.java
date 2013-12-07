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

import de.vksi.c4j.ContractReference;

@ContractReference(KeyboardPresenterContract.class)
public class SearchPanelPresenter implements KeyboardViewListenerSpec,
		SearchPanelPresenterSpec {

	KeyboardModel keyboard = new KeyboardModel();
	private final TreeStructure treeStructure = new TreeStructure();
	private final Keyboard keyboardView = new Keyboard();
	private final SearchField searchField = new SearchField();
	private final SearchPanel searchPanel = new SearchPanel(treeStructure, keyboardView, searchField);

	private String searchString;

	public SearchPanelPresenter() {
		keyboardView.addListener(this);
	}

	@Override
	public void buttonClick(String key) {
		if (!key.equals("DELETE")) {
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
	}

	public void deleteKeyFromSearchString() {
		searchString = keyboard.getSearchString();

		if (searchString.length() > 0) {
			searchString = searchString.substring(0, searchString.length() - 1);
		}

		keyboard.setSearchString(searchString);
	}

	public void clearSearchString() {
		keyboard.setSearchString("");
	}
	
	public SearchPanel getSearchPanel()
	{
		return this.searchPanel;
	}
}