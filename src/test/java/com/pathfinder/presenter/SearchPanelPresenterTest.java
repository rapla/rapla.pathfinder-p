package com.pathfinder.presenter;

import static com.pathfinder.view.components.KeyboardId.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.pathfinder.model.KeyboardModel;
import com.pathfinder.view.components.Keyboard;
import com.pathfinder.view.components.KeyboardId;

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
		searchPanelPresenter.buttonClick(I);
		searchPanelPresenter.buttonClick(K);
		assertEquals("IK", searchPanelPresenter.getSearchString());
	}

	@Test
	public void deleteKeyboardKeyFromSearchStringTest() {
		searchPanelPresenter.buttonClick(I);
		searchPanelPresenter.buttonClick(K);
		searchPanelPresenter.buttonClick(DELETE);
		assertEquals("I", searchPanelPresenter.getSearchString());
	}

	@Test
	public void clearSearchStringTest() {
		searchPanelPresenter.buttonClick(I);
		searchPanelPresenter.buttonClick(K);
		searchPanelPresenter.clearSearchString();
		assertEquals("", searchPanelPresenter.getSearchString());
	}

	@Test
	public void complexKeyboardKeyTest() {

		searchPanelPresenter.buttonClick(L);
		searchPanelPresenter.buttonClick(P);
		assertEquals("LP", searchPanelPresenter.getSearchString());

		searchPanelPresenter.buttonClick(DELETE);
		searchPanelPresenter.buttonClick(DELETE);
		searchPanelPresenter.buttonClick(DELETE);
		searchPanelPresenter.buttonClick(DELETE);
		assertEquals("", searchPanelPresenter.getSearchString());

		searchPanelPresenter.buttonClick(T);
		searchPanelPresenter.buttonClick(AE);
		searchPanelPresenter.buttonClick(S);
		assertEquals("TÄS", searchPanelPresenter.getSearchString());

		searchPanelPresenter.buttonClick(SPACE);
		assertEquals("TÄS ", searchPanelPresenter.getSearchString());

		searchPanelPresenter.buttonClick(SPACE);
		searchPanelPresenter.buttonClick(SPACE);
		assertEquals("TÄS   ", searchPanelPresenter.getSearchString());

		searchPanelPresenter.buttonClick(KeyboardId.RIGHT);
		searchPanelPresenter.buttonClick(KeyboardId.RIGHT);
		searchPanelPresenter.buttonClick(KeyboardId.RIGHT);
		searchPanelPresenter.buttonClick(KeyboardId.RIGHT);
		searchPanelPresenter.buttonClick(KeyboardId.RIGHT);
		searchPanelPresenter.buttonClick(KeyboardId.DELETE);
		assertEquals("TÄS  ", searchPanelPresenter.getSearchString());

		searchPanelPresenter.buttonClick(UE);
		searchPanelPresenter.buttonClick(UE);
		searchPanelPresenter.buttonClick(LEFT);
		searchPanelPresenter.buttonClick(X);
		assertEquals("TÄS  ÜXÜ", searchPanelPresenter.getSearchString());

		searchPanelPresenter.buttonClick(LEFT);
		searchPanelPresenter.buttonClick(LEFT);
		searchPanelPresenter.buttonClick(LEFT);
		searchPanelPresenter.buttonClick(LEFT);
		searchPanelPresenter.buttonClick(LEFT);
		searchPanelPresenter.buttonClick(LEFT);
		searchPanelPresenter.buttonClick(LEFT);
		searchPanelPresenter.buttonClick(LEFT);
		searchPanelPresenter.buttonClick(LEFT);
		searchPanelPresenter.buttonClick(LEFT);
		searchPanelPresenter.buttonClick(LEFT);
		searchPanelPresenter.buttonClick(OE);
		assertEquals("ÖTÄS  ÜXÜ", searchPanelPresenter.getSearchString());

		searchPanelPresenter.buttonClick(RIGHT);
		searchPanelPresenter.buttonClick(RIGHT);
		searchPanelPresenter.buttonClick(RIGHT);
		searchPanelPresenter.buttonClick(M);
		assertEquals("ÖTÄSM  ÜXÜ", searchPanelPresenter.getSearchString());
	}

}
