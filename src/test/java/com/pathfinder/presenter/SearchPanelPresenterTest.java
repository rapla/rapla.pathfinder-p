package com.pathfinder.presenter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.pathfinder.model.KeyboardModel;
import com.pathfinder.view.components.Keyboard;

public class SearchPanelPresenterTest {
	KeyboardModel keyboardModel;
	Keyboard keyboard;
	SearchPanelPresenter searchPanelPresenter;

	@Before
	public void setUp() {
		searchPanelPresenter = new SearchPanelPresenter();
		keyboard = searchPanelPresenter.getKeyboard();

	}

	@Test
	public void addKeyboardKeyToSearchStringTest() {
		searchPanelPresenter.buttonClick("I");
		searchPanelPresenter.buttonClick("K");
		assertEquals("IK", searchPanelPresenter.getSearchString());
	}

	@Test
	public void deleteKeyboardKeyFromSearchStringTest() {
		searchPanelPresenter.buttonClick("I");
		searchPanelPresenter.buttonClick("K");
		searchPanelPresenter.buttonClick("DELETE");
		assertEquals("I", searchPanelPresenter.getSearchString());
	}

	@Test
	public void clearSearchStringTest() {
		searchPanelPresenter.buttonClick("I");
		searchPanelPresenter.buttonClick("K");
		searchPanelPresenter.clearSearchString();
		assertEquals("", searchPanelPresenter.getSearchString());
	}

}
