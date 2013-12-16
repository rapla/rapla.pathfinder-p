package com.pathfinder.presenter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.pathfinder.model.KeyboardModel;
import com.pathfinder.view.components.Keyboard;
import com.pathfinder.view.components.KeyboardIds;

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
		searchPanelPresenter.buttonClick(KeyboardIds.DELETE);
		assertEquals("I", searchPanelPresenter.getSearchString());
	}

	@Test
	public void clearSearchStringTest() {
		searchPanelPresenter.buttonClick("I");
		searchPanelPresenter.buttonClick("K");
		searchPanelPresenter.clearSearchString();
		assertEquals("", searchPanelPresenter.getSearchString());
	}

	@Test
	public void complexKeyboardKeyTest() {

		searchPanelPresenter.buttonClick("L");
		searchPanelPresenter.buttonClick("P");
		assertEquals("LP", searchPanelPresenter.getSearchString());

		searchPanelPresenter.buttonClick(KeyboardIds.DELETE);
		searchPanelPresenter.buttonClick(KeyboardIds.DELETE);
		searchPanelPresenter.buttonClick(KeyboardIds.DELETE);
		searchPanelPresenter.buttonClick(KeyboardIds.DELETE);
		assertEquals("", searchPanelPresenter.getSearchString());

		searchPanelPresenter.buttonClick("T");
		searchPanelPresenter.buttonClick("Ä");
		searchPanelPresenter.buttonClick("ß");
		assertEquals("TÄß", searchPanelPresenter.getSearchString());

		searchPanelPresenter.buttonClick(KeyboardIds.SPACE);
		assertEquals("TÄß ", searchPanelPresenter.getSearchString());

		searchPanelPresenter.buttonClick(KeyboardIds.SPACE);
		searchPanelPresenter.buttonClick(KeyboardIds.SPACE);
		assertEquals("TÄß   ", searchPanelPresenter.getSearchString());

		searchPanelPresenter.buttonClick(KeyboardIds.RIGHT);
		searchPanelPresenter.buttonClick(KeyboardIds.RIGHT);
		searchPanelPresenter.buttonClick(KeyboardIds.RIGHT);
		searchPanelPresenter.buttonClick(KeyboardIds.RIGHT);
		searchPanelPresenter.buttonClick(KeyboardIds.RIGHT);
		searchPanelPresenter.buttonClick(KeyboardIds.DELETE);
		assertEquals("TÄß  ", searchPanelPresenter.getSearchString());

		searchPanelPresenter.buttonClick("Ü");
		searchPanelPresenter.buttonClick("Ü");
		searchPanelPresenter.buttonClick(KeyboardIds.LEFT);
		searchPanelPresenter.buttonClick("X");
		assertEquals("TÄß  ÜXÜ", searchPanelPresenter.getSearchString());

		searchPanelPresenter.buttonClick(KeyboardIds.LEFT);
		searchPanelPresenter.buttonClick(KeyboardIds.LEFT);
		searchPanelPresenter.buttonClick(KeyboardIds.LEFT);
		searchPanelPresenter.buttonClick(KeyboardIds.LEFT);
		searchPanelPresenter.buttonClick(KeyboardIds.LEFT);
		searchPanelPresenter.buttonClick(KeyboardIds.LEFT);
		searchPanelPresenter.buttonClick(KeyboardIds.LEFT);
		searchPanelPresenter.buttonClick(KeyboardIds.LEFT);
		searchPanelPresenter.buttonClick(KeyboardIds.LEFT);
		searchPanelPresenter.buttonClick(KeyboardIds.LEFT);
		searchPanelPresenter.buttonClick(KeyboardIds.LEFT);
		searchPanelPresenter.buttonClick("Ö");
		assertEquals("ÖTÄß  ÜXÜ", searchPanelPresenter.getSearchString());

		searchPanelPresenter.buttonClick(KeyboardIds.RIGHT);
		searchPanelPresenter.buttonClick(KeyboardIds.RIGHT);
		searchPanelPresenter.buttonClick(KeyboardIds.RIGHT);
		searchPanelPresenter.buttonClick("M");
		assertEquals("ÖTÄßM  ÜXÜ", searchPanelPresenter.getSearchString());
	}

}
